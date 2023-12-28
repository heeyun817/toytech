package io.toytech.backend.member.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member_ratings")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRatings {

  @Id
  @Column(name = "id", updatable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  private Member member;

  @Column(name = "likes_given_count")
  private Long likesGivenCount;

  @Column(name = "dislikes_given_count")
  private Long dislikesGivenCount;

  @Column(name = "total_eval_score")
  private Double totalEvalScore;

  @Column(name = "total_likes_score")
  private Double totalLikesScore;

  @Builder
  public MemberRatings(Member member, Long likesGivenCount, Long dislikesGivenCount,
      Double totalEvalScore, Double totalLikesScore) {
    this.member = member;
    this.likesGivenCount = likesGivenCount;
    this.dislikesGivenCount = dislikesGivenCount;
    this.totalEvalScore = totalEvalScore;
    this.totalLikesScore = totalLikesScore;
  }
}
