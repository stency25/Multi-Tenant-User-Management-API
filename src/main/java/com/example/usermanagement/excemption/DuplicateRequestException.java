package com.example.usermanagement.excemption;



public class DuplicateRequestException extends RuntimeException {
    private final  String code;

    public DuplicateRequestException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
