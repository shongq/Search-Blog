package com.assignment.blog.repository;

import com.assignment.blog.domain.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KeywordRepository extends JpaRepository<Keyword, String> {

    List<Keyword> findTop10ByOrderBySearchCountDesc();
    Optional<Keyword> findByWord(String word);
}
