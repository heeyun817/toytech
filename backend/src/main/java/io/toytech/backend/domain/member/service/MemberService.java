package io.toytech.backend.domain.member.service;

import io.toytech.backend.domain.member.domain.Member;
import io.toytech.backend.domain.member.dto.MemberCreateRq;
import io.toytech.backend.domain.member.dto.MemberRs;
import java.util.List;
import java.util.Optional;

public interface MemberService {

  Optional<Member> findById(Long id);

  List<Member> findAll();

  Member create(Member member);

  void delete(Long id);

  MemberRs createDto(MemberCreateRq request);
}
