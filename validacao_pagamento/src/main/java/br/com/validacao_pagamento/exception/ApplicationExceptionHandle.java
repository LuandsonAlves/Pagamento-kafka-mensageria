package br.com.validacao_pagamento.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApplicationExceptionHandle extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> notFound(ValidationException ex, WebRequest request) {
        var response = ErrorResponse.builder()
                .error(ex.getMessage())
                .timestamp(new Date())
                .code(HttpStatus.BAD_REQUEST.value())
                .path(request.getDescription(false))
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        var erros = e.getFieldErrors().stream()
                .map(item -> item.getField() + " " + item.getDefaultMessage())
                .collect(Collectors.toList());

        var response = ErrorResponse.builder()
                .messages(erros)
                .error(HttpStatus.BAD_REQUEST.toString())
                .timestamp(new Date())
                .code(HttpStatus.BAD_REQUEST.value())
                .path(request.getDescription(false))
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}