package com.cur.furniture.config;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cur.furniture.exception.CategoryNotFoundException;
import com.cur.furniture.exception.FurnitureNotFoundException;
import com.cur.furniture.exception.UserAlreadyExistsException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FurnitureNotFoundException.class)
    public ResponseEntity<?> handleNotFound(FurnitureNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND) // 404
            .body(Map.of(
                "timestamp", Instant.now().toString(),
                "status", 404,
                "error", "Not Found",
                "message", ex.getMessage(),
                "code", "NOT_FOUND"
            ));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<?> handleNotFound(CategoryNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND) // 404
            .body(Map.of(
                "timestamp", Instant.now().toString(),
                "status", 404,
                "error", "Not Found",
                "message", ex.getMessage(),
                "code", "NOT_FOUND"
            ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Bad Request");
        
        String errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(error -> error.getDefaultMessage())
            .collect(Collectors.joining(", "));
        
        response.put("message", errors);
        
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(
            HttpMessageNotReadableException ex) {
        
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Bad Request");
        
        response.put("message", ex.getMessage());
        
        return ResponseEntity.badRequest().body(response);
    }

     @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> handleUsernameNotFound(UsernameNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND) 
            .body(Map.of(
                "timestamp", Instant.now().toString(),
                "status", 404,
                "error", "Not Found",
                "message", ex.getMessage(),
                "code", "NOT_FOUND"
            ));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        return ResponseEntity
            .status(HttpStatus.CONFLICT) // 409
            .body(Map.of(
                "timestamp", Instant.now().toString(),
                "status", 409,
                "error", "User already exists",
                "message", ex.getMessage(),
                "code", "CONFLICT"
            ));
    }

}
