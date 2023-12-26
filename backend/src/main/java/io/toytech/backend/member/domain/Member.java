package io.toytech.backend.member.domain;

import io.toytech.backend.member.constant.Status;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"name", "email"})
public class Member {

  @Id
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  private Long id;

  @Email(message = "이메일 형식에 맞지 않습니다.")
  @Column(name = "email", nullable = false, unique = true, updatable = false, length = 50)
  private String email;

  @Column(name = "password", nullable = false, length = 50)
  private String password;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Past
  @Column(name = "date_birth", nullable = false)
  private LocalDateTime dateBirth;

  @Embedded
  @AttributeOverride(name = "street", column = @Column(name = "address_street", length = 100))
  @AttributeOverride(name = "city", column = @Column(name = "address_city", length = 50))
  @AttributeOverride(name = "state", column = @Column(name = "address_state", length = 50))
  @AttributeOverride(name = "zipCode", column = @Column(name = "address_zip_code", length = 10))
  private Address address;

  @CreationTimestamp(source = SourceType.DB)
  @Column(name = "create_at", nullable = false, updatable = false)
  private LocalDateTime createAt;

  @UpdateTimestamp(source = SourceType.DB)
  @Column(name = "update_at", nullable = false)
  private LocalDateTime updateAt;

  @Column(name = "status", nullable = false, length = 1)
  private Status status;

  @Embedded
  @AttributeOverride(name = "likesGivenCount", column = @Column(name = "likes_given_count"))
  @AttributeOverride(name = "dislikesGivenCount", column = @Column(name = "dislikes_given_count"))
  @AttributeOverride(name = "totalEvalScore", column = @Column(name = "total_eval_score"))
  @AttributeOverride(name = "totalLikesScore", column = @Column(name = "total_likes_score"))
  private MemberRatings memberRatings;

  @Builder
  public Member(String email, String password, String name, LocalDateTime dateBirth,
      Address address, Status status, MemberRatings memberRatings) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.dateBirth = dateBirth;
    this.address = address;
    this.status = status;
    this.memberRatings = memberRatings;
  }
}
