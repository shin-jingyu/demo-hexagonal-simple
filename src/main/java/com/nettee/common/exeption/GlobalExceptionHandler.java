package com.nettee.common.exeption;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException exception) {
        record ApiErrorResponse(
                int status,
                String code,
                String message
        ) {}

        ErrorCode errorCode = exception.getErrorCode();

        var responseBody = new ApiErrorResponse(
                errorCode.defaultHttpStatus().value(),
                errorCode.name(),
                exception.getMessage()
        );

        return ResponseEntity
                .status(errorCode.defaultHttpStatus())
                .body(responseBody);
    }
}
