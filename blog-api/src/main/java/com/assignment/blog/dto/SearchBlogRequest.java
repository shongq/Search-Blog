package com.assignment.blog.dto;

import com.assignment.blog.common.api.pattern.IHandlerRequest;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;

@Data
@Builder
@AllArgsConstructor
public class SearchBlogRequest implements IHandlerRequest {
    @NotNull
    private String query;   //검색 키워드
    private String sort;
    @Max(50)
    private Integer size;   //페이지당 출력할 데이터 개수
    @Max(50)//결과 정렬 방식
    private Integer page;   //현재 페이지 번호

    public SearchBlogRequest() {
        this.page = 1;
        this.size = 10;
        this.sort = Sort.accuracy.toString();
    }

    @Override
    public String query() {
        return query;
    }

    @Override
    public String sort() {
        return sort;
    }

    public enum Sort {
        accuracy, recency
    }
}
