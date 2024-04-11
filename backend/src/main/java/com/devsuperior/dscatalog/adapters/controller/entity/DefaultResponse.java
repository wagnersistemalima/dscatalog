package com.devsuperior.dscatalog.adapters.controller.entity;

import java.io.Serializable;

public class DefaultResponse <T> implements Serializable {

    private T data;

    @Deprecated
    public DefaultResponse() {
    }

    public DefaultResponse(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DefaultResponse{" +
                "data=" + data +
                '}';
    }
}
