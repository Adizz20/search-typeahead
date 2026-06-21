package com.aditya.searchtypehead.repository;

import com.aditya.searchtypehead.model.SearchEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchEventRepository
        extends JpaRepository<SearchEvent, Long> {
}