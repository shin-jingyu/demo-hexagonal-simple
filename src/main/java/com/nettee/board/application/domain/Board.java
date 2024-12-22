package com.nettee.board.application.domain;

import com.nettee.board.application.domain.type.BoardStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class Board {
    private String title;
    private String content;
    private BoardStatus status;
    private Instant createdAt;
    private Instant updatedAt;
}
