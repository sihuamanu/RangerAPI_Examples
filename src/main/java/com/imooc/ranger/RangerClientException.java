package com.imooc.ranger;

public class RangerClientException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Throwable cause;
    private int status;
    private String message;

    public RangerClientException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return String.format("%s http status = %s", message, status);
    }

    @Override
    public String toString() {
        return String.format("%s http status = %s", message, status);
    }
}
