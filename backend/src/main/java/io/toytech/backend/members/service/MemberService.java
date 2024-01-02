package io.toytech.backend.members.service;

import io.toytech.backend.members.domain.Member;
import io.toytech.backend.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

  private final MemberRepository memberRepository;

  public Member findOneReturnEntity(Long memberId) {
    return memberRepository.findById(memberId).get();
  }

}
