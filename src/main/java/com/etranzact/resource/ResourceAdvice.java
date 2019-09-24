package com.etranzact.resource;

import com.etranzact.core.exceptions.CustomException;
import com.etranzact.core.exceptions.ErrorDetails;
import org.modelmapper.MappingException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * Created by johnadeshola on 9/21/19.
 */
@RestControllerAdvice
public class ResourceAdvice {

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<ErrorDetails> handleCustomException(CustomException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(true));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
    public final ResponseEntity<ErrorDetails> handleInvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(true));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MappingException.class)
    public final ResponseEntity<ErrorDetails> handleMappingException(MappingException ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(true));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
