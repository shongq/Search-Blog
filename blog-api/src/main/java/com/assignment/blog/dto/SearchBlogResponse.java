package com.assignment.blog.dto;

import com.assignment.blog.domain.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchBlogResponse {
    private Meta meta;
    private List<Document> documents;
}
