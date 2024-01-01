package io.toytech.backend.member.constant;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

/**
 * Status 열거형을 수정, 삭제해도 DB에 영향 받지 않게 하기 위한 JPA Converter
 */
@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {

  /**
   * @param status 변환할 엔티티 속성 값
   * @return 데이터베이스 열 값
   */
  @Override
  public String convertToDatabaseColumn(Status status) {
    if (status == null) {
      return null;
    }
    return status.getCode();
  }

  /**
   * @param code 변환할 데이터베이스 열의 데이터
   * @return 엔티티 속성 값
   */
  @Override
  public Status convertToEntityAttribute(String code) {
    if (code == null) {
      return null;
    }
    return Stream.of(Status.values())
        .filter(st -> st.getCode().equals(code))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
