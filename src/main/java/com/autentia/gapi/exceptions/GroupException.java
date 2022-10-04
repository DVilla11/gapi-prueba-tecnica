package com.autentia.gapi.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GroupException extends RuntimeException{

    private String message;
    private HttpStatus httpStatus;

    public GroupException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }

}
