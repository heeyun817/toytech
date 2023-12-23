package io.toytech.backend.member.temp;

import io.toytech.backend.member.constant.Status;
import io.toytech.backend.member.domain.Address;
import io.toytech.backend.member.domain.Member;
import io.toytech.backend.member.repository.MemberRepository;
import java.time.LocalDateTime;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemberProvider {

  @Bean
  public ApplicationRunner test(MemberRepository memberRepository) {
    return args -> {
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
          .status(Status.MEMBER)
          .build());
    };
  }
}
