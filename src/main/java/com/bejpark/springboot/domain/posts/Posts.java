package com.bejpark.springboot.domain.posts;


import com.bejpark.springboot.domain.BaseTimeEntity;
//import javafx.geometry.Pos;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //모든 필드의 Getter 메소드 자동생성
@NoArgsConstructor //기본생성자 자동추가
@Entity //테이블과 링크될 클래스
public class Posts extends BaseTimeEntity { //해당 Entity클래스는 Setter메소드가 없다.
    @Id //해당 테이블의 PK테이블
    @GeneratedValue(strategy = GenerationType.IDENTITY) //PK생성규칙
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //빌더를 사용하면 어느 필드에 어떤값을 채울지 인지하기 쉬움
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
