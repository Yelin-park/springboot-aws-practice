package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*
    보통 MyBatis에서 Dao라고 불리는 DB Layer 접근자를 JPA에선 Repository라고 부르며 인터페이스로 생성
    생성 후 JpaRepository<Entity 클래스, PK 타입>를 상속하면 기본적인 CRUD 메소드가 자동으로 생성된다.
    @Repository 추가할 필요도 없음
    주의! Entity 클래스와 기본 Entity Repository는 함께 위치해야 함!
        도메인 별로 프로젝트를 분리해야 한다면 Entity클래스와 Repository는 함께 움직여야 하므로 도메인 패키지에서 함께 관리
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {

    // Querydsl 사용 - 조회에 사용하고 등록/수정/삭제는 SpringDataJpa 사용
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
