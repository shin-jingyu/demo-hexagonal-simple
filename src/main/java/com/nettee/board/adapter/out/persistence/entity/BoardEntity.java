package com.nettee.board.adapter.out.persistence.entity;

import com.nettee.board.application.domain.type.BoardStatus;
import com.nettee.common.jpa.support.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import java.time.Instant;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Entity(name = "board")
public class BoardEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "title", length = 50, nullable = false)
    private String title;

    @Lob // 큰 데이터를 저장할때 사용
    @Column(name = "content", columnDefinition = "LONGTEXT", nullable = false)
    private String content;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BoardStatus status;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    public BoardStatus status() {
        return status;
    }

    @Builder
    public BoardEntity(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Builder(
            builderClassName = "CreateBoardBuilder",
            builderMethodName = "prepareCreate",
            buildMethodName = "create"
    )
    public void createBoard(String title, String content, BoardStatus status, Instant createdAt) {
        this.title = title;
        this.content = content;
        this.status = status;
        this.createdAt = createdAt;
    }

    @Builder(
            builderClassName = "UpdateBoardBuilder",
            builderMethodName = "prepareUpdate",
            buildMethodName = "update"
    )
    public void updateBoard(String title, String content, Instant updatedAt) {
        Objects.requireNonNull(title, "title cannot be null");
        Objects.requireNonNull(content, "content cannot be null");
        Objects.requireNonNull(updatedAt, "updatedAt cannot be null");

        this.updatedAt = updatedAt;
        this.title = title;
        this.content = content;
    }

    public void setToDelete() {
        this.status = BoardStatus.REMOVED;
        this.updatedAt = Instant.now();
    }
}
