package io.toytech.backend.member.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import io.toytech.backend.domain.member.constant.Grade;
import io.toytech.backend.domain.member.domain.Address;
import io.toytech.backend.domain.member.domain.Member;
import io.toytech.backend.domain.member.repository.MemberRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MemberRepositoryTest {

  private final MemberRepository memberRepository;

  @Autowired
  public MemberRepositoryTest(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @BeforeEach
  void setUp() {
    Address address = Address.builder()
        .state("경기도")
        .city("부천시")
        .street("신흥로 000-0")
        .zipCode("00000")
        .build();

    memberRepository.save(Member.builder()
        .email("test@test")
        .password("test")
        .name("상우")
        .dateBirth(LocalDateTime.now())
        .address(address)
        .grade(Grade.MEMBER)
        .build());
  }


  @Test
  @DisplayName("멤버 이름으로 검색이 정확한지 테스트 한다.")
  void testFindByName() {
    // 테스트 사례 1: 기존 이름 검색
    List<Member> result1 = memberRepository.findByName("상우");
    assertThat(result1).hasSize(1);
    assertThat(result1.get(0).getName()).isEqualTo("상우");

    // 테스트 사례 2: 일치하는 멤버가 없는 이름 검색
    List<Member> result2 = memberRepository.findByName("우상");
    assertEquals(0, result2.size());

    // 테스트 사례 3: 빈 이름 검색
    List<Member> result3 = memberRepository.findByName("");
    assertEquals(0, result3.size());

    // 테스트 사례 4: null 이름 검색
    List<Member> result4 = memberRepository.findByName(null);
    assertEquals(0, result4.size());
  }

}