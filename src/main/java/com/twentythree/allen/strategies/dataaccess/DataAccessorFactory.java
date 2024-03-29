package com.twentythree.allen.strategies.dataaccess;

import com.twentythree.allen.exceptions.UnsupportedDataBase;
import com.twentythree.allen.models.enums.DataBase;

public class DataAccessorFactory {

    public static IDataAccessor getInstance(DataBase dataBase){

        if(dataBase.equals(DataBase.IN_MEMORY)) return InMemoryDataAccessor.getINSTANCE();
        else throw new UnsupportedDataBase("DB not supported");
    }
}
