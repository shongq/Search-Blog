package com.assignment.blog.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Meta {
    private Integer total_count;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer pageable_count;
    private Boolean is_end;
}
