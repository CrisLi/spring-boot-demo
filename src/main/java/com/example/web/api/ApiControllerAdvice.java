package com.example.web.api;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ApiControllerAdvice.class);

    public static class ErrorResponse {

        private String message;
        private LocalDateTime dateTime;
        private String path;
        private int status;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public LocalDateTime getDateTime() {
            return dateTime;
        }

        public void setDateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handException(HttpServletRequest request, Exception exception) {

        logger.error("Request {} raised \"{}\" exception ", request.getRequestURL(), exception.getClass().getName());
        logger.error(exception.getClass().getName(), exception);

        ResponseStatus responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);
        HttpStatus httpStatus = null;

        if (responseStatus == null) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            httpStatus = responseStatus.code();
        }

        ErrorResponse response = new ErrorResponse();

        response.setDateTime(LocalDateTime.now());
        response.setPath(request.getRequestURI());
        response.setMessage(exception.getMessage());
        response.setStatus(httpStatus.value());

        return new ResponseEntity<>(response, httpStatus);
    }
}
