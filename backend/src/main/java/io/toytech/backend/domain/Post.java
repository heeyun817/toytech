package io.toytech.backend.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Post extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "post_id")
  private Long id;

  private String title;

  private String description;

//  private Member author;

  @OneToMany(mappedBy = "post")
  private List<Image> imageList = new ArrayList<>();

  private boolean isDeleted = false;

  @OneToMany(mappedBy = "post")
  private Set<Skill> skills = new HashSet<>();

  private float score = 0.0f;

  private int views = 0;

  private int commentCnt = 0;

  @Builder
  public Post(String title, String description, boolean isDeleted,
      float score, int views, int commentCnt) {
    this.title = title;
    this.description = description;
    this.isDeleted = isDeleted;
    this.score = score;
    this.views = views;
    this.commentCnt = commentCnt;
  }
}
