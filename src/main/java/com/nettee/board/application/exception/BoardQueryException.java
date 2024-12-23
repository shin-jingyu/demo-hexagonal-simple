package com.nettee.board.application.exception;

import com.nettee.common.exeption.CustomException;
import com.nettee.common.exeption.ErrorCode;

public class BoardQueryException extends CustomException {
    public BoardQueryException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BoardQueryException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
