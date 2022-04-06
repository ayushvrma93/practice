package com.flipkart.machinecoding;

import com.flipkart.machinecoding.impl.InMemoryAccessor;

import static com.flipkart.machinecoding.Constants.DBNames.IN_MEMORY;

public class DataAccessorFactory {

    private static final DataAccessorFactory INSTANCE = new DataAccessorFactory();

    public static DataAccessorFactory getInstance() {
        return INSTANCE;
    }

    public IDataAccessor getDataAccessor(String dbName){

        if(dbName.equals(IN_MEMORY)) return InMemoryAccessor.getInstance();

        return null;
    }
}
