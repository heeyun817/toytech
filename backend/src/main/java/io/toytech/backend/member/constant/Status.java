package io.toytech.backend.member.constant;

public enum Status {
  ADMIN("관리자"),
  MEMBER("일반회원"),
  GUEST("게스트"),
  BANNED("정지"),
  DELETED("탈퇴"),
  INACTIVE("휴면");


  private final String description;

  Status(String description) {
    this.description = description;
  }
}
