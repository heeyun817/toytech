package io.toytech.backend.recruitment.service;

import io.toytech.backend.recruitment.domain.Recruitment;
import io.toytech.backend.recruitment.domain.RecruitmentTag;
import io.toytech.backend.recruitment.domain.Tag;
import io.toytech.backend.recruitment.repository.RecruitmentRepository;
import io.toytech.backend.recruitment.repository.RecruitmentTagRepository;
import io.toytech.backend.recruitment.repository.TagRepository;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecruitmentServiceImpl implements RecruitmentService{

  private final RecruitmentRepository recruitmentRepository;
  private final RecruitmentTagRepository recruitmentTagRepository;
  private final TagRepository tagRepository;


  @Autowired
  public RecruitmentServiceImpl(RecruitmentRepository recruitmentRepository, RecruitmentTagRepository recruitmentTagRepository, TagRepository tagRepository) {
    this.recruitmentRepository = recruitmentRepository;
    this.recruitmentTagRepository = recruitmentTagRepository;
    this.tagRepository = tagRepository;
  }

  // 전체 글 조회
  @Override
  public Map<Recruitment, List<Tag>> findAll() {
    Iterable<Recruitment> allRecruitments = recruitmentRepository.findAll();

    Map<Recruitment, List<Tag>> recruitmentTagMap = new HashMap<>();

    for (Recruitment recruitment : allRecruitments) {
      long recruitmentId = recruitment.getId();
      List<RecruitmentTag> tagIds = recruitmentTagRepository.findByRecruitmentId(recruitmentId);
      List<Tag> tags = new ArrayList<>();
      for (RecruitmentTag tagId : tagIds) {
        Tag tag = tagId.getTag();
        tags.add(tag);
      }
      recruitmentTagMap.put(recruitment, tags);
    }
    return recruitmentTagMap;
  }

  // 게시글 id별 조회
  @Override
  public Map<Recruitment, List<Tag>> findById(Long Id) {
    Optional<Recruitment> optionalRecruitment = recruitmentRepository.findById(Id);
    Recruitment recruitment = optionalRecruitment.get();
    Map<Recruitment, List<Tag>> recruitmentTagMap = new HashMap<>();
    List<RecruitmentTag> tagIds = recruitmentTagRepository.findByRecruitmentId(Id);
    List<Tag> tags = new ArrayList<>();
    for (RecruitmentTag tagId : tagIds) {
      Tag tag = tagId.getTag();
      tags.add(tag);
    }
    recruitmentTagMap.put(recruitment, tags);
    return recruitmentTagMap;
  }

}
