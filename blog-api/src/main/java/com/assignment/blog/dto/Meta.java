package com.assignment.blog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Meta {
    @JsonProperty("total_count")
    private Integer totalCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("pageable_count")
    private Integer pageableCount;
    @JsonProperty("is_end")
    private Boolean isEnd;
}
