package com.twentyfour.adyen;

import lombok.Data;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
class Payment{
    private String id;
    private String hashedCardNumber;

    private Instant registeredAt;

    public Payment(String id, String hashedCardNumber, Instant registeredAt) {
        this.id = id;
        this.hashedCardNumber = hashedCardNumber;
        this.registeredAt = registeredAt;
    }
}


public class PaymentService {
    private List<Payment> payments = new ArrayList<>();

    // Method to register a payment
    public void registerPayment(String id, String hashedCardNumber, Instant registeredAt) {
        Payment payment = new Payment(id, hashedCardNumber, registeredAt);
        payments.add(payment);
    }

    // Method to get the count of a specific hashedCardNumber registered within a given interval
    public long countPaymentsInInterval(Instant start, Duration duration, String hashedCardNumber) {
        Instant end = start.plus(duration);

        return payments.stream()
                .filter(payment -> payment.getRegisteredAt().isAfter(start) && payment.getRegisteredAt().isBefore(end))
                .filter(payment -> payment.getHashedCardNumber().equals(hashedCardNumber))
                .count();
    }
}
