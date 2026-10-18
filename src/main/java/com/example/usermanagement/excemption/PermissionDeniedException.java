package com.example.usermanagement.excemption;



public class PermissionDeniedException extends RuntimeException{
    private final String code;
//constructor
    public PermissionDeniedException(String message, String code) {
        super(message);
        this.code = code;
    }
//getter methid
    public String getCode() {
        return code;
    }
}
