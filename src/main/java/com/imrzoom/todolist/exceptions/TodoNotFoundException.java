package com.imrzoom.todolist.exceptions;

public class TodoNotFoundException extends RuntimeException{

    public TodoNotFoundException() {
        super("Todo not found!");
    }

    public TodoNotFoundException(String message) {
        super(message);
    }
}
