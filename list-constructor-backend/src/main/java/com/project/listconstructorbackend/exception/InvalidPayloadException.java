package com.project.listconstructorbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidPayloadException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidPayloadException(String message) {
        super(message);
    }

}
