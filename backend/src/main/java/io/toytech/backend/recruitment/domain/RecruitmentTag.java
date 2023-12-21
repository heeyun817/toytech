package io.toytech.backend.recruitment.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "recruitment_tag")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecruitmentTag {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false)
  private long id;

  @ToString.Exclude
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "recruitment_id")
  private Recruitment recruitment;

  @ToString.Exclude
  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "tag_id")
  private Tag tag;

}

