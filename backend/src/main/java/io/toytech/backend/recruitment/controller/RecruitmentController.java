package io.toytech.backend.recruitment.controller;

import io.toytech.backend.recruitment.domain.Recruitment;
import io.toytech.backend.recruitment.domain.Tag;
import io.toytech.backend.recruitment.dto.RecruitmentDto;
import io.toytech.backend.recruitment.service.RecruitmentService;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.persistence.EntityNotFoundException;

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

  // 글 작성
  @PostMapping("/recruitments")
  public Map<Recruitment, List<Tag>> createRecruitment(@RequestBody RecruitmentDto recruitmentDto) {
    Recruitment recruitment = recruitmentDto.getRecruitment();
    Set<Tag> tags = recruitmentDto.getTags();

    return service.createRecruitment(recruitment, tags);
  }

  // 글 수정
  @PutMapping("/recruitments/{id}")
  public ResponseEntity<Map<Recruitment, List<Tag>>> updateRecruitment(@PathVariable Long id, @RequestBody RecruitmentDto recruitmentDto) {
    try {
      Map<Recruitment, List<Tag>> updatedRecruitment = service.updateRecruitment(id, recruitmentDto.getRecruitment(), recruitmentDto.getTags());
      return new ResponseEntity<>(updatedRecruitment, HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // 글 삭제
  @DeleteMapping("/recruitments/{id}")
  public ResponseEntity<String> deleteRecruitment(@PathVariable Long id) {
    try {
      service.deleteRecruitment(id);
      return new ResponseEntity<>("성공적으로 삭제되었습니다", HttpStatus.OK);
    } catch (EntityNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  // 검색
  @GetMapping("/recruitments/search")
  public Map<Recruitment, List<Tag>> searchRecruitmentByTitle(@RequestParam String keyword) {
    return service.search(keyword);
  }

}