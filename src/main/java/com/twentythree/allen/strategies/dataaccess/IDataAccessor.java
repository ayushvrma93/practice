package com.twentythree.allen.strategies.dataaccess;

import com.twentythree.allen.models.Pair;

import java.util.List;
import java.util.Map;

public interface IDataAccessor {

    Map<String, Object> get(String key);
    List<String> search(String attributeKey, String attributeValue);

    boolean put(String key, List<Pair<String, Object>> listOfAttributePairs);

    boolean delete(String key);

    List<String> keys();

}
