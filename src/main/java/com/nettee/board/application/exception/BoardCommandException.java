package com.nettee.board.application.exception;

import com.nettee.common.exeption.CustomException;
import com.nettee.common.exeption.ErrorCode;

public class BoardCommandException extends CustomException {
    // (intellij) Ctrl + O
    // (eclipse or sts) Alt Shift S => generate constructors

    public BoardCommandException(ErrorCode errorCode) {
        super(errorCode);
    }

    public BoardCommandException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }
}
