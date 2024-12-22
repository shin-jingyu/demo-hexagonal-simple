package com.nettee.board.application.usecase;

import com.nettee.board.application.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardReadUseCase {
    Board getBoard(Long id);
    Page<Board> findGeneralBy(Pageable pageable);
}
