package io.toytech.backend.domain.member.constant;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Stream;

/**
 * Status 열거형을 수정, 삭제해도 DB에 영향 받지 않게 하기 위한 JPA Converter
 */
@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Grade, String> {

  /**
   * @param grade 변환할 엔티티 속성 값
   * @return 데이터베이스 열 값
   */
  @Override
  public String convertToDatabaseColumn(Grade grade) {
    if (grade == null) {
      return null;
    }
    return grade.getCode();
  }

  /**
   * @param code 변환할 데이터베이스 열의 데이터
   * @return 엔티티 속성 값
   */
  @Override
  public Grade convertToEntityAttribute(String code) {
    if (code == null) {
      return null;
    }
    return Stream.of(Grade.values())
        .filter(st -> st.getCode().equals(code))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }
}
