package io.toytech.backend.member.dto;

import io.toytech.backend.member.domain.Member;
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
        .build();
  }
}
