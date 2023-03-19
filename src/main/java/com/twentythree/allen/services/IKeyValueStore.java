package com.twentythree.allen.services;

import com.twentythree.allen.models.Pair;

import java.util.List;
import java.util.Map;

public interface IKeyValueStore {

    Map<String, Object> get(String key);
    List<String> search(String attributeKey, String attributeValue);

    boolean put(String key, List<Pair<String, Object>> listOfAttributePairs);

    boolean delete(String key);

    List<String> keys();
}
