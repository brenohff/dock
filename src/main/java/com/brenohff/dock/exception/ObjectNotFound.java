package com.brenohff.dock.exception;

public class ObjectNotFound extends RuntimeException {

    public ObjectNotFound() {
    }

    public ObjectNotFound(String message) {
        super(message);
    }

    public ObjectNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectNotFound(Throwable cause) {
        super(cause);
    }
}
