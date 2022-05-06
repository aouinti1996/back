package com.springheroes.wellco.exceptions;

public class UsernameExistException extends Exception {
    public UsernameExistException(String message) {
        super(message);
    }
}
