package io.toytech.backend.recruitment.repository;

import io.toytech.backend.recruitment.domain.Tag;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {

}
