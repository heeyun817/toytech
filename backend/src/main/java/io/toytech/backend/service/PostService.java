package io.toytech.backend.service;

import io.toytech.backend.domain2.Post;
import io.toytech.backend.repository.PostRepository;
import io.toytech.backend.web.dto.PostCreateRq;
import io.toytech.backend.web.dto.PostDetailRs;
import io.toytech.backend.web.dto.PostRs;
import io.toytech.backend.web.dto.ReviewRs;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  public Long createPost(PostCreateRq request) {
    //멤버 정보 등록, 검증
    Post post = request.toEntity();
    return postRepository.save(post).getId();
  }

  @Transactional
  public Long updatePost(Long postId, PostCreateRq request) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + postId));

    post.update(request.getTitle(), request.getContent());
    return postId;
  }

  @Transactional
  public String deletePost(Long postId) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + postId));

    postRepository.delete(post);
    return "삭제되었습니다";
  }

  public PostDetailRs findPost(Long postId) {
    Post post = postRepository.findById(postId)
        .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + postId));

    return new PostDetailRs(post);
  }

  public ReviewRs findReview(Long postId) {
    return null;
  }

  public Page<PostRs> findPostList(Pageable pageable) {
    return null;
  }
}
