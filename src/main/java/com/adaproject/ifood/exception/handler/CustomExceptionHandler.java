package com.adaproject.ifood.exception.handler;

import com.adaproject.ifood.exception.NumberDocumentException;
import com.adaproject.ifood.exception.RenavamDuplicadoException;
import com.adaproject.ifood.exception.ValidarDataException;
import com.adaproject.ifood.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(RenavamDuplicadoException.class)
    public ResponseEntity<ErrorResponse> handleRenavamDuplicadoException(RenavamDuplicadoException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage(), "/ifood/veiculos");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberDocumentException.class)
    public ResponseEntity<ErrorResponse> numberDocumentException(NumberDocumentException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage(), "/ifood/entregadores");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidarDataException.class)
    public ResponseEntity<ErrorResponse> validarDataException(ValidarDataException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", ex.getMessage(), "/ifood/entregadores");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

