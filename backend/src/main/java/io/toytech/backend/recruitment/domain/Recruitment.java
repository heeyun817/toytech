package io.toytech.backend.recruitment.domain;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.*;

@Entity
@Table(name = "recruitment")
@Getter
@ToString
public class Recruitment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false)
  private long id;
  // 제목
  @Column(name = "title", nullable = false)
  private String title;
  // 본문
  @Column(name = "content", nullable = false)
  private String content;

  // 작성자 -> 나중에 Member로 바꾸기
  //  @JoinColumn(name = "memberId", nullable = false, referencedColumnName = "id")   // foreign key (memberId) references Member (id)
  //  @ManyToOne(fetch = FetchType.LAZY)
  @Column(name = "memberId")
  private long member;

  // 태그
  @OneToMany(mappedBy = "recruitment")
  private Set<RecruitmentTag> tags = new HashSet<>();

  // 작성일
  @CreationTimestamp
  @Column(name = "createAt", nullable = false, updatable = false)
  private LocalDateTime createAt;

  // 수정일
  @UpdateTimestamp
  @Column(name = "updateAt", nullable = false)
  private LocalDateTime updateAt;

  // 마감여부
  @Column(name = "active", nullable = false)
  private boolean active;

  // 조회수
  @Column(name = "views", nullable = false)
  private int views;

  // 댓글
  //  @ManyToOne(fetch = FetchType.LAZY)
//  @Column(name = "comment")
//  private List<Comment> comments;

}

