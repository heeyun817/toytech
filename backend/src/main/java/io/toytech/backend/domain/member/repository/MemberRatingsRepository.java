package io.toytech.backend.domain.member.repository;

import io.toytech.backend.domain.member.domain.Member;
import io.toytech.backend.domain.member.domain.MemberRatings;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRatingsRepository extends JpaRepository<MemberRatings, Member> {


  Optional<MemberRatings> findById(Long id);
}
