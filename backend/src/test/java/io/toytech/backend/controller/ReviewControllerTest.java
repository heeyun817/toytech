package io.toytech.backend.controller;

import static org.assertj.core.api.Assertions.assertThat;

import io.toytech.backend.domain2.Post;
import io.toytech.backend.repository.PostRepository;
import io.toytech.backend.web.dto.PostCreateRq;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ReviewControllerTest {

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate restTemplate;

  @Autowired
  private PostRepository postRepository;

  @Autowired
  private EntityManagerFactory emf;

  public static void truncateTables(EntityManager em, String... tableNames) {
    if (tableNames.length == 0) {
      return;
    }

    em.getTransaction().begin();
    em.createNativeQuery("SET FOREIGN_KEY_CHECKS=0").executeUpdate();
    Arrays.stream(tableNames).forEach(
        (tableName) -> em.createNativeQuery("TRUNCATE TABLE " + tableName).executeUpdate());
    em.createNativeQuery("SET FOREIGN_KEY_CHECKS=1").executeUpdate();
    em.getTransaction().commit();
    em.close();
  }

  @BeforeEach
  void setup() throws IOException {
    EntityManager em = emf.createEntityManager();
    truncateTables(em, "post");
  }

  @Test
  public void Post_등록() throws Exception {
    //given
    String title = "title";
    String content = "content";
    PostCreateRq request = PostCreateRq.builder()
        .title(title)
        .content(content).build();
    String url = "http://localhost:" + port + "/project";

    //when
    ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, request,
        Long.class);

    //then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isGreaterThan(0L);

    List<Post> all = postRepository.findAll();
    assertThat(all.get(0).getTitle()).isEqualTo(title);
    assertThat(all.get(0).getContent()).isEqualTo(content);
  }

  @Test
  public void Post_수정() throws Exception {
    //given
    Post savedPost = Post.builder()
        .title("title")
        .content("content")
        .build();

    postRepository.save(savedPost);
    Long updateId = savedPost.getId();
    String title2 = "title2";
    String content2 = "content2";

    PostCreateRq request = PostCreateRq.builder()
        .title(title2)
        .content(content2).build();

    String url = "http://localhost:" + port + "/project/" + updateId;
    HttpEntity<PostCreateRq> requestEntity = new HttpEntity<>(request);

    //when
    ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity,
        Long.class);

    //then
    assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    assertThat(responseEntity.getBody()).isGreaterThan(0L);

    List<Post> all = postRepository.findAll();
    assertThat(all.get(0).getTitle()).isEqualTo(title2);
    assertThat(all.get(0).getContent()).isEqualTo(content2);

  }

}