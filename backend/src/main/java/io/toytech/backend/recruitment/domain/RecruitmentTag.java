package io.toytech.backend.recruitment.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.ToString;

@Entity
@Table(name = "recruitment_tag")
@Getter
@ToString
public class RecruitmentTag {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false)
  private long id;

  @ManyToOne
  @JoinColumn(name = "recruitment_id")
  private Recruitment recruitment;

  @ManyToOne
  @JoinColumn(name = "tag_id")
  private Tag tag;

}

