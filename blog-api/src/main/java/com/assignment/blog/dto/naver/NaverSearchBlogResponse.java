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
    private String lastBuildDate;
    private Integer total;
    private Integer start;
    private Integer display;
    private List<Document> items;
}
