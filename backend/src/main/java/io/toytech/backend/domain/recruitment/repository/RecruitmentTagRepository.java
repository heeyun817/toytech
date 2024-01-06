package io.toytech.backend.domain.recruitment.repository;

import io.toytech.backend.domain.recruitment.domain.RecruitmentTag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentTagRepository extends JpaRepository<RecruitmentTag,Long> {
  List<RecruitmentTag> findByRecruitmentId(long recruitmentId);
}
