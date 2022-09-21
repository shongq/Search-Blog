package com.assignment.blog.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
public class Document {

    private String title;   //블로그 글 제목

    @JsonAlias({"contents", "description"})
    private String contents;    //블로그 글 요약

    @JsonAlias({"url", "link"})
    private String url; //블로그 글 URL

    @JsonAlias({"blogname", "bloggername"})
    private String blogname;    //블로그의 이름

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String thumbnail;   //검색 시스템에서 추출한 대표 미리보기 이미지 URL

    @JsonAlias({"datetime", "postdate"})
    @JsonFormat(timezone = "GMT+9")
    private Date datetime;  //블로그 글 작성시간
}
