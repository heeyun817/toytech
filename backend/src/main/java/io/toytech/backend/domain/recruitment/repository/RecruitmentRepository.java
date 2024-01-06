package io.toytech.backend.domain.recruitment.repository;

import io.toytech.backend.domain.recruitment.domain.Recruitment;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

  Optional<Recruitment> findById(long id);
  @Modifying
  @Query("update Recruitment r set r.view = r.view + 1 where r.id = :id")
  int updateView(Long id);

  Page<Recruitment> findAllByActive(Pageable pageable, Boolean active);

  Page<Recruitment> findByTitleContainingAndActive(String keyword, Pageable pageable, Boolean active);

  Page<Recruitment> findByTitleContaining(String keyword, Pageable pageable);

  Page<Recruitment> findByRecruitmentTagsTagIdAndActive(long id, Pageable pageable, Boolean active);

  Page<Recruitment> findByRecruitmentTagsTagId(long id, Pageable pageable);
}