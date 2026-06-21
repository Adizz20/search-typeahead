package com.aditya.searchtypehead.repository;

import com.aditya.searchtypehead.model.SearchQuery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchQueryRepository extends JpaRepository<SearchQuery, Long> {

    List<SearchQuery>
    findByQueryStartingWithIgnoreCaseOrderByTotalCountDesc(
            String prefix,
            Pageable pageable
    );

    SearchQuery findByQueryIgnoreCase(String query);
}