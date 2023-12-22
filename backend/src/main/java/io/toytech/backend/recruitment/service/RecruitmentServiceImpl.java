package io.toytech.backend.recruitment.service;

import io.toytech.backend.recruitment.domain.Recruitment;
import io.toytech.backend.recruitment.domain.RecruitmentTag;
import io.toytech.backend.recruitment.domain.Tag;
import io.toytech.backend.recruitment.repository.RecruitmentRepository;
import io.toytech.backend.recruitment.repository.RecruitmentTagRepository;
import io.toytech.backend.recruitment.repository.TagRepository;
import jakarta.transaction.Transactional;
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

  // 전체 글 조회 (최신순)
  @Override
  public Map<Recruitment, List<Tag>> findAll() {
    Iterable<Recruitment> allRecruitments = recruitmentRepository.findAllByOrderByCreatedAtDesc();

    Map<Recruitment, List<Tag>> recruitmentTagMap = new LinkedHashMap<>();

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

  // 전체 글 조회 (조회순)
  @Override
  public Map<Recruitment, List<Tag>> findAllByView() {
    Iterable<Recruitment> allRecruitments = recruitmentRepository.findAllByOrderByViewDesc();

    Map<Recruitment, List<Tag>> recruitmentTagMap = new LinkedHashMap<>();

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
  public Map<Recruitment, List<Tag>> findById(Long id) {
    Optional<Recruitment> optionalRecruitment = recruitmentRepository.findById(id);
    Recruitment recruitment = optionalRecruitment.get(); //java.util.NoSuchElementException
    Map<Recruitment, List<Tag>> recruitmentTagMap = new HashMap<>();
    List<RecruitmentTag> tagIds = recruitmentTagRepository.findByRecruitmentId(id);
    List<Tag> tags = new ArrayList<>();
    for (RecruitmentTag tagId : tagIds) {
      Tag tag = tagId.getTag();
      tags.add(tag);
    }
    recruitmentTagMap.put(recruitment, tags);
    return recruitmentTagMap;
  }

  // 글 작성
  // 글, tag 입력 받음 (List or Set)
  // 글 save (Recruitment recruitment = recruitmentRepository.save();)
  // tag입력 받은 것들(Set<TagDto> tagDto) 중에서 for문으로 (for (TagDto tag : tagDto))
  // Tag tags=tagRepository.findByname() tag이름 있는지 확인
  // if 없으면 (isEmpty())
  //  tagRepository.save
  //  RecruitmentTagRepository.save (새로 추가된 tagId랑 추가하는 게시글)
  // else 있으면
  //  RecruitmentTagRepository.save(해당 tags랑 추가하는 게시글)

  // 조회수 증가
  @Override
  @Transactional
  public int updateView(Long id){
    return recruitmentRepository.updateView(id);
  }


}
