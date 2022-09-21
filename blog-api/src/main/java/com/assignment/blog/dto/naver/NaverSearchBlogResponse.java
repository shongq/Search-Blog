package com.assignment.blog.dto.naver;

import com.assignment.blog.domain.Document;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NaverSearchBlogResponse {

    @JsonFormat(timezone = "GMT+9")
    private String lastBuildDate;   //검색 결과를 생성한 시간
    private Integer total;  //총 검색 결과 개수
    private Integer start;  //검색 시작 위치
    private Integer display;    //한 번에 표시할 검색 결과 개수
    private List<Document> items;
}
