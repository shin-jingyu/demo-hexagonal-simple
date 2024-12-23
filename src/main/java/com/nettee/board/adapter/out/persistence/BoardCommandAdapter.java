package com.nettee.board.adapter.out.persistence;

import com.nettee.board.adapter.out.mapper.BoardEntityMapper;
import com.nettee.board.application.domain.Board;
import com.nettee.board.application.exception.BoardCommandErrorCode;
import com.nettee.board.application.port.BoardCommandPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import static java.time.Instant.now;

@Repository
@RequiredArgsConstructor
public class BoardCommandAdapter implements BoardCommandPort {
    private final BoardJpaRespository boardJpaRespository;
    private final BoardEntityMapper boardEntityMapper;


    @Override
    public Board createBoard(Board board) {
        var boardEntity = boardEntityMapper.toEntity(board);
        return boardEntityMapper.toDomain(boardJpaRespository.save(boardEntity));
    }

    @Override
    public Board updateBoard(Board board) {
        var existBoard = boardJpaRespository.findById(board.getId()).orElseThrow(
                BoardCommandErrorCode.BOARD_NOT_FOUND::defaultException);
        existBoard.prepareUpdate()
                .title(board.getTitle())
                .content(board.getContent())
                .updatedAt(now())
                .update();
        return boardEntityMapper.toDomain(boardJpaRespository.save(existBoard));
    }

    @Override
    public void deleteBoard(Long id) {
        var existBoard = boardJpaRespository.findById(id).orElseThrow(
          BoardCommandErrorCode.BOARD_NOT_FOUND::defaultException
        );
        existBoard.setToDelete();
    }
}
