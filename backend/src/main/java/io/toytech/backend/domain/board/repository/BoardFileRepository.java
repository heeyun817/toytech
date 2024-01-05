package io.toytech.backend.domain.board.repository;

import io.toytech.backend.domain.board.domain.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardFileRepository extends JpaRepository<BoardFile, Long> {

}
