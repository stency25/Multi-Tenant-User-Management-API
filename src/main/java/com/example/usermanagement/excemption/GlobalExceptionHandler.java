package com.example.usermanagement.excemption;

import com.example.usermanagement.dto.ApiResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateRequestException.class)
    public ResponseEntity<ApiResponseDto<Void>>handleDuplicate(DuplicateRequestException exception){

        return error(HttpStatus.CONFLICT, exception.getCode(),exception.getMessage());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleNotFound(ResourceNotFoundException ex) {
        return error(HttpStatus.NOT_FOUND, ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleDenied(PermissionDeniedException ex) {
        return error(HttpStatus.FORBIDDEN, ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleBadArgument(IllegalArgumentException ex) {
        return error(HttpStatus.BAD_REQUEST, "BAD_REQUEST", ex.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<ApiResponseDto<Void>>handleValidation(MethodArgumentNotValidException exception){
        String message =exception.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return error(HttpStatus.BAD_REQUEST, "VALIDATION_ERROR", message);
    }


    private ResponseEntity<ApiResponseDto<Void>> error(HttpStatus status,String code, String message) {
        ApiResponseDto<Void> body = new ApiResponseDto<>();
        body.setStatus("error");
        body.setCode(code);
        body.setMessage(message);
        return ResponseEntity.status(status).body(body);
    }
@ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResponseDto<Void>>  handleUnauthorized( UnauthorizedException exception){
        return  error(HttpStatus.UNAUTHORIZED, exception.getCode(),exception.getMessage());
    }
}

