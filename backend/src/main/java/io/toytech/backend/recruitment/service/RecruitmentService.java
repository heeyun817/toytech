package io.toytech.backend.recruitment.service;

import io.toytech.backend.recruitment.domain.Recruitment;

public interface RecruitmentService {
  Iterable<Recruitment> findAll();

}