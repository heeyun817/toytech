package io.toytech.backend.member.constant;

import lombok.Getter;

@Getter
public enum Status {
  ADMIN("A"), // 관리자
  MEMBER("M"), // 일반 회원
  GUEST("G"), // 게스트
  BANNED("B"), // 정지된 회원
  DELETED("D"), // 삭제된 회원
  INACTIVE("I"); // 휴면 회원

  private final String code;

  Status(String code) {
    this.code = code;
  }

}
