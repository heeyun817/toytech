package io.toytech.backend.domain.recruitment.domain;

import io.toytech.backend.domain.member.domain.Member;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.*;

@Entity
@Table(name = "recruitment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Recruitment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "recruitment_id", updatable = false)
  private long id;
  // 제목
  @Column(name = "title", nullable = false)
  private String title;
  // 본문
  @Column(name = "content", nullable = false, columnDefinition = "TEXT")
  private String content;

  // 작성자
  @JoinColumn(name = "member_id")
  @ManyToOne(fetch = FetchType.LAZY)
  private Member member;

  // 태그
  @OneToMany(mappedBy = "recruitment")
  private Set<RecruitmentTag> recruitmentTags;

  // 작성일
  @CreationTimestamp
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  // 수정일
  @UpdateTimestamp
  @Column(name = "updated_at", nullable = false)
  private LocalDateTime updatedAt;

  // 마감여부
  @Column(name = "active", nullable = false)
  private boolean active;

  // 조회수
  @Column(name = "view",columnDefinition = "integer default 0", nullable = false)
  private int view;

  // 댓글
//  @OneToMany(mappedBy = "recruitment", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
//  private List<Comment> comments;
  @Column(name = "comment",columnDefinition = "integer default 0", nullable = false)
  private int comment;

  public void update(String title, String content, boolean active) {
    this.title = title;
    this.content = content;
    this.active = active;
  }

}

