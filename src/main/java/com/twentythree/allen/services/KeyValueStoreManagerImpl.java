package com.twentythree.allen.services;

import com.twentythree.allen.models.Pair;
import com.twentythree.allen.models.enums.DataBase;
import com.twentythree.allen.strategies.dataaccess.DataAccessorFactory;

import java.util.List;
import java.util.Map;

public class KeyValueStoreManagerImpl implements IKeyValueStore{

    private static final IKeyValueStore INSTANCE = new KeyValueStoreManagerImpl();

    private KeyValueStoreManagerImpl(){}

    public static IKeyValueStore getINSTANCE() {
        return INSTANCE;
    }


    @Override
    public Map<String, Object> get(String key) {
        return DataAccessorFactory.getInstance(DataBase.IN_MEMORY).get(key);
    }

    @Override
    public List<String> search(String attributeKey, String attributeValue) {
        return DataAccessorFactory.getInstance(DataBase.IN_MEMORY).search(attributeKey, attributeValue);
    }

    @Override
    public boolean put(String key, List<Pair<String, Object>> listOfAttributePairs) {
        return DataAccessorFactory.getInstance(DataBase.IN_MEMORY).put(key, listOfAttributePairs);
    }

    @Override
    public boolean delete(String key) {
        return DataAccessorFactory.getInstance(DataBase.IN_MEMORY).delete(key);
    }

    @Override
    public List<String> keys() {
        return DataAccessorFactory.getInstance(DataBase.IN_MEMORY).keys();
    }
}
