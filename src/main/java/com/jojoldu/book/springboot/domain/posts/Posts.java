package com.jojoldu.book.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
    P93 참고하기
    Entity 클래스에서는 절대 Setter 메서드를 만들지 않는다.
    대신, 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야 함

    Setter가 없는 상황에서 어떻게 값을 채워 DB에 삽입 하는가?
     > 기본적인 구조는 생성자를 통해 최종값을 채운 후 DB 삽입하는 것. 값 변경이 필요한 경우 해당 이벤트에 맞는 public 메소드를 호출하여 변경
     여기서는 생성자 대신 @Builder를 통해 제공되는 빌더 클래스를 사용.
     생성 시점에 값을 채워주는 역할로 생성자와 같지만 생성자의 경우 지금 채워야 할 필드가 무엇인지 명확히 지정할 수 없음
 */

// Posts 클래스는 실제 DB의 테이블과 매칭될 클래스 = Entity 클래스
@Getter
@NoArgsConstructor
@Entity // 테이블과 링크될 클래스임을 나타냄, 기본값으로 클래스의 카멜케이스 이름을 _로 테이블 이름을 매칭(SalesManager.java == sales_manager table)
public class Posts {

    @Id // 해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성규칙
    private Long id;
    /*
       Entity의 PK는 Auto_increment 추천 - 주민번호오자 같이 비즈니스상 유니크 키나, 여러 키를 조합한 복합키로 PK를 잡을 경우 난감한 상황이 발생
          FK를 맺을 때 다른 테이블에서 복합키를 전부 갖고 있거나, 중간 테이블을 하나 더 둬야하는 상활 발생
          인덱스에 좋은 영향을 끼치지 못함
          유니크한 조건이 변경될 경우 PK를 전체 수정해야 하는 상황 발생
          * 주민번호, 복합키 등은 유니크 키로 별도로 추가하는 것이 좋다!
     */

    /*
        @Column
        테이블의 컬럼을 나타내며 굳이 선언하지 않아도 해당 클래스의 필드는 모두 컬럼이 됨
        사용하는 이유는 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용
        문자열의 경우 VARCHAR(255)가 기본인데 사이즈를 500으로 늘리고 싶거나 타입을 TEXT로 변경하고 싶거나 등 사용함
    */
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스 생성, 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
