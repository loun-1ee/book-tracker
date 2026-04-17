package com.loun.booktracker.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Book {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; //책 제목
    private String author; //저자
    private String publisher; //출판사
    private String thumbnail; //표지 이미지 URL

    @Enumerated(EnumType.STRING)
    private ReadStatus status; //독서 상태

    private Integer rating; //별점 (1~5)
    private String review; //한줄평

    public enum ReadStatus {
        WANT, //읽고 싶은 책
        READING, //읽는 중인 책
        DONE //다 읽은 책
    }
}
