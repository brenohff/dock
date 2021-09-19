package com.brenohff.dock.exception;

public class DuplicatedTerminalException extends RuntimeException {

    public DuplicatedTerminalException() {
    }

    public DuplicatedTerminalException(String message) {
        super(message);
    }

    public DuplicatedTerminalException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicatedTerminalException(Throwable cause) {
        super(cause);
    }
}
