package io.toytech.backend.recruitment.controller;

import io.toytech.backend.recruitment.domain.Recruitment;
import io.toytech.backend.recruitment.domain.Tag;
import io.toytech.backend.recruitment.service.RecruitmentService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecruitmentController {

  private final RecruitmentService service;
  @Autowired
  public RecruitmentController(RecruitmentService service) {
    this.service = service;
  }

  // 모든 글 조회
  @GetMapping("/recruitments")
  public Map<Recruitment, List<Tag>> getAllRecruitments() {
    return service.findAll();
  }



}