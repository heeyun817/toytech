package io.toytech.backend.recruitment.repository;

import io.toytech.backend.recruitment.domain.RecruitmentTag;
import io.toytech.backend.recruitment.domain.Tag;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentTagRepository extends JpaRepository<RecruitmentTag,Long> {
  List<RecruitmentTag> findByRecruitmentId(long recruitmentId);

  List<RecruitmentTag> findByTag(Tag tag);
}
