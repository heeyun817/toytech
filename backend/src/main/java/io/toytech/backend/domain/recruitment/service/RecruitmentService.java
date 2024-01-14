package io.toytech.backend.domain.recruitment.service;

import io.toytech.backend.domain.recruitment.domain.Recruitment;
import io.toytech.backend.domain.recruitment.domain.Tag;
import io.toytech.backend.domain.recruitment.dto.RecruitmentRs;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.data.domain.Pageable;

public interface RecruitmentService {
  // 전체 글 조회
  List<RecruitmentRs> findAll(Pageable pageable, Boolean active);

  // 전체 글 조회 (조회순)
//  Map<Recruitment, List<Tag>> findAllByView(Pageable pageable);

  // 게시글 id별 조회
  RecruitmentRs findById(Long Id);

  // 글 작성
  RecruitmentRs createRecruitment(Recruitment recruitment, Set<Tag> tags);

  // 글 수정
  RecruitmentRs updateRecruitment(Long id, Recruitment recruitment, Set<Tag> tags);

  // 글 삭제
  void deleteRecruitment(Long id);

  // 조회수 증가
  int updateView(Long id);

  // 검색
  List<RecruitmentRs> search(Pageable pageable, String keyword, Boolean active);

  // 태그 검색
  List<RecruitmentRs> findByTag(String tagName, Pageable pageable, Boolean active);
}