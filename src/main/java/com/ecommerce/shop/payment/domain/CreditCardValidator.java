package com.ecommerce.shop.payment.domain;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

class CreditCardValidator {

    private static final Pattern CVV_PATTERN = Pattern.compile("^\\d{3,4}$");
    private static final Pattern CARD_NUMBER_PATTERN = Pattern.compile("^\\d{13,19}$");

    public static boolean validate(CreditCard card) {
        return isValidCardNumber(card.getCardNumber()) &&
                isValidExpirationDate(card.getExpirationDate()) &&
                isValidCvv(card.getCvv()) &&
                isValidCardHolderName(card.getCardHolderName());
    }

    public static boolean isValidCardNumber(String cardNumber) {
        if (cardNumber == null || !CARD_NUMBER_PATTERN.matcher(cardNumber).matches()) {
            return false;
        }
        return luhnCheck(cardNumber);
    }

    private static boolean luhnCheck(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Character.getNumericValue(cardNumber.charAt(i));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n -= 9;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }

    public static boolean isValidExpirationDate(String expirationDate) {
        if (expirationDate == null || !expirationDate.matches("^(0[1-9]|1[0-2])\\/\\d{2}$")) {
            return false;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
            YearMonth expDate = YearMonth.parse(expirationDate, formatter);
            return expDate.isAfter(YearMonth.now());
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isValidCvv(String cvv) {
        return cvv != null && CVV_PATTERN.matcher(cvv).matches();
    }

    public static boolean isValidCardHolderName(String name) {
        return name != null && !name.trim().isEmpty();
    }
}