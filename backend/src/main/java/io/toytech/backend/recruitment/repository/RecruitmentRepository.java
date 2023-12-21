package io.toytech.backend.recruitment.repository;

import io.toytech.backend.recruitment.domain.Recruitment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
  Optional<Recruitment> findById(long id);
}