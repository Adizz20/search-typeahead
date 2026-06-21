package com.aditya.searchtypehead.service;

import com.aditya.searchtypehead.batch.SearchBuffer;
import com.aditya.searchtypehead.model.SearchEvent;
import com.aditya.searchtypehead.repository.SearchEventRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SearchService {

    private final SearchBuffer searchBuffer;
    private final SearchEventRepository eventRepository;

    public SearchService(
            SearchBuffer searchBuffer,
            SearchEventRepository eventRepository
    ) {
        this.searchBuffer = searchBuffer;
        this.eventRepository = eventRepository;
    }

    public void recordSearch(String query) {

        // Add to batch buffer
        searchBuffer.addSearch(query);

        // Save search event immediately
        SearchEvent event =
                new SearchEvent(
                        query,
                        LocalDateTime.now()
                );

        eventRepository.save(event);
    }
}