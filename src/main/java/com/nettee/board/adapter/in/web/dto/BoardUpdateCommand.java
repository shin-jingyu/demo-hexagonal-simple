package com.nettee.board.adapter.in.web.dto;

import jakarta.validation.constraints.NotNull;

public record BoardUpdateCommand(
        @NotNull Long id
) {
}
