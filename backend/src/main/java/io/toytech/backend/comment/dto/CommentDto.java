package io.toytech.backend.comment.dto;

import io.toytech.backend.board.dto.BoardDto;
import io.toytech.backend.comment.domain.Comment;
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