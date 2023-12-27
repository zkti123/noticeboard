package com.example.demo.service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.BoardEntity;
import com.example.demo.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// DTO -> Entity
//Entity -> DTO
@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public void save(BoardDTO bOardDTO) {
        BoardEntity saveEntity = BoardEntity.toSaveEntity(bOardDTO);
        boardRepository.save(saveEntity);

    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for (BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }
}
