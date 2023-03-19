package com.twentythree.allen;

import com.twentythree.allen.models.Pair;
import com.twentythree.allen.services.IKeyValueStore;
import com.twentythree.allen.services.KeyValueStoreManagerImpl;

import java.util.ArrayList;
import java.util.List;

public class Driver {

    private static final IKeyValueStore keyValueStore = KeyValueStoreManagerImpl.getINSTANCE();

    public static void main(String[] args) {

        String key1 = "sde_bootcamp";

        List<Pair<String, Object>> listOfAttributePairs1 = new ArrayList<>();
        Pair pair1 = new Pair("title", "SDE-Bootcamp");
        Pair pair2 = new Pair("price", "30000.00");
        Pair pair3 = new Pair("enrolled", false);
        Pair pair4 = new Pair("estimated_time", "30");

        listOfAttributePairs1.add(pair1);
        listOfAttributePairs1.add(pair2);
        listOfAttributePairs1.add(pair3);
        listOfAttributePairs1.add(pair4);

        System.out.println(keyValueStore.put(key1, listOfAttributePairs1));
        System.out.println(keyValueStore.get(key1));
        System.out.println(keyValueStore.keys());

        String key2 = "sde_kickstart";

        List<Pair<String, Object>> listOfAttributePairs2 = new ArrayList<>();
        Pair pair5 = new Pair("title", "sde_kickstart");
        Pair pair6 = new Pair("price", 4000);
        Pair pair7 = new Pair("enrolled", false);
        Pair pair8 = new Pair("estimated_time", "30");

        listOfAttributePairs2.add(pair5);
        listOfAttributePairs2.add(pair6);
        listOfAttributePairs2.add(pair7);
        listOfAttributePairs2.add(pair8);

        System.out.println(keyValueStore.put(key2, listOfAttributePairs2));
        System.out.println(keyValueStore.get(key2));
        System.out.println(keyValueStore.keys());

        List<Pair<String, Object>> listOfAttributePairs3 = new ArrayList<>();
        Pair pair9 = new Pair("title", "sde_kickstart");
        Pair pair10 = new Pair("price", 4000.00);
        Pair pair11 = new Pair("enrolled", false);
        Pair pair12 = new Pair("estimated_time", "30");

        listOfAttributePairs3.add(pair9);
        listOfAttributePairs3.add(pair10);
        listOfAttributePairs3.add(pair11);
        listOfAttributePairs3.add(pair12);

        //System.out.println(keyValueStore.put(key2, listOfAttributePairs3));

        System.out.println(keyValueStore.keys());
        //System.out.println(keyValueStore.delete(key1));
        System.out.println(keyValueStore.keys());
        System.out.println(keyValueStore.search("price", "30000.00"));


    }
}
