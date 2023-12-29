package io.toytech.backend.web.dto;

import io.toytech.backend.domain.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostDetailRs {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostDetailRs(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getMember().getName();
    }
}
