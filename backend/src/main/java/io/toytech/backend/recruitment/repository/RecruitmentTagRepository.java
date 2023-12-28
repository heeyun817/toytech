package io.toytech.backend.recruitment.repository;

import io.toytech.backend.recruitment.domain.RecruitmentTag;
import io.toytech.backend.recruitment.domain.Tag;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentTagRepository extends JpaRepository<RecruitmentTag,Long> {
  List<RecruitmentTag> findByRecruitmentId(long recruitmentId);

  Page<RecruitmentTag> findByTagOrderByRecruitmentCreatedAtDesc(Tag tag, Pageable pageable);

  Page<RecruitmentTag> findByTagOrderByRecruitmentViewDesc(Tag tag, Pageable pageable);
}
