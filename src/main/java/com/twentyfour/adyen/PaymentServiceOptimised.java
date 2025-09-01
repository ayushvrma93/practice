package com.twentyfour.adyen;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
public class PaymentServiceOptimised {

    private final NavigableMap<String, NavigableMap<Instant, List<Payment>>> paymentsMap = new TreeMap<>();

    // Method to register a payment
    public void registerPayment(String id, String hashedCardNumber, Instant registeredAt) {
        Payment payment = new Payment(id, hashedCardNumber, registeredAt);
        paymentsMap
                .computeIfAbsent(hashedCardNumber, k -> new TreeMap<>())
                .computeIfAbsent(registeredAt, k -> new ArrayList<>()).add(payment);
    }


    // Method to get the count of a specific hashedCardNumber registered within a given interval
    public long countPaymentsInInterval(Instant start, Duration duration, String hashedCardNumber) {
        Instant end = start.plus(duration);

        // Retrieve the map of payments for the hashedCardNumber
        NavigableMap<Instant, List<Payment>> timeMap = paymentsMap.get(hashedCardNumber);
        if (timeMap == null) {
            return 0;
        }

        // Use subMap to efficiently get the range of payments within the time interval
        NavigableMap<Instant, List<Payment>> instantSubMap = timeMap.subMap(start, true, end, false);

        return instantSubMap.values().stream().mapToInt(List::size).sum();
    }

    public static void main(String[] args) {
        PaymentServiceOptimised service = new PaymentServiceOptimised();
        service.registerPayment("1", "hashedCard123", Instant.parse("2024-08-17T10:15:30.00Z"));
        service.registerPayment("2", "hashedCard123", Instant.parse("2024-08-17T10:25:30.00Z"));
        service.registerPayment("3", "hashedCard124", Instant.parse("2024-08-17T10:25:30.00Z"));

        System.out.println(service.countPaymentsInInterval(Instant.parse("2024-08-17T10:00:00.00Z"), Duration.ofMinutes(30), "hashedCard123"));
        System.out.println(service.countPaymentsInInterval(Instant.parse("2024-08-17T10:20:00.00Z"), Duration.ofMinutes(30), "hashedCard123"));
    }
}
