package com.devsuperior.dscatalog.adapters.controller.exceptions;

import java.io.Serializable;

public class ResourceNotFoundException extends RuntimeException implements Serializable {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
