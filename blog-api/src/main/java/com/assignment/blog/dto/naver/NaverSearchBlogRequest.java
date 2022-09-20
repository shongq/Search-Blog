package com.assignment.blog.dto.naver;

import com.assignment.blog.common.api.pattern.IHandlerRequest;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class NaverSearchBlogRequest implements IHandlerRequest {
    private String query;   //검색 키워드
    private String sort;    //결과 정렬 방식
    private Integer display;   //페이지당 출력할 데이터 개수
    private Integer start;  //검색 시작 위치

    public NaverSearchBlogRequest() {
        this.display = 10;
        this.start = 1;
        this.sort = "sim";
    }

    @Override
    public String query() {
        return query;
    }

    @Override
    public String sort() {
        return sort;
    }
}
