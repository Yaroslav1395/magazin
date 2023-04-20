package com.example.magazin.exceptions;

public class EntryAlreadyExists extends RuntimeException{
    public EntryAlreadyExists(String message) {
        super(message);
    }
}
