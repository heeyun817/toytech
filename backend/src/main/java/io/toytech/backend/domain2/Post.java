package io.toytech.backend.domain2;

import static jakarta.persistence.GenerationType.IDENTITY;

import io.toytech.backend.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
import org.hibernate.annotations.SQLDelete;

@Entity
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE post SET is_deleted=true WHERE post_id=?")
@Getter
public class Post extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "post_id")
  private Long id;

  private String title;

  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @OneToMany(mappedBy = "post")
  private final List<Image> imageList = new ArrayList<>();

  private boolean isDeleted = false;

  @OneToMany(mappedBy = "post")
  private final Set<Skill> skills = new HashSet<>();

  private float score = 0.0f;

  private int views = 0;

  private int commentCnt = 0;

  @Builder
  public Post(String title, String content, boolean isDeleted,
      float score, int views, int commentCnt) {
    this.title = title;
    this.content = content;
    this.isDeleted = isDeleted;
    this.score = score;
    this.views = views;
    this.commentCnt = commentCnt;
  }

  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }

}
