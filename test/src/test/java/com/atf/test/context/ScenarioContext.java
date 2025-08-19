package com.atf.test.context;

import io.cucumber.spring.ScenarioScope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ScenarioScope
@Slf4j
public class ScenarioContext {

    private final Map<String, Object> store = new ConcurrentHashMap<>();

    public <T> void put(String key, T value) {
        store.put(key, value);
        log.debug("[ScenarioContext] put key: {} value: {}", key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key) {
        T value = (T) store.get(key);
        log.debug("[ScenarioContext] get key: {} value: {}", key, value);
        return value;
    }

    public boolean has(String key) {
        boolean present = store.containsKey(key);
        log.debug("[ScenarioContext] has key: {} present: {}", key, present);
        return present;
    }

    public void clear() {
        store.clear();
        log.debug("[ScenarioContext] cleared all entries");
    }
}