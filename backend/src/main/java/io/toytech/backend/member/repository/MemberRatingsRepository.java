package io.toytech.backend.member.repository;

import io.toytech.backend.member.domain.Member;
import io.toytech.backend.member.domain.MemberRatings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRatingsRepository extends JpaRepository<MemberRatings, Member> {

}
