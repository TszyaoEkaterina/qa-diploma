package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataGenerator {
    private static Faker faker = new Faker(new Locale("en"));


    @Value
    public static class CardData {
        private String cardNumber;
        private String month;
        private String year;
        private String cardOwner;
        private String cvv;
    }

    public String getValidMonth() {
        int monthNumber = faker.number().numberBetween(1, 12);
        String validMonth;
        if (monthNumber < 10) {
            validMonth = "0" + monthNumber;
        } else {
            validMonth = String.valueOf(monthNumber);
        }
        return validMonth;
    }

    String validYear = String.valueOf(faker.number().numberBetween(23, 27));
    String validCardOwner = faker.name().fullName();
    String validCvv = String.valueOf(faker.number().randomNumber(3, true));

    public CardData getApprovedCard() {
        return new CardData("4444 4444 4444 4441",
                getValidMonth(), validYear, validCardOwner, validCvv);
    }

    public CardData getDeclinedCard() {
        return new CardData("4444 4444 4444 4442",
                getValidMonth(), validYear, validCardOwner, validCvv);
    }

    public String getNumberWithFewerDigits() {
        return String.valueOf(faker.number().randomNumber(14, true));
    }

    public CardData getCardWithoutCardNumber() {
        return new CardData("",
                getValidMonth(), validYear, validCardOwner, validCvv);
    }

    public CardData getCardWithoutMonth() {
        return new CardData("4444 4444 4444 4441",
                "", validYear, validCardOwner, validCvv);
    }

    public CardData getCardWithoutYear() {
        return new CardData("4444 4444 4444 4441",
                getValidMonth(), "", validCardOwner, validCvv);
    }

    public CardData getCardWithoutOwner() {
        return new CardData("4444 4444 4444 4441",
                getValidMonth(), validYear, "", validCvv);
    }

    public CardData getCardWithoutCvv() {
        return new CardData("4444 4444 4444 4441",
                getValidMonth(), validYear, validCardOwner, "");
    }
    public CardData getCardWithoutDate() {
        return new CardData("4444 4444 4444 4441",
                "", "", validCardOwner, validCvv);
    }
}
