package com.nettee.board.application.exception;

import com.nettee.common.exeption.ErrorCode;
import org.springframework.http.HttpStatus;

public enum BoardCommandErrorCode implements ErrorCode {
    BOARD_NOT_FOUND("게시물을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    BOARD_GONE("더 이상 존재하지 않는 게시물입니다.", HttpStatus.GONE),
    BOARD_FORBIDDEN("권한이 없습니다.", HttpStatus.FORBIDDEN),
    DEFAULT("게시물 조작 오류", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus status;

    BoardCommandErrorCode(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    @Override
    public String defaultMessage() {
        return message;
    }

    @Override
    public HttpStatus defaultHttpStatus() {
        return status;
    }

    @Override
    public BoardCommandException defaultException() {
        return new BoardCommandException(this);
    }

    @Override
    public BoardCommandException defaultException(Throwable cause) {
        return new BoardCommandException(this, cause);
    }
}
