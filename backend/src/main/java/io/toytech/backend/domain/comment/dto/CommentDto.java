package io.toytech.backend.domain.comment.dto;

import io.toytech.backend.domain.board.dto.BoardDto;
import io.toytech.backend.domain.comment.domain.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

  private Long id;

  private String text; //댓글 내용

  private BoardDto boardDto;

  public CommentDto(Comment comment) {
    this.id = comment.getId();
    this.text = comment.getText();
    this.boardDto = new BoardDto(comment.getBoard());
  }
}