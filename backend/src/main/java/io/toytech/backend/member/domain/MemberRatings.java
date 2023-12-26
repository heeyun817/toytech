package io.toytech.backend.member.domain;


import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRatings {

  private Long likesGivenCount;

  private Long dislikesGivenCount;

  private Double totalEvalScore;

  private Double totalLikesScore;

  @Builder
  public MemberRatings(Long likesGivenCount, Long dislikesGivenCount, Double totalEvalScore,
      Double totalLikesScore) {
    this.likesGivenCount = likesGivenCount;
    this.dislikesGivenCount = dislikesGivenCount;
    this.totalEvalScore = totalEvalScore;
    this.totalLikesScore = totalLikesScore;
  }
}
