package com.assignment.blog.dto;

import com.assignment.blog.common.api.pattern.IHandlerResponse;
import lombok.*;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HandlerResponse implements IHandlerResponse {
    private boolean isSuccess;  //핸들러 성공 여부
    private boolean isSearch;   //검색 성공 여부
    @Getter
    private SearchBlogResponse searchBlogResponse;

    @Override
    public boolean isSuccess() {
        return isSuccess;
    }

    @Override
    public boolean isSearch() {
        return isSearch;
    }
}
