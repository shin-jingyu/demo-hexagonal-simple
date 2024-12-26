package com.nettee.board.adapter.in.web;

import com.nettee.board.adapter.in.mapper.BoardDtoMapper;
import com.nettee.board.adapter.in.web.dto.BoardCreateCommand;
import com.nettee.board.adapter.in.web.dto.BoardDeleteCommand;
import com.nettee.board.adapter.in.web.dto.BoardDto;
import com.nettee.board.adapter.in.web.dto.BoardUpdateCommand;
import com.nettee.board.application.usecase.BoardCreateUseCase;
import com.nettee.board.application.usecase.BoardDeleteUseCase;
import com.nettee.board.application.usecase.BoardUpdateUseCase;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardCommandController {

    private final BoardCreateUseCase boardCreateUseCase;
    private final BoardUpdateUseCase boardUpdateUseCase;
    private final BoardDeleteUseCase boardDeleteUseCase;
    private final BoardDtoMapper boardDtoMapper;

    @Operation(
            summary = "게시판 등록 API",
            description = "등록할 게시판 데이터를 전달받아 새로운 게시판을 등록합니다."
    )
    @PostMapping
    public ResponseEntity<BoardDto> create( @RequestBody @Valid BoardCreateCommand boardCreateCommand) {
        var board = boardCreateUseCase.create(boardDtoMapper.toDomain(boardCreateCommand));
        return ResponseEntity.ok(boardDtoMapper.toDto(board));
    }

    @Operation(
            summary = "게시판 수정 API",
            description = "수정할 포스트의 항목(들)을 전달받아 포스트를 수정합니다."
    )
    @PutMapping("/{boardId}")
    public ResponseEntity<BoardDto> update(@PathVariable("boardId") Long boardId, @RequestBody @Valid BoardUpdateCommand boardUpdateCommand) {
        var board = boardUpdateUseCase.updateBoard(boardDtoMapper.toDomain(boardUpdateCommand));
        return ResponseEntity.ok(boardDtoMapper.toDto(board));
    }

    @Operation(
            summary = "게시판 삭제 API",
            description = "삭제할 게시판의 PK 값을 전달받아 게시판을 삭제합니다."
    )
    @DeleteMapping("/{boardId}")
    public void delete(@PathVariable("boardId") Long boardId,@RequestBody BoardDeleteCommand boardDeleteCommand) {
        boardDeleteUseCase.delete(boardDeleteCommand.id());
    }
}
