package ru.netology.tests;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.MainPage;
import ru.netology.data.APIHelper;

public class APITest {
    DataGenerator generator = new DataGenerator();
    MainPage mainPage = new MainPage();

    @Test
    void shouldApprovePaymentByApprovedCard() {
        APIHelper.payByApprovedCard();
    }

    @Test
    void shouldApproveCreditByApprovedCard() {
        APIHelper.payByCreditWithApprovedCard();
    }

    @Test
    void shouldDeclinePaymentByDeclinedCard() {
        APIHelper.payByDeclinedCard();
    }

    @Test
    void shouldDeclineCreditByDeclinedCard() {
        APIHelper.payByCreditWithDeclinedCard();
    }
}
