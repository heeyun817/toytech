package io.toytech.backend.recruitment.repository;

import io.toytech.backend.recruitment.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag,Long> {

}
