package io.toytech.backend.recruitment.dto;

import io.toytech.backend.recruitment.domain.Recruitment;
import io.toytech.backend.recruitment.domain.Tag;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecruitmentDto {
  private Recruitment recruitment;
  private Set<Tag> tags;

}
