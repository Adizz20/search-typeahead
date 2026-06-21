package com.aditya.searchtypehead.config;

import com.aditya.searchtypehead.model.SearchQuery;
import com.aditya.searchtypehead.repository.SearchQueryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final SearchQueryRepository repository;

    public DataLoader(SearchQueryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {

        if(repository.count() == 0) {

            repository.save(new SearchQuery("iphone", 100000L));
            repository.save(new SearchQuery("iphone 15", 85000L));
            repository.save(new SearchQuery("iphone charger", 60000L));
            repository.save(new SearchQuery("java tutorial", 40000L));

            System.out.println("Sample data loaded!");
        }
    }
}