package io.toytech.backend.recruitment.repository;

import io.toytech.backend.recruitment.domain.Recruitment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {

  List<Recruitment> findAllByOrderByCreatedAtDesc();
  Optional<Recruitment> findById(long id);
  @Modifying
  @Query("update Recruitment r set r.view = r.view + 1 where r.id = :id")
  int updateView(Long id);

//  @Query("SELECT r FROM Recruitment r ORDER BY r.view DESC")
//  Optional<Recruitment> findAllByView();

}