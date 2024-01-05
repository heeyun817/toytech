package io.toytech.backend.domain.board.repository;

import io.toytech.backend.domain.board.constant.BoardType;
import io.toytech.backend.domain.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

  Page<Board> findByBoardType(BoardType boardType, Pageable pageable);
}

