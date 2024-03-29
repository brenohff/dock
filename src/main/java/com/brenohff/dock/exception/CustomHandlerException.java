package com.brenohff.dock.exception;

import com.brenohff.dock.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomHandlerException {

    @ExceptionHandler(JsonValidationException.class)
    public ResponseEntity<ErrorDTO> jsonValidationException(JsonValidationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generateError(HttpStatus.BAD_REQUEST, e.getLocalizedMessage().replace("#: ", "")));
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<ErrorDTO> objectNotFound(ObjectNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(generateError(HttpStatus.NOT_FOUND, e.getLocalizedMessage()));
    }

    @ExceptionHandler(DuplicatedTerminalException.class)
    public ResponseEntity<ErrorDTO> duplicatedTerminalException(DuplicatedTerminalException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(generateError(HttpStatus.CONFLICT, e.getLocalizedMessage()));
    }

    @ExceptionHandler(SchemaNotFoundException.class)
    public ResponseEntity<ErrorDTO> schemaNotFoundException(SchemaNotFoundException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(generateError(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> methodNotAllowed(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(generateError(HttpStatus.METHOD_NOT_ALLOWED, e.getLocalizedMessage()));
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ErrorDTO> unsuportedMediaType(HttpMediaTypeNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(generateError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, e.getLocalizedMessage()));
    }

    private ErrorDTO generateError(HttpStatus status, String error) {
        return new ErrorDTO().setMessage(error).setTimestamp(System.currentTimeMillis()).setStatus(status);
    }
}
