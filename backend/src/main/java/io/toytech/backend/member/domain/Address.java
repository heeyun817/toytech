package io.toytech.backend.member.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

  /**
   * 상세주소 (예: 신흥로 000-0)
   */
  private String street;

  /**
   * 도시 (예: 부천시)
   */
  private String city;

  /**
   * 도 (예: 경기도)
   */
  private String state;

  /**
   * 우편번호
   */
  private String zipCode;
}
