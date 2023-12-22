package io.toytech.backend.recruitment.controller;

import io.toytech.backend.recruitment.domain.Recruitment;
import io.toytech.backend.recruitment.domain.Tag;
import io.toytech.backend.recruitment.service.RecruitmentService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  // 모든 글 조회 (최신순)
  @GetMapping("/recruitments")
  public Map<Recruitment, List<Tag>> getAllRecruitments() {
    return service.findAll();
  }

  // 모든 글 조회 (조회순)
  @GetMapping("/recruitments/view")
  public Map<Recruitment, List<Tag>> getAllRecruitmentsByView() {
    return service.findAllByView();
  }

  // 특정 글 조회
  @GetMapping("/recruitments/{id}")
  public Map<Recruitment, List<Tag>> getRecruitmentById(@PathVariable Long id) {
    service.updateView(id);
    return service.findById(id);
  }

}