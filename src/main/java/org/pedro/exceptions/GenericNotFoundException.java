package org.pedro.exceptions;

import java.io.Serial;

public class GenericNotFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUId = 113582324531L;

    public GenericNotFoundException(String message) {
        super(message);
    }

    public GenericNotFoundException(String entityName, Integer entityId) {
        super(entityName + " with id: " + entityId + " not found");
    }

    public GenericNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
