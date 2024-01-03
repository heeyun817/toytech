package io.toytech.backend.domain.member.dto;

import io.toytech.backend.domain.member.constant.Status;
import io.toytech.backend.domain.member.domain.Member;

public record MemberCreateRequest(
    String email,
    String name,
    String password
) {

  public Member toEntity() {
    return Member.builder()
        .email(email)
        .name(name)
        .password(password)
        .status(Status.MEMBER)
        .build();
  }
}
