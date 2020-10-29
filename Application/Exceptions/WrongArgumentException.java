package com.company.Application.Exceptions;

/**
 * Exception to catch wrong arguments
 */
public class WrongArgumentException extends Exception{
    public WrongArgumentException(String message) {
        super(message);
    }
}
