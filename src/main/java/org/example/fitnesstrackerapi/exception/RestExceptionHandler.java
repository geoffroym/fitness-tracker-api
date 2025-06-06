package org.example.fitnesstrackerapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(BaseNotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> handleNotFound(BaseNotFoundException ex) {
        ApiExceptionResponse response = ApiExceptionResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reason(ex.getMessage())
                .timestamp(ZonedDateTime.now(ZoneId.of("Europe/Oslo")))
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}