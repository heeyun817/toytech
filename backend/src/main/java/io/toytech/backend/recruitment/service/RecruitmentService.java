package io.toytech.backend.recruitment.service;

import io.toytech.backend.recruitment.domain.Recruitment;
import io.toytech.backend.recruitment.domain.Tag;
import java.util.List;
import java.util.Map;

public interface RecruitmentService {
  Map<Recruitment, List<Tag>> findAll();

  Map<Recruitment, List<Tag>> findById(Long Id);

  int updateView(Long id);

}