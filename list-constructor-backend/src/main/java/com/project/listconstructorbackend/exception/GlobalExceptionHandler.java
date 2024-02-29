package com.project.listconstructorbackend.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String INVALID_PAYLOAD = "Invalid Payload";

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<ErrorInfo> handleResourceNotFoundException(Exception ex, WebRequest request) {
        ErrorInfo errorInfo = new ErrorInfo(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({InvalidPayloadException.class})
    public ResponseEntity<ErrorInfo> handleInvalidPayloadException(Exception ex, WebRequest request) {
        ErrorInfo errorInfo = new ErrorInfo(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({TransactionSystemException.class})
    public ResponseEntity<ErrorInfo> handleConstraintViolationException(Exception ex, WebRequest request) {

        Throwable cause = ((TransactionSystemException) ex).getRootCause();
        if (cause instanceof ConstraintViolationException constraintViolationException) {

            final List<String> errors = new ArrayList<>();
            for (final ConstraintViolation<?> violation : constraintViolationException.getConstraintViolations()) {
                errors.add(violation.getPropertyPath() + ": " + violation.getMessage());
            }

            DetailedErrorInfo errorInfo =
                    new DetailedErrorInfo(INVALID_PAYLOAD, request.getDescription(false), errors);
            return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
        }

        ErrorInfo errorInfo = new ErrorInfo(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorInfo> handleGenericException(Exception ex, WebRequest request) {
        ErrorInfo errorInfo = new ErrorInfo(ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
