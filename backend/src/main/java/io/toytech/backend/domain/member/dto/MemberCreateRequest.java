package io.toytech.backend.domain.member.dto;

import io.toytech.backend.domain.member.domain.Member;
import jakarta.validation.constraints.Email;

public record MemberCreateRequest(
    @Email(message = "이메일 형식에 맞지 않습니다.")
    String email,
    String name,
    String password
) {

  public Member toEntity() {
    return Member.builder()
        .email(email)
        .name(name)
        .password(password)
        .build();
  }
}
