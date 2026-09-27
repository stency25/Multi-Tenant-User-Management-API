package com.example.usermanagement.excemption;
//handles errors for non exixsting records
public class ResourceNotFoundException extends RuntimeException {

    private  final String code;

    public ResourceNotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }
    //get method
    public String getCode() {
        return code;
    }
}
