package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -8735586007560091224L;

    public ResourceNotFoundException(Class<?> clazz) {
        super(clazz.getSimpleName() + " not found");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
