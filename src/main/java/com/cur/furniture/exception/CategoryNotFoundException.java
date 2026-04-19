package com.cur.furniture.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(Long id) {
        super("Category not found: " + id);
    }

}
