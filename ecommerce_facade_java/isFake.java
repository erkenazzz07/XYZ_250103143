package com.ecommerce;

import java.util.Set;

/**
 * Subsystem Class 5: FraudDetectionService
 * Decides whether an incoming order looks fake before any other subsystem is touched.
 */
public class isFake {

    private static final double MAX_ALLOWED_AMOUNT = 10000.00;
    private static final Set<String> BLACKLISTED_CUSTOMERS = Set.of("bot_001", "fraud_999");

    /**
     * @return true if the order is FAKE (must be rejected), false if it looks legitimate.
     */
    public boolean isFake(String productId, String customerId, String address, double amount) {
        System.out.println("[FraudDetectionService] Checking order for customer: " + customerId);

        if (isBlank(productId) || isBlank(customerId) || isBlank(address)) {
            System.out.println("[FraudDetectionService] Fake: missing order details.");
            return true;
        }

        if (amount <= 0 || amount > MAX_ALLOWED_AMOUNT) {
            System.out.println("[FraudDetectionService] Fake: suspicious amount $" + amount);
            return true;
        }

        if (BLACKLISTED_CUSTOMERS.contains(customerId)) {
            System.out.println("[FraudDetectionService] Fake: blacklisted customer.");
            return true;
        }

        System.out.println("[FraudDetectionService] Order looks legitimate.");
        return false;
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}