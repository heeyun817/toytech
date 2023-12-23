package io.toytech.backend.member.service;

import io.toytech.backend.member.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberService {

  Optional<Member> findById(Long id);

  List<Member> findAll();

  Member create(Member member);

  void delete(Long id);
}
