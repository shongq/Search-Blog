package com.assignment.blog.domain;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;

@Data
public class Document {

    private String title;

    @JsonAlias({"contents", "description"})
    private String contents;

    @JsonAlias({"url", "link"})
    private String url;

    @JsonAlias({"blogname", "bloggername"})
    private String blogname;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String thumbnail;

    @JsonAlias({"datetime", "postdate"})
    @JsonFormat(timezone = "GMT+9")
    private Date datetime;
}
