package com.uniandes.keymanager.exception;

/**
 *
 * @author cuent
 */
public class FileStorageException extends RuntimeException {

    /**
     *
     * @param message
     */
    public FileStorageException(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
