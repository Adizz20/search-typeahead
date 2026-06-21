package com.aditya.searchtypehead.service;

import com.aditya.searchtypehead.cache.CacheNode;
import com.aditya.searchtypehead.cache.ConsistentHashing;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    private final ConsistentHashing consistentHashing;

    public CacheService(ConsistentHashing consistentHashing) {
        this.consistentHashing = consistentHashing;
    }

    public Object get(String key) {

        CacheNode node = consistentHashing.getNode(key);

        if(node.contains(key)) {
            System.out.println("CACHE HIT -> " + node.getNodeName());
            return node.get(key);
        }

        System.out.println("CACHE MISS -> " + node.getNodeName());
        return null;
    }

    public void put(String key, Object value) {

        CacheNode node = consistentHashing.getNode(key);

        node.put(key, value);

        System.out.println("Stored in " + node.getNodeName());
    }
}