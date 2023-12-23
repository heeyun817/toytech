package io.toytech.backend.member.service;

import io.toytech.backend.member.domain.Member;
import io.toytech.backend.member.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;

  @Autowired
  public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  @Override
  public Optional<Member> findById(Long id) {
    return memberRepository.findById(id);
  }

  @Override
  public List<Member> findAll() {
    return memberRepository.findAll();
  }

  @Override
  public Member create(Member member) {
    return memberRepository.save(member);
  }
}
