package io.toytech.backend.recruitment.domain;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
  @Column(name = "id", updatable = false)
  private long id;
  // 제목
  @Column(name = "title", nullable = false)
  private String title;
  // 본문
  @Column(name = "content", nullable = false, columnDefinition = "TEXT")
  private String content;

  // 작성자 -> 나중에 Member로 바꾸기
  //  @JoinColumn(name = "memberId", nullable = false, referencedColumnName = "id")   // foreign key (memberId) references Member (id)
  //  @ManyToOne(fetch = FetchType.LAZY)
  @Column(name = "memberId")
  private long member;

  // 태그
  @OneToMany(mappedBy = "recruitment")
  private Set<RecruitmentTag> recruitmentTags;

  // 작성일
  @CreationTimestamp
  @Column(name = "createdAt", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  // 수정일
  @UpdateTimestamp
  @Column(name = "updatedAt", nullable = false)
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

