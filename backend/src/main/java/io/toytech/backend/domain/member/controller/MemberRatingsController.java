package io.toytech.backend.domain.member.controller;

import io.toytech.backend.domain.member.service.MemberRatingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member-ratings")
public class MemberRatingsController {

  private final MemberRatingsService memberRatingsService;

  @Autowired
  public MemberRatingsController(MemberRatingsService memberRatingsService) {
    this.memberRatingsService = memberRatingsService;
  }

  @PatchMapping("/{id}/likes")
  public void likes(@PathVariable("id") Long id) {
    memberRatingsService.likes(id);
  }

  @PatchMapping("/{id}/dislikes")
  public void like(@PathVariable("id") Long id) {
    memberRatingsService.dislikes(id);
  }


}
