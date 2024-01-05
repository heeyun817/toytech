package io.toytech.backend.global.util;

import jakarta.persistence.EntityManager;

/**
 * 엔티티 로딩 횟수를 추적하고 필요한 정보를 제공하는 클래스
 * <p>
 * N + 1 문제가 안터지는지 테스트 코드를 작성하는데 활용된다.
 */
public class QueryCountUtil {

  public static long getEntityLoadCount(EntityManager entityManager) {
    return entityManager.unwrap(org.hibernate.Session.class).getSessionFactory().getStatistics()
        .getEntityLoadCount();
  }

  public static long getSelectQueryCount(EntityManager entityManager) {
    return entityManager.unwrap(org.hibernate.Session.class).getSessionFactory().getStatistics()
        .getQueryExecutionCount();
  }

  public static void clearAllCount(EntityManager entityManager) {
    entityManager.unwrap(org.hibernate.Session.class).getSessionFactory().getStatistics()
        .clear();
  }
}