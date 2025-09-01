package com.twentyfour.atlassian.machinecoding;

import lombok.Data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class RateLimit {
    static Map<String, CustomerInfo> customerInfoMap = new HashMap<>();
    @Data
    static class CustomerInfo{
        int SIZE;
        Queue<String> bucket;

        private long lastChecked;

        private final int leakRate;

        CustomerInfo(int size, Queue<String> bucket, long lastChecked, int leakRate){
            this.SIZE = size;
            this.bucket = bucket;
            this.lastChecked = lastChecked;
            this.leakRate = leakRate;

        }
    }

    public boolean rateLimit(String customerID){
        CustomerInfo customerInfo = customerInfoMap.get(customerID);

        if(!customerInfoMap.containsKey(customerID)){
            //log error and return Customer not found exception.
        }

        Queue<String> queue = customerInfo.getBucket();

        leak(customerInfo);

        synchronized (this){
            if(queue.size() == customerInfo.getSIZE()){
                // log
                return false;
            }
            queue.add("packet");
        }

        return true;
    }

    public void leak(CustomerInfo customerInfo){
        long currTime = System.currentTimeMillis();
        long lastChecked = customerInfo.getLastChecked();
        long elapsedTime = currTime - lastChecked;

        int packetsToLeak = (int) (elapsedTime * customerInfo.getLeakRate())/1000;

        synchronized (customerInfo.bucket){
            for(int i=0; i<packetsToLeak; i++){
                Queue<String> queue = customerInfo.getBucket();
                if(queue.isEmpty()){
                    break;
                }
                queue.poll();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RateLimit rateLimit = new RateLimit();

        Queue<String> q1 = new LinkedList<>();
        CustomerInfo customerInfo1 = new CustomerInfo(3, q1, System.currentTimeMillis(), 2);
        customerInfoMap.put("customer1", customerInfo1);

        Runnable thread1 = () -> {
            System.out.println(rateLimit.rateLimit("customer1"));
            System.out.println(rateLimit.rateLimit("customer1"));
            System.out.println(rateLimit.rateLimit("customer1"));
        };

        Runnable thread2 = new Thread(() -> {
            System.out.println(rateLimit.rateLimit("customer1"));
            System.out.println(rateLimit.rateLimit("customer1"));
            System.out.println(rateLimit.rateLimit("customer1"));
//            try {
//                Thread.sleep(10000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            System.out.println(rateLimit.rateLimit("customer1"));
        });

        thread1.run();
        thread2.run();

    }
}
