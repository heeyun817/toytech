package io.toytech.backend.member.dto;

import io.toytech.backend.member.domain.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MemberRs {

  private Long id;
  private String email;
  private String name;

  @Builder
  public MemberRs(Member member) {
    this.id = member.getId();
    this.email = member.getEmail();
    this.name = member.getName();
  }

}
