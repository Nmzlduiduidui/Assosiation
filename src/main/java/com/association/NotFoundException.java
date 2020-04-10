package com.association;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //添加状态，
public class NotFoundException extends RuntimeException {
    public NotFoundException(){

    }

    public NotFoundException(String message){

    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
