package io.toytech.backend.recruitment.service;

import io.toytech.backend.recruitment.domain.Recruitment;
import io.toytech.backend.recruitment.domain.Tag;
import java.util.List;
import java.util.Map;

public interface RecruitmentService {
  // 전체 글 조회 (최신순)
  Map<Recruitment, List<Tag>> findAll();

  // 전체 글 조회 (조회순)
//  Map<Recruitment, List<Tag>> findAllByView();

  // 게시글 id별 조회
  Map<Recruitment, List<Tag>> findById(Long Id);

  // 조회수 증가
  int updateView(Long id);

}