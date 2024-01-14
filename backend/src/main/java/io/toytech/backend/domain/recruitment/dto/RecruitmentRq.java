package io.toytech.backend.domain.recruitment.dto;

import io.toytech.backend.domain.recruitment.domain.Recruitment;
import io.toytech.backend.domain.recruitment.domain.Tag;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruitmentRq {
  private Recruitment recruitment;
  private Set<Tag> tags;
}
