package it.jpanik.jgaming.controllers;

import it.jpanik.jgaming.dtos.ErrorDto;
import it.jpanik.jgaming.exceptions.NotImplementedException;
import it.jpanik.jgaming.exceptions.ServiceException;
import it.jpanik.jgaming.exceptions.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(value = { ValidationException.class })
    protected ResponseEntity<Object> handler(ValidationException ex, WebRequest request) {
        ErrorDto error = new ErrorDto(ex);
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = { ServiceException.class })
    protected ResponseEntity<Object> handler(ServiceException ex, WebRequest request) {
        ErrorDto error = new ErrorDto(ex);
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.EXPECTATION_FAILED, request);
    }

    @ExceptionHandler(value = { NotImplementedException.class })
    protected ResponseEntity<Object> handler(NotImplementedException ex, WebRequest request) {
        ErrorDto error = new ErrorDto();
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_IMPLEMENTED, request);
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<Object> handler(Exception ex, WebRequest request) {
        LOGGER.error("Unexpected Error", ex);
        ErrorDto error = new ErrorDto();
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
