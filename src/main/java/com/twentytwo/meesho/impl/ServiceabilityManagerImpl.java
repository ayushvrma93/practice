package com.twentytwo.meesho.impl;

import com.twentytwo.meesho.IServiceabilityManager;
import com.twentytwo.meesho.entity.enums.PaymentMode;

import java.util.*;

public class ServiceabilityManagerImpl implements IServiceabilityManager {

    private static final IServiceabilityManager INSTANCE = new ServiceabilityManagerImpl();

    public static IServiceabilityManager getINSTANCE() {
        return INSTANCE;
    }


    public Map<Integer, Map<Integer, Set<PaymentMode>>> getServiceabilityMap() {
        return pinVsMode;
    }

    @Override
    public boolean create(Integer sourcePin, Integer destinationPin, PaymentMode paymentMode) {

        if(sourcePin == null || destinationPin == null || paymentMode == null){
            System.out.println("Invalid input.");
            return false;
        }


        if(pinVsMode.containsKey(sourcePin)){

            Map<Integer, Set<PaymentMode>> destinationModes = pinVsMode.get(sourcePin);
            Set<PaymentMode> modes;

            if(destinationModes.containsKey(destinationPin)){
                modes = destinationModes.get(destinationPin);
            }

            else{
                 modes = new HashSet<>();
            }
            modes.add(paymentMode);
            destinationModes.put(destinationPin, modes);
        }

        else{

            Map<Integer, Set<PaymentMode>> destinationModes = new HashMap<>();

            Set<PaymentMode> modes = new HashSet<>();
            modes.add(paymentMode);

            destinationModes.put(destinationPin, modes);

            pinVsMode.put(sourcePin, destinationModes);
        }
        return true;
    }
}
