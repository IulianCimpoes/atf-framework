package com.atf.test.context;

import io.cucumber.spring.ScenarioScope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ScenarioScope
public class ScenarioContext {

    private final Map<String, Object> store = new ConcurrentHashMap<>();

    public <T> void put(String key, T value) {
        store.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        return (T) store.get(key);
    }

    public boolean has(String key) {
        return store.containsKey(key);
    }

    public void clear() {
        store.clear();
    }
}