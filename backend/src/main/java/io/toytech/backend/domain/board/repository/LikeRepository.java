package io.toytech.backend.domain.board.repository;

import io.toytech.backend.domain.board.domain.Board;
import io.toytech.backend.domain.board.domain.Like;
import io.toytech.backend.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {

  Like findByBoardAndMember(Board board, Member member);

  boolean existsByBoardAndMember(Board board, Member member);
}
