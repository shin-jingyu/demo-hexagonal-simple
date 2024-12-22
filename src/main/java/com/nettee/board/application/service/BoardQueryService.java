package com.nettee.board.application.service;

import com.nettee.board.application.domain.Board;
import com.nettee.board.application.domain.type.BoardStatus;
import com.nettee.board.application.port.BoardQueryPort;
import com.nettee.board.application.usecase.BoardReadUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class BoardQueryService implements BoardReadUseCase {

    private final BoardQueryPort boardQueryPort;

    @Override
    public Board getBoard(Long id) {
        return boardQueryPort.findBoardById(id).orElse(null);
    }

    @Override
    public Page<Board> findGeneralBy(Pageable pageable) {
        Collection<BoardStatus> statuses = BoardStatus.getGeneralQueryStatus();
        return boardQueryPort.findPageByStatusIn(statuses, pageable);
    }
}
