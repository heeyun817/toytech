package io.toytech.backend.recruitment.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tag")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "name", nullable = false)
  private String name;

}
