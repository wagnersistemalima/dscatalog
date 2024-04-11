package com.devsuperior.dscatalog.adapters.controller.exceptions;

import java.io.Serializable;

public class BussinessException extends RuntimeException implements Serializable {

    public BussinessException(String message) {
        super(message);
    }
}
