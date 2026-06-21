package com.aditya.searchtypehead.controller;

import com.aditya.searchtypehead.service.SearchService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/search")
    public Map<String, String> search(
            @RequestParam String query
    ) {

        searchService.recordSearch(query);

        return Map.of(
                "message",
                "Searched"
        );
    }
}