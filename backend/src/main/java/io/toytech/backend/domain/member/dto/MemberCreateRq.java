package io.toytech.backend.domain.member.dto;

import io.toytech.backend.domain.member.constant.Status;
import io.toytech.backend.domain.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Data
@Setter(AccessLevel.NONE)
public class MemberCreateRq {

  private String email;
  private String name;
  private String password;

  @Builder
  public MemberCreateRq(String email, String name, String password) {
    this.email = email;
    this.name = name;
    this.password = password;
  }

  public Member toEntity() {
    return Member.builder()
        .email(email)
        .name(name)
        .password(password)
        .status(Status.MEMBER)
        .build();
  }
}
