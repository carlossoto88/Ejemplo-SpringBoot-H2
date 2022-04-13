package com.ejemplo.ejemplo.dao;

import org.springframework.stereotype.Component;


public class response_messages {
    private String messages;
    private String descripcion;

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
