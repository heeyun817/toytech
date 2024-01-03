package io.toytech.backend.domain.member.service;

import io.toytech.backend.domain.member.domain.Member;
import io.toytech.backend.domain.member.dto.MemberCreateRequest;
import io.toytech.backend.domain.member.dto.MemberRs;
import io.toytech.backend.domain.member.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;
  private final MemberRatingsService memberRatingsService;

  @Autowired
  public MemberServiceImpl(MemberRepository memberRepository,
      MemberRatingsService memberRatingsService) {
    this.memberRepository = memberRepository;
    this.memberRatingsService = memberRatingsService;
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
    Member savedMember = memberRepository.save(member);
    memberRatingsService.create(savedMember);
    return savedMember;
  }

  @Override
  public void delete(Long id) {
    memberRepository.deleteById(id);
  }

  @Override
  public MemberRs createDto(MemberCreateRequest request) {
    return MemberRs.builder()
        .member(memberRepository.save(request.toEntity()))
        .build();
  }
}
