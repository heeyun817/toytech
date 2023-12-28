package io.toytech.backend.member.service;

import io.toytech.backend.member.domain.Member;
import io.toytech.backend.member.domain.MemberRatings;
import io.toytech.backend.member.repository.MemberRatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberRatingsServiceImpl implements MemberRatingsService {

  private static final Long DEFAULT_LIKES = 1L;

  private final MemberRatingsRepository memberRatingsRepository;

  @Autowired
  public MemberRatingsServiceImpl(MemberRatingsRepository memberRatingsRepository) {
    this.memberRatingsRepository = memberRatingsRepository;
  }

  @Override
  public MemberRatings create(Member member) {
    MemberRatings memberRatings = MemberRatings
        .builder()
        .member(member)
        .likesGivenCount(0L)
        .dislikesGivenCount(0L)
        .totalEvalScore(0.0)
        .totalLikesScore(0.0)
        .build();
    return memberRatingsRepository.save(memberRatings);
  }

  @Override
  public void likes(Long id) {
    memberRatingsRepository
        .findById(id)
        .ifPresent(memberRatings -> {
          memberRatings.likes(DEFAULT_LIKES);
          memberRatingsRepository.save(memberRatings);
        });
  }

  @Override
  public void dislikes(Long id) {
    memberRatingsRepository
        .findById(id)
        .ifPresent(memberRatings -> {
          memberRatings.dislikes(DEFAULT_LIKES);
          memberRatingsRepository.save(memberRatings);
        });
  }
}
