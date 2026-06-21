package com.aditya.searchtypehead.controller;

import com.aditya.searchtypehead.model.SearchQuery;
import com.aditya.searchtypehead.repository.SearchQueryRepository;
import com.aditya.searchtypehead.service.CacheService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
public class SuggestionController {

    private final SearchQueryRepository repository;
    private final CacheService cacheService;

    public SuggestionController(
            SearchQueryRepository repository,
            CacheService cacheService
    ) {
        this.repository = repository;
        this.cacheService = cacheService;
    }

    @GetMapping("/suggest")
    public List<SearchQuery> suggest(
            @RequestParam String q
    ) {

        Object cachedResult = cacheService.get(q);

        if(cachedResult != null) {
            return (List<SearchQuery>) cachedResult;
        }

        List<SearchQuery> suggestions =
                repository.findAll()
                        .stream()
                        .filter(item ->
                                item.getQuery()
                                        .toLowerCase()
                                        .startsWith(q.toLowerCase()))
                        .sorted(
                                Comparator.comparing(
                                        SearchQuery::getTotalCount
                                ).reversed()
                        )
                        .limit(10)
                        .toList();

        cacheService.put(q, suggestions);

        return suggestions;
    }
}