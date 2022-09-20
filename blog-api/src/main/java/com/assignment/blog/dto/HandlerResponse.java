package com.assignment.blog.dto;

import com.assignment.blog.common.api.pattern.IHandlerResponse;
import com.assignment.blog.dto.SearchBlogResponse;
import lombok.*;

@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HandlerResponse implements IHandlerResponse {
    private boolean isSuccess;
    private boolean isSearch;
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
