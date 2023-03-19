package com.twentythree.allen.strategies.dataaccess;

import com.twentythree.allen.exceptions.DataTypeError;
import com.twentythree.allen.exceptions.KeyNotExists;
import com.twentythree.allen.models.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDataAccessor implements IDataAccessor{

    private static final IDataAccessor INSTANCE = new InMemoryDataAccessor();

    private InMemoryDataAccessor(){}

    public static IDataAccessor getINSTANCE() {
        return INSTANCE;
    }

    ConcurrentHashMap<String, Map<String, Object>> keyValueStore = new ConcurrentHashMap<>();

    @Override
    public Map<String, Object> get(String key) {
        if(keyValueStore.containsKey(key)) return keyValueStore.get(key);
        throw new KeyNotExists("Given key does not exist");
    }

    @Override
    public List<String> search(String attributeKey, String attributeValue) {

        List<String> allKeysWithPair = new ArrayList<>();

        for(Map.Entry<String, Map<String, Object>> entry : keyValueStore.entrySet()){

            Map<String, Object> currEntry = entry.getValue();
            if(currEntry.containsKey(attributeKey) && currEntry.get(attributeKey).equals(attributeValue)){
                allKeysWithPair.add(entry.getKey());
            }
        }
        return allKeysWithPair;
    }

    @Override
    public boolean put(String key, List<Pair<String, Object>> listOfAttributePairs) {

        Map<String, Object> temp;

        if(!keyValueStore.containsKey(key)) {
            temp = new HashMap<>();
        }
        else {
            temp = keyValueStore.get(key);
        }

        for(Pair<String, Object> pairs : listOfAttributePairs) {
            if(temp.containsKey(pairs.getKey())){
                Object curr = temp.get(pairs.getKey());
                if(!curr.getClass().equals(pairs.getValue().getClass())){
                    throw new DataTypeError("Data type does not match");
                }
            }
            temp.put(pairs.getKey(), pairs.getValue());
        }
        keyValueStore.put(key, temp);
        return true;
    }

    @Override
    public boolean delete(String key) {

        if(!keyValueStore.containsKey(key)){
            throw new KeyNotExists("Key does not exist");
        }

        Object value = keyValueStore.remove(key);
        return value!=null;
    }

    @Override
    public List<String> keys() {

        List<String> keys = new ArrayList<>();

        for(Map.Entry<String, Map<String, Object>> entry : keyValueStore.entrySet()){
            keys.add(entry.getKey());
        }
        return keys;
    }
}
