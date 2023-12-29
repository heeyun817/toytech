package io.toytech.backend.controller;

import io.toytech.backend.service.PostService;
import io.toytech.backend.web.dto.PostCreateRq;
import io.toytech.backend.web.dto.PostDetailRs;
import io.toytech.backend.web.dto.PostRs;
import io.toytech.backend.web.dto.ReviewRs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final PostService postService;

  /*
  1. 프로젝트 등록
  2. 프로젝트 수정
  3. 프로젝트 삭제
  4. 프로젝트 리뷰
  5. 프로젝트 페이징 조회
   */

    @PostMapping("/project")
    public Long createPost(@RequestBody PostCreateRq request) {
        return postService.createPost(request);
    }

    @PutMapping ("/project/{postId}")
    public Long updatePost(@PathVariable Long postId, @RequestBody PostCreateRq request) {
        return postService.updatePost(postId, request);
    }

    @DeleteMapping("/project/{postId}")
    public String deletePost(@PathVariable Long postId) {
        return postService.deletePost(postId);
    }

    @GetMapping("/project/{postId}")
    public PostDetailRs findPost(@PathVariable Long postId) {
        return postService.findPost(postId);
    }

    @GetMapping("/project/{postId}/review")
    public ReviewRs findReview(@PathVariable Long postId) {
        return postService.findReview(postId);
    }

    @GetMapping("/project/list")
    public Page<PostRs> findPostList(
        @PageableDefault(size = 10, sort = "createDate", direction = Direction.DESC)
        Pageable pageable) {
        return postService.findPostList(pageable);
    }
}
