package io.toytech.backend.domain.recruitment.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import io.toytech.backend.domain.recruitment.domain.Recruitment;
import io.toytech.backend.domain.recruitment.domain.Tag;
import io.toytech.backend.domain.recruitment.dto.RecruitmentRq;
import io.toytech.backend.domain.recruitment.dto.RecruitmentRs;
import io.toytech.backend.domain.recruitment.service.RecruitmentService;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
@RequiredArgsConstructor
public class RecruitmentController {

  private final RecruitmentService service;

  // 모든 글 조회 (최신순 :createAt, 조회순:view, 댓글순:comment)
  // 검색
  @GetMapping("/recruitments")
  public List<EntityModel<RecruitmentRs>> getAllRecruitments(
      @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Direction.DESC) Pageable pageable,
      @RequestParam(required = false) String keyword,
      @RequestParam(required = false) Boolean active) {
    List<RecruitmentRs> recruitments;
    if (keyword == null) {
      recruitments = service.findAll(pageable, active);
    }
    else  recruitments = service.search(pageable,keyword,active);

    return recruitments.stream()
        .map(recruitment -> {
          EntityModel<RecruitmentRs> entityModel = EntityModel.of(recruitment);
          WebMvcLinkBuilder link = linkTo(methodOn(getClass()).getRecruitmentById(recruitment.getId()));
          entityModel.add(link.withSelfRel());
          return entityModel;
        }).toList();
  }

  // 특정 글 조회
  @GetMapping("/recruitments/{id}")
  public RecruitmentRs getRecruitmentById(@PathVariable Long id) {
    service.updateView(id);
    return service.findById(id);
  }

  // 글 작성
  @PostMapping("/recruitments")
  public RecruitmentRs createRecruitment(@RequestBody RecruitmentRq recruitmentRq) {
    Recruitment recruitment = recruitmentRq.getRecruitment();
    Set<Tag> tags = recruitmentRq.getTags();

    return service.createRecruitment(recruitment, tags);
  }

  // 글 수정
  @PutMapping("/recruitments/{id}")
  public ResponseEntity<RecruitmentRs> updateRecruitment(@PathVariable Long id, @RequestBody RecruitmentRq recruitmentRq) {
    try {
      RecruitmentRs updatedRecruitment = service.updateRecruitment(id, recruitmentRq.getRecruitment(), recruitmentRq.getTags());
      return ResponseEntity.ok(updatedRecruitment);
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  // 글 삭제
  @DeleteMapping("/recruitments/{id}")
  public ResponseEntity<String> deleteRecruitment(@PathVariable Long id) {
    try {
      service.deleteRecruitment(id);
      return ResponseEntity.ok("성공적으로 삭제되었습니다");
    } catch (EntityNotFoundException e) {
      return ResponseEntity.notFound().build();
    }
  }

  // 태그 검색
  @GetMapping("/recruitments/tagSearch")
  public List<RecruitmentRs> getRecruitmentByTag(
      @PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Direction.DESC) Pageable pageable,
      @RequestParam String tag,
      @RequestParam(required = false) Boolean active) {
    return service.findByTag(tag,pageable, active);
  }




  // 검색
//  @GetMapping("/recruitments/search")
//  public Map<Recruitment, List<Tag>> searchRecruitmentByTitle(@RequestParam String keyword) {
//    return service.search(keyword);
//  }

}