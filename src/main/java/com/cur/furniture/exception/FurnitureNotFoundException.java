package com.cur.furniture.exception;

public class FurnitureNotFoundException extends RuntimeException {

    public FurnitureNotFoundException(String message) {
        super(message);
    }

    public FurnitureNotFoundException(Long id) {
        super("Furniture not found: " + id);
    }

}
