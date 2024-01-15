package io.toytech.backend.domain.recruitment.dto;

import io.toytech.backend.domain.member.domain.Member;
import io.toytech.backend.domain.recruitment.domain.Recruitment;
import io.toytech.backend.domain.recruitment.domain.Tag;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RecruitmentRs {
  private long id;
  private String title;
  private String content;
  private Member member;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private boolean active;
  private int view;
  private Set<Tag> tags;
  private int comment;

  public RecruitmentRs(Recruitment recruitment, Set<Tag> tags) {
    this.id = recruitment.getId();
    this.title = recruitment.getTitle();
    this.content = recruitment.getContent();
    this.member = recruitment.getMember();
    this.createdAt = recruitment.getCreatedAt();
    this.updatedAt = recruitment.getUpdatedAt();
    this.active = recruitment.isActive();
    this.view = recruitment.getView();
    this.tags = tags;
    this.comment = recruitment.getComment();
  }
}
