package com.example.demo.repository;

import com.example.demo.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    // update board_table set board_hits = board_hits+1 where id = ?
    @Modifying
    @Query(value = "update BoardEntity b set b.boardHits = b.boardHits+1 where b.id =:id")
    void updateHits(@Param("id") Long id);
}
