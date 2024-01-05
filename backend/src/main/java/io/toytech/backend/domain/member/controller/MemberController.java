package io.toytech.backend.domain.member.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import io.toytech.backend.domain.member.domain.Member;
import io.toytech.backend.domain.member.dto.MemberCreateRequest;
import io.toytech.backend.domain.member.dto.MemberRs;
import io.toytech.backend.domain.member.service.MemberService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/members")
public class MemberController {

  private final MemberService memberService;

  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }


  /**
   * 모든 엔터티를 검색한다.
   *
   * @return 엔터티 모델 목록
   */
  @GetMapping()
  public List<EntityModel<Member>> findAll() {
    return memberService.findAll()
        .stream()
        .map(member -> {
          EntityModel<Member> entityModel = EntityModel.of(member);
          WebMvcLinkBuilder link = linkTo(methodOn(getClass()).findById(member.getId()));
          entityModel.add(link.withSelfRel());
          return entityModel;
        }).toList();
  }

  /**
   * ID로 특정 엔터티를 검색한다.
   *
   * @param id 검색할 엔터티의 ID
   * @return 검색된 엔터티를 포함하는 EntityModel
   */
  @GetMapping("/{id}")
  public EntityModel<Member> findById(@PathVariable("id") Long id) {
    Optional<Member> member = memberService.findById(id);
    if (member.isEmpty()) {
      throw new IllegalArgumentException("선택한 회원을 찾을 수 없습니다.");
    }
    EntityModel<Member> entityModel = EntityModel.of(member.get());
    WebMvcLinkBuilder link = linkTo(methodOn(getClass()).findAll());
    entityModel.add(link.withRel("all-members"));

    return entityModel;
  }

  /**
   * 지정된 MemberCreateRequest 개체를 처리하여 새 회원을 만든다.
   *
   * @param request 생성된 회원의 정보가 포함된 MemberCreateRequest 객체
   * @return 새로 생성된 MemberRs 객체를 포함하는 ResponseEntity 객체
   */
  @PostMapping()
  public ResponseEntity<MemberRs> create(@RequestBody @Valid MemberCreateRequest request) {
    MemberRs memberRs = memberService.createDto(request);
    Link link = linkTo(getClass()).slash(memberRs.getId()).withSelfRel();
    return ResponseEntity.created(link.toUri()).body(memberRs);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
    memberService.delete(id);
    return ResponseEntity.noContent().build();
  }


}
