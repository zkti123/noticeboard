package com.example.demo.service;

import com.example.demo.dto.CommentDto;
import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.CommentEntity;
import com.example.demo.repository.BoardRepository;
import com.example.demo.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    public Long save(CommentDto commentDto) {
        /* 부모엔티티(BoardEntity) 조회 */
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDto.getBoardId());
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDto, boardEntity);
            return commentRepository.save(commentEntity).getId();
        } else {
            return null;
        }
    }

    public List<CommentDto> findAll(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);
        /* EntityList -> DTOList */
        List<CommentDto> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity: commentEntityList) {
            CommentDto commentDTO = CommentDto.toCommentDTO(commentEntity, boardId);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
