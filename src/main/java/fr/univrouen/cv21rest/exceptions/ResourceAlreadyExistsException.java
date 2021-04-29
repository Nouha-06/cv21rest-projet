package fr.univrouen.cv21rest.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ResourceAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    public ResourceAlreadyExistsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}