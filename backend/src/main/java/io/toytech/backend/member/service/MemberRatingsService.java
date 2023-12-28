package io.toytech.backend.member.service;

import io.toytech.backend.member.domain.Member;
import io.toytech.backend.member.domain.MemberRatings;

public interface MemberRatingsService {

  MemberRatings create(Member member);

}
