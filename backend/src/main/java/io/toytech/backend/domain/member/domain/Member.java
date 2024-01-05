package io.toytech.backend.domain.member.domain;

import io.toytech.backend.domain.board.domain.Board;
import io.toytech.backend.domain.member.constant.Grade;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Past;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString.Include;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@SQLDelete(sql = "UPDATE member SET deleted = true WHERE id = ?")
//@Where(clause = "deleted = 'N'") jpa 최신버전은 Deprecated 되었다
//@FilterDef(name = "deletedProductFilter", parameters = @ParamDef(name = "isDeleted", type = "boolean"))
//@Filter(name = "deletedProductFilter", condition = "deleted = :isDeleted")
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {

  @Id
  @Include
  @EqualsAndHashCode.Include
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false)
  private Long id;

  @Include
  @Column(name = "email", nullable = false, unique = true, length = 50)
  private String email;

  @Column(name = "password", nullable = false, length = 50)
  private String password;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Past
  @Column(name = "date_birth")
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

  @Column(name = "grade", nullable = false, length = 1)
  private Grade grade;

  @Column(name = "deleted", nullable = false, length = 1)
  private boolean deleted;

  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
  private List<Board> boards;


  @Builder
  public Member(String email, String password, String name, LocalDateTime dateBirth,
      Address address, Grade grade) {
    this.email = email;
    this.password = password;
    this.name = name;
    this.dateBirth = dateBirth;
    this.address = address;
    this.grade = (grade == null) ? Grade.MEMBER : grade;
    this.deleted = false;
  }
}
