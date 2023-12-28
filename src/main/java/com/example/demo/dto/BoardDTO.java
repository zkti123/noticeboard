package com.example.demo.dto;

import com.example.demo.entity.BoardEntity;
import com.example.demo.entity.BoardFileEntity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor //기본 생성자
@AllArgsConstructor //모든 필드를 매개변수로 하는 생성자
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

    private List<MultipartFile> boardFile;  //save.html -> controller 파일 담는 용도
    private List<String>  originalFileName;  // 원본 파일 이름
    private List<String>  storedFileName; // 서버 저장용 파일 이름
    private int fileAttached; // 파일 첨부 여부 (첨부1, 미첨부0)

    public BoardDTO(Long id, String boardWriter, String boardTitle, int boardHits, LocalDateTime boardCreatedTime) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardHits = boardHits;
        this.boardCreatedTime = boardCreatedTime;
    }


    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardPass(boardEntity.getBoardPass());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedtime());
//        if (boardEntity.getFileAttached() == 0) {
//            boardDTO.setFileAttached(boardEntity.getFileAttached());//0
//        } else {
//            boardDTO.setFileAttached(boardEntity.getFileAttached());//1
//            //파일 이름을 가져가야함
//            //originalFileName,sotredFileName : board_file_table(BoardFileEntity) 에 있음
//            // join
//            //select * from board_file_table b , board_file_table bf where b.id = bf.board_id
//            //and where b.id = ?
//            boardDTO.setOriginalFileName(boardEntity.getBoardFileEntityList().get(0).getOriginalFileName());
//            boardDTO.setStoredFileName(boardEntity.getBoardFileEntityList().get(0).getStoredFileName());
//        }


        Integer fileAttached = boardEntity.getFileAttached();
        if (fileAttached != null) {
            boardDTO.setFileAttached(fileAttached); // 파일 첨부 여부 설정

            if (fileAttached == 1) {
                List<String> originalFileNameList = new ArrayList<>();
                List<String> storedFileNameList = new ArrayList<>();
                for (BoardFileEntity boardFileEntity : boardEntity.getBoardFileEntityList()) {
                    originalFileNameList.add(boardFileEntity.getOriginalFileName());
                    storedFileNameList.add(boardFileEntity.getStoredFileName());
                }
                boardDTO.setOriginalFileName(originalFileNameList);
                boardDTO.setStoredFileName(storedFileNameList);
            }
        } else {
            boardDTO.setFileAttached(0); // 파일 첨부되지 않은 경우 0으로 설정
        }
//        if (boardEntity.getFileAttached() == 0) {
//            boardDTO.setFileAttached(boardEntity.getFileAttached());//0
//        } else {
//            List<String > originalFileNameList = new ArrayList<>();
//            List<String > storedFileNameList = new ArrayList<>();
//            boardDTO.setFileAttached(boardEntity.getFileAttached());//1
//            for (BoardFileEntity boardFileEntity : boardEntity.getBoardFileEntityList()) {
//                originalFileNameList.add(boardFileEntity.getOriginalFileName());
//                storedFileNameList.add(boardFileEntity.getStoredFileName());
//            }
//            boardDTO.setOriginalFileName(originalFileNameList);
//            boardDTO.setStoredFileName(storedFileNameList);
//        }

        return boardDTO;
    }


}
