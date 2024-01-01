package io.toytech.backend.member.temp;

import io.toytech.backend.member.constant.Status;
import io.toytech.backend.member.domain.Address;
import io.toytech.backend.member.domain.Member;
import io.toytech.backend.member.service.MemberService;
import java.time.LocalDateTime;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberProvider {

  private static final int COUNT = 3;

  @Bean
  public ApplicationRunner test(MemberService memberService) {
    return args -> {

      for (int i = 0; i < COUNT; i++) {
        Address address = Address.builder()
            .state("경기도")
            .city("부천시")
            .street("신흥로 000-0")
            .zipCode("0000" + i)
            .build();

        memberService.create(Member.builder()
            .email("test" + i + "@test")
            .password("test" + i)
            .name("상우" + i)
            .dateBirth(LocalDateTime.now())
            .address(address)
            .status(Status.MEMBER)
            .build());
      }

    };
  }
}
