package com.example.demo.entity;

import com.example.demo.dto.CommentDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "comment_table")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String commentWriter;

    @Column
    private String commentContents;

    /*
    Board: comment = 1:N
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    public static CommentEntity toSaveEntity(CommentDto commentDto,BoardEntity boardEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(commentDto.getId());
        commentEntity.setCommentWriter(commentDto.getCommentWriter());
        commentEntity.setCommentContents(commentDto.getCommentContents());
        commentEntity.setBoardEntity(boardEntity);
        return commentEntity;
    }
}
