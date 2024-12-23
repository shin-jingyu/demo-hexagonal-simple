package com.nettee.board.adapter.out.persistence;

import com.nettee.board.adapter.out.mapper.BoardEntityMapper;
import com.nettee.board.adapter.out.persistence.entity.BoardEntity;
import com.nettee.board.application.domain.Board;
import com.nettee.board.application.domain.type.BoardStatus;
import com.nettee.board.application.port.BoardQueryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public class BoardQueryAdapter extends QuerydslRepositorySupport implements BoardQueryPort {

    private final BoardEntityMapper boardEntityMapper;

    public BoardQueryAdapter(BoardEntityMapper boardEntityMapper) {
        super(BoardEntity.class);
        this.boardEntityMapper = boardEntityMapper;
    }

    @Override
    public Page<Board> findPageByStatusIn(Collection<BoardStatus> status, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Board> findBoardById(Long id) {
        return boardEntityMapper.toOptionalDomain(
                getQuerydsl().createQuery()
                        .select(BoardEntity)
                        .from(BoardEntity)
                        .where(
                                BoardEntity.id.eq(id)
                        ).fetchOne()
        );
    }
}
