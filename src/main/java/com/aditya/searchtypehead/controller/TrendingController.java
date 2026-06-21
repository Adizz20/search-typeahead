package com.aditya.searchtypehead.controller;

import com.aditya.searchtypehead.model.SearchEvent;
import com.aditya.searchtypehead.repository.SearchEventRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class TrendingController {

    private final SearchEventRepository repository;

    public TrendingController(SearchEventRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/trending")
    public List<String> trending() {

        List<SearchEvent> events = repository.findAll();

        Map<String, Long> counts =
                events.stream()
                        .collect(Collectors.groupingBy(
                                SearchEvent::getQuery,
                                Collectors.counting()
                        ));

        return counts.entrySet()
                .stream()
                .sorted((a, b) ->
                        Long.compare(
                                b.getValue(),
                                a.getValue()
                        ))
                .limit(10)
                .map(Map.Entry::getKey)
                .toList();
    }
}