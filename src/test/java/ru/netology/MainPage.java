package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.netology.data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {
    private SelenideElement cardNumber = $("input[placeholder='0000 0000 0000 0000']");
    private SelenideElement month = $("input[placeholder='08']");
    private SelenideElement year = $("input[placeholder='22']");
    private SelenideElement cardOwner = $x("//span[text()='Владелец']/parent::span//input");
    private SelenideElement cvv = $("input[placeholder='999']");

    private SelenideElement successNotification = $(byText("Операция одобрена Банком."));
    private SelenideElement failureNotification = $(byText("Ошибка! Банк отказал в проведении операции."));
    private SelenideElement incorrectFormat = $(byText("Неверный формат"));
    private SelenideElement emptyField = $(byText("Поле обязательно для заполнения"));

    private SelenideElement invalidDate = $(byText("Неверно указан срок действия карты"));
    private SelenideElement cardExpiredError = $(byText("Истёк срок действия карты"));
    private SelenideElement continueButton = $(byText("Продолжить"));

    public void payWithCard() {
        $(By.xpath("//*[text()='Купить']")).click();
        $$("h3.heading").last().shouldHave(text("Оплата по карте"));
    }

    public void payWithCredit() {
        $(By.xpath("//*[text()='Купить в кредит']")).click();
        $$("h3.heading").last().shouldHave(text("Кредит по данным карты"));
    }

    public void fillCardData(DataGenerator.CardData data) {
        cardNumber.setValue(data.getCardNumber());
        month.setValue(data.getMonth());
        year.setValue(data.getYear());
        cardOwner.setValue(data.getCardOwner());
        cvv.setValue(data.getCvv());
    }

    public void clickContinue() {
        continueButton.click();
    }

    public void setCardNumber() {

    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber.setValue(cardNumber);
    }

    public void setMonth(String month) {
        this.month.setValue(month);
    }

    public void setYear(String year) {
        this.year.setValue(year);
    }

    public void setCardOwner(String cardOwner) {
        this.cardOwner.setValue(cardOwner);
    }

    public void setCvv(String cvv) {
        this.cvv.setValue(cvv);
    }

    public void successNotificationVisible() {
        successNotification.shouldBe(visible, Duration.ofSeconds(10));
        failureNotification.shouldBe(hidden);
    }

    public void failureNotificationVisible() {
        failureNotification.shouldBe(visible, Duration.ofSeconds(10));
        successNotification.shouldBe(hidden);
    }

    public void incorrectFormatNote() {
        incorrectFormat.shouldBe(visible);
    }

    public void emptyFieldNote() {
        emptyField.shouldBe(visible);
    }

    public void invalidDateNote() {
        invalidDate.shouldBe(visible);
    }

    public void cardExpiredNote() {
        cardExpiredError.shouldBe(visible);
    }
}
