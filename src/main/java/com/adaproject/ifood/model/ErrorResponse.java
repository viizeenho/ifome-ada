package com.adaproject.ifood.model;

public class ErrorResponse {
    private int status;
    private String error;
    private String message;
    private String path;

    // Construtores, getters e setters

    // Exemplo de construtor
    public ErrorResponse(int status, String error, String message, String path) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    // Exemplo de getter
    public int getStatus() {
        return status;
    }
}
