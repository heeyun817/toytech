package io.toytech.backend.community.dto;


import io.toytech.backend.comment.dto.CommentDto;
import io.toytech.backend.community.constant.CommunityType;
import io.toytech.backend.community.domain.Community;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommunityDto {

  private Long id;

  private String title;
  private String content;

  private int views;
  private int likes;
  private int dislikes;

  private LocalDateTime createdAt;
  private LocalDateTime modifiedAt;

  @Enumerated(EnumType.STRING)
  private CommunityType communityType;


  //  private MemberDto memberDto;
  private String name; //글쓴이 이름 lazy 확인하려고

  private List<CommentDto> comments;


  public CommunityDto(Community community) {
    this.id = community.getId();
    this.title = community.getTitle();
    this.content = community.getContent();
    this.views = community.getViews();
    this.likes = community.getLikes();
    this.dislikes = community.getDislikes();
    this.createdAt = community.getCreatedAt();
    this.modifiedAt = community.getModifiedAt();
    this.communityType = community.getCommunityType();
//    this.memberDto = new MemberDto(community.getMember()); //lazy 로딩인지 확인해야 됨  ===> 무한 루프에 빠짐
    this.name = community.getMember().getName();
    this.comments = community.getComments().stream()
        .map(c -> new CommentDto(c)).collect(Collectors.toList());
  }
}
