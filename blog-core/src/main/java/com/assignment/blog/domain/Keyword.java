package com.assignment.blog.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
public class Keyword implements Serializable {

    /*@Id @GeneratedValue
    private Long id;*/

    @Id
    private String word;
    private Integer searchCount;

//    @Version
//    private Integer version;

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
