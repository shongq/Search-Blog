package com.assignment.blog.repository;

import com.assignment.blog.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface KeywordRepository extends JpaRepository<Keyword, String> {

    List<Keyword> findTop10ByOrderBySearchCountDesc();

//    @Lock(LockModeType.NONE)
    Optional<Keyword> findByWord(String word);
}
