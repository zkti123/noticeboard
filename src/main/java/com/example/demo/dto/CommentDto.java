package com.example.demo.dto;

import com.example.demo.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentDto {
    private Long id;
    private  String  commentWriter;
    private String commentContents;
    private Long boardId;
    private LocalDateTime commentCreatedTime;

    public static CommentDto toCommentDTO(CommentEntity commentEntity, Long boardId) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(commentEntity.getId());
        commentDto.setCommentContents(commentEntity.getCommentContents());
        commentDto.setCommentWriter(commentEntity.getCommentWriter());
        commentDto.setCommentCreatedTime(commentEntity.getBoardEntity().getCreatedTime());
//        commentDto.setBoardId(commentEntity.getBoardEntity().getId()); //Service 메서드에 @Tramsactional
        commentDto.setBoardId(boardId);
        return commentDto;
    }
}
