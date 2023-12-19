package io.toytech.backend.recruitment.controller;

import io.toytech.backend.recruitment.domain.Recruitment;
import io.toytech.backend.recruitment.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecruitmentController {

  private final RecruitmentService service;
  @Autowired
  public RecruitmentController(RecruitmentService service) {
    this.service = service;
  }

  @GetMapping("/recruitments")
  public Iterable<Recruitment> getAllRecruitments() {
    return service.findAll();
  }

}