package com.nettee.board.adapter.out.persistence;

import com.nettee.board.adapter.out.mapper.BoardEntityMapper;
import com.nettee.board.adapter.out.persistence.entity.BoardEntity;
import com.nettee.board.application.domain.Board;
import com.nettee.board.application.domain.type.BoardStatus;
import com.nettee.board.application.port.BoardQueryPort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

import static com.nettee.board.adapter.out.persistence.entity.QBoardEntity.boardEntity;

@Repository
public class BoardQueryAdapter extends QuerydslRepositorySupport implements BoardQueryPort {

    private final BoardEntityMapper boardEntityMapper;

    public BoardQueryAdapter(BoardEntityMapper boardEntityMapper) {
        super(BoardEntity.class);
        this.boardEntityMapper = boardEntityMapper;
    }

    @Transactional
    @Override
    public Page<Board> findPageByStatusIn(Collection<BoardStatus> status, Pageable pageable) {
        var result = getQuerydsl().createQuery()
                .select(boardEntity)
                .from(boardEntity)
                .where(
                        boardEntity.status.in(status)
                );
        var totalCount = getQuerydsl().createQuery()
                .select(boardEntity.content)
                .from(boardEntity)
                .where(
                        boardEntity.status.in(status)
                );
        return PageableExecutionUtils.getPage(
                result.stream().map(boardEntityMapper::toDomain).toList(),
                pageable,
                totalCount::fetchCount);
    }

    @Transactional
    @Override
    public Optional<Board> findBoardById(Long id) {
        return boardEntityMapper.toOptionalDomain(
                getQuerydsl().createQuery()
                        .select(boardEntity)
                        .from(boardEntity)
                        .where(
                                boardEntity.id.eq(id)
                        ).fetchOne()
        );
    }
}
