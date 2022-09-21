package com.assignment.blog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Meta {
    @JsonProperty("total_count")
    private Integer totalCount; //검색된 문서 수
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("pageable_count")
    private Integer pageableCount;  //totalCount 중 노출 가능 문서 수
    @JsonProperty("is_end")
    private Boolean isEnd;  //마지막 페이지 여부
}
