package com.assignment.blog.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Keyword {

    @Id @GeneratedValue
    private Long id;
    private String word;
    private Integer searchCount;

    protected Keyword() {
    }

    public Keyword(String word, Integer searchCount) {
        this.word = word;
        this.searchCount = searchCount;
    }

    public void plusSearchCount() {
        this.searchCount += 1;
    }
}
