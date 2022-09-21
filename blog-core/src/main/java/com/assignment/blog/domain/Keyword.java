package com.assignment.blog.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Keyword implements Serializable {

    @JsonIgnore
    @Id @GeneratedValue
    private Long id;

    private String word;
    private Integer searchCount;

    public Keyword(String word, Integer searchCount) {
        this.word = word;
        this.searchCount = searchCount;
    }

    public void plusSearchCount() {
        this.searchCount += 1;
    }
}
