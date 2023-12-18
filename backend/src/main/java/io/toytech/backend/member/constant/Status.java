package io.toytech.backend.member.constant;

public enum Status {
  ADMIN("A"),
  MEMBER("M"),
  GUEST("G"),
  BANNED("B"),
  DELETED("D"),
  INACTIVE("I");

  private final String code;

  Status(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
