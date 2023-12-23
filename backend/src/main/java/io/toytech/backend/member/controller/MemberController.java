package io.toytech.backend.member.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import io.toytech.backend.member.domain.Member;
import io.toytech.backend.member.service.MemberService;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
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

  @GetMapping()
  public List<Member> findAll() {
    return memberService.findAll();
  }

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

//  @GetMapping("/{name}")
//  public List<Member> findByName(@PathVariable("name") String name) {
//    return memberService.findByName(name);
//  }

  @PostMapping()
  public ResponseEntity<Object> create(@RequestBody Member member) {
    Member savedMember = memberService.create(member);
    URI location = linkTo(MemberController.class).slash(savedMember.getId()).toUri();
    return ResponseEntity.created(location).build();
  }
}
