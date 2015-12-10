package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DataInvalidException extends RuntimeException {

    private static final long serialVersionUID = 5508057135307388568L;

    public DataInvalidException(Class<?> clazz) {
        super(clazz.getSimpleName() + " invalid");
    }

    public DataInvalidException(String message) {
        super(message);
    }
}
