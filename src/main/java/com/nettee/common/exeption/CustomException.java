package com.nettee.common.exeption;

public class CustomException extends RuntimeException {
    protected ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.defaultMessage());
        this.errorCode = errorCode;
    }

    public CustomException(ErrorCode errorCode, Throwable throwable) {
        super(errorCode.defaultMessage(), throwable);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

}
