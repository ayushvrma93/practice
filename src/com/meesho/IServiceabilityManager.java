package com.meesho;

import com.meesho.entity.enums.PaymentMode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public interface IServiceabilityManager {

    Map<Integer, Map<Integer, Set<PaymentMode>>> pinVsMode = new HashMap<>();

    //Ideally, this should return the whole source, destination and added serviceability entry
    boolean create(Integer sourcePin, Integer destinationPin, PaymentMode paymentMode);
    Map<Integer, Map<Integer, Set<PaymentMode>>> getServiceabilityMap();
}
