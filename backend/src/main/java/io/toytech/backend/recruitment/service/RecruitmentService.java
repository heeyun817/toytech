package io.toytech.backend.recruitment.service;

import io.toytech.backend.recruitment.domain.Recruitment;
import io.toytech.backend.recruitment.domain.Tag;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.data.domain.Pageable;

public interface RecruitmentService {
  // 전체 글 조회
  Map<Recruitment, List<Tag>> findAll(Pageable pageable, String order, Boolean active);

  // 전체 글 조회 (조회순)
//  Map<Recruitment, List<Tag>> findAllByView(Pageable pageable);

  // 게시글 id별 조회
  Map<Recruitment, List<Tag>> findById(Long Id);

  // 글 작성
  Map<Recruitment, List<Tag>> createRecruitment(Recruitment recruitment, Set<Tag> tags);

  // 글 수정
  Map<Recruitment, List<Tag>> updateRecruitment(Long id, Recruitment recruitment, Set<Tag> tags);

  // 글 삭제
  void deleteRecruitment(Long id);

  // 조회수 증가
  int updateView(Long id);

  // 검색
  Map<Recruitment, List<Tag>> search(Pageable pageable, String keyword, String order, Boolean active);

  // 태그 검색
  Map<Recruitment, List<Tag>> findByTag(String tagName, Pageable pageable, String order);
}