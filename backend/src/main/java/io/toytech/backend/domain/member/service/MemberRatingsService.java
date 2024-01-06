package io.toytech.backend.domain.member.service;

import io.toytech.backend.domain.member.domain.Member;
import io.toytech.backend.domain.member.domain.MemberRatings;

public interface MemberRatingsService {

  MemberRatings create(Member member);

  void likes(Long id);

  void dislikes(Long id);
}
