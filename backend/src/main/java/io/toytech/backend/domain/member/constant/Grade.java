package io.toytech.backend.domain.member.constant;

import lombok.Getter;

@Getter
public enum Grade {
  ADMIN("A"), // 관리자
  MEMBER("M"), // 일반 회원
  BANNED("B"); // 정지된 회원

  private final String code;

  Grade(String code) {
    this.code = code;
  }

}
