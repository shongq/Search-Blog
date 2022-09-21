package com.assignment.blog.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Keyword implements Serializable, Persistable<String> {

    /*@Id @GeneratedValue
    private Long id;*/

    @Id
    private String word;
    private Integer searchCount;

//    @Version
//    private Integer version;

    public Keyword(String word, Integer searchCount) {
        this.word = word;
        this.searchCount = searchCount;
    }

    public void plusSearchCount() {
        this.searchCount += 1;
    }

    @Override
    public String getId() {
        return word;
    }

    @Override
    public boolean isNew() {
        return searchCount==null;
    }
}
