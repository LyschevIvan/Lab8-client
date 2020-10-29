package com.company.Application.Exceptions;

public class NoConnectionException extends Exception {
    public NoConnectionException() {
        super("Connection lost!");
    }
}
