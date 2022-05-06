package com.springheroes.wellco.exceptions;

public class NotificationNotFoundException extends RuntimeException{
    public NotificationNotFoundException(Long id) {
        super("Couldn't find notification with id: "+ id);
    }
}
