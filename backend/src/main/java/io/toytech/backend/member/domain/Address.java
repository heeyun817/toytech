package io.toytech.backend.member.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Address {

  /**
   * 도 (예: 경기도)
   */
  private String state;
  /**
   * 도시 (예: 부천시)
   */
  private String city;
  /**
   * 상세주소 (예: 신흥로 000-0)
   */
  private String street;
  /**
   * 우편번호
   */
  private String zipCode;


  @Builder
  public Address(String state, String city, String street, String zipCode) {
    this.state = state;
    this.city = city;
    this.street = street;
    this.zipCode = zipCode;
  }
}
