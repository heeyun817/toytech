package io.toytech.backend.file.domain;


import io.toytech.backend.community.domain.Community;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FileRequest {

  @Id
  @GeneratedValue
  @Column(name = "file_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "community_id")
  private Community community;

  private String original_name;
  private String save_name;
  private long size;

  @Builder
  public FileRequest(String original_name, String save_name, long size) {
    this.original_name = original_name;
    this.save_name = save_name;
    this.size = size;
  }
}
