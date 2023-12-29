package io.toytech.backend.member.repository;

import io.toytech.backend.member.domain.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
  
  List<Member> findByName(String name);

  Member findByEmail(String email);


}
