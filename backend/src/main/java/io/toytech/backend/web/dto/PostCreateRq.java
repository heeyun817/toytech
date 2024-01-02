package io.toytech.backend.web.dto;

import io.toytech.backend.domain2.Post;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostCreateRq {

  private String title;
  private String content;

  @Builder
  public PostCreateRq(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public Post toEntity() {
    return Post.builder()
        .title(title)
        .content(content).build();
  }
}
