package com.kgc.book.springboot.domain.posts;

import com.kgc.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor // 기본생성자 추가
@Entity // 테이블과 링크될 클래스 임을 나타냄
public class Posts extends BaseTimeEntity {

    @Id // 테이블의 PK 필드 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성규칙, strategy = GenerationType.IDENTITY - auto_increment 속성부여
    private Long id;

    @Column(length = 500, nullable = false) // 컬럼임을 표시, 없어도 자동으로 생성, 추가 옵션 있을시 사용
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 빌더 패턴 클래스 생성
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
