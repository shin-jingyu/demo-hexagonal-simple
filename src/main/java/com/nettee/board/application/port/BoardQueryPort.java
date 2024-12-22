package com.nettee.board.application.port;

import com.nettee.board.application.domain.Board;
import com.nettee.board.application.domain.type.BoardStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.Optional;

public interface BoardQueryPort {
    Page<Board> findPageByStatusIn(Collection<BoardStatus> status, Pageable pageable);
    Optional<Board> findBoardById(Long id);
}
