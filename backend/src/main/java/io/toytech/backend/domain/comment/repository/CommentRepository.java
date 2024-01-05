package io.toytech.backend.domain.comment.repository;

import io.toytech.backend.domain.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
