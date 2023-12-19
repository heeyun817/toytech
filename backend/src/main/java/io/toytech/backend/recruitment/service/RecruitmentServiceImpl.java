package io.toytech.backend.recruitment.service;

import io.toytech.backend.recruitment.domain.Recruitment;
import io.toytech.backend.recruitment.repository.RecruitmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecruitmentServiceImpl implements RecruitmentService{

  private final RecruitmentRepository repository;

  @Autowired
  public RecruitmentServiceImpl(RecruitmentRepository repository) {
    this.repository = repository;
  }

  @Override
  public Iterable<Recruitment> findAll() {
    return repository.findAll();
  }
}
