package com.uniandes.keymanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author cuent
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends RuntimeException {

    /**
     *
     * @param message
     */
    public MyFileNotFoundException(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public MyFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
