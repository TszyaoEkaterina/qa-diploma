package ru.netology.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.DataGenerator;
import ru.netology.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class BuyWithCreditTest {
    MainPage mainPage = new MainPage();
    DataGenerator generator = new DataGenerator();

    @BeforeAll
    static void openWebsite() {
        Configuration.browser = "chrome";
    }

    @BeforeEach
    void clickPayWithCard() {
        open("http://localhost:8080");
        mainPage.payWithCredit();
    }

    //happy path
    @Test
    void happyPathTest() {
        mainPage.fillCardData(generator.getApprovedCard());
        mainPage.clickContinue();
        mainPage.successNotificationVisible();
    }

    //declined card test
    @Test
    void shouldNotAcceptDeclinedCard() {
        mainPage.fillCardData(generator.getDeclinedCard());
        mainPage.clickContinue();
        mainPage.failureNotificationVisible();
    }

    //empty test
    @Test
    void emptyTest() {
        mainPage.clickContinue();
        mainPage.emptyFieldNote();
        mainPage.incorrectFormatNote();
    }

    //номер карты
    @Test
    void shouldNotPassWithoutCardNumber() {
        mainPage.fillCardData(generator.getCardWithoutCardNumber());
        mainPage.clickContinue();
        mainPage.incorrectFormatNote();
    }

    @Test
    void numberWithFewerDigitsShouldNotPass() {
        mainPage.fillCardData(generator.getCardWithoutCardNumber());
        mainPage.setCardNumber(generator.getNumberWithFewerDigits());
        mainPage.clickContinue();
        mainPage.incorrectFormatNote();
    }

    @Test
    void numberWithLettersShouldNotPass() {
        mainPage.fillCardData(generator.getCardWithoutCardNumber());
        mainPage.setCardNumber("xcddjxiaochaoddj");
        mainPage.clickContinue();
        mainPage.incorrectFormatNote();
    }

    @Test
    void numberWithSymbolsShouldNotPass() {
        mainPage.fillCardData(generator.getCardWithoutCardNumber());
        mainPage.setCardNumber("@$;/%?<=#>!+-,[}");
        mainPage.clickContinue();
        mainPage.incorrectFormatNote();
    }

    //Месяц
    @Test
    void shouldNotPassWithoutMonth() {
        mainPage.fillCardData(generator.getCardWithoutMonth());
        mainPage.clickContinue();
        mainPage.incorrectFormatNote();
    }

    @Test
    void monthWithLettersShouldNotPass() {
        mainPage.fillCardData(generator.getCardWithoutMonth());
        mainPage.setMonth("Jan");
        mainPage.clickContinue();
        mainPage.incorrectFormatNote();
    }

    @Test
    void monthWithSymbolsShouldNotPass() {
        mainPage.fillCardData(generator.getCardWithoutMonth());
        mainPage.setMonth("#%");
        mainPage.clickContinue();
        mainPage.incorrectFormatNote();
    }

    @Test
    void invalidMonth13ShouldNotPass() {
        mainPage.fillCardData(generator.getCardWithoutMonth());
        mainPage.setMonth("13");
        mainPage.clickContinue();
        mainPage.invalidDateNote();
    }

    @Test
    void validMonthInInvalidFormatShouldNotPass() {
        mainPage.fillCardData(generator.getCardWithoutMonth());
        mainPage.setMonth("1");
        mainPage.clickContinue();
        mainPage.incorrectFormatNote();
    }

    @Test
    void expiredDateShouldNotPass() {
        mainPage.fillCardData(generator.getCardWithoutDate());
        mainPage.setYear("22");
        mainPage.setMonth("01");
        mainPage.clickContinue();
        mainPage.invalidDateNote();
    }

    //Год
    @Test
    void yearWithin10YearsFromNowShouldPass() {
        mainPage.fillCardData(generator.getCardWithoutYear());
        mainPage.setYear("32");
        mainPage.clickContinue();
        mainPage.successNotificationVisible();
    }

    @Test
    void shouldNotPassWithoutYear() {
        mainPage.fillCardData(generator.getCardWithoutYear());
        mainPage.clickContinue();
        mainPage.incorrectFormatNote();
    }

    @Test
    void yearWithLettersShouldNotPass() {
        mainPage.fillCardData(generator.getCardWithoutYear());
        mainPage.setYear("tw");
        mainPage.clickContinue();
        mainPage.incorrectFormatNote();
    }

    @Test
    void yearWithSymbolsShouldNotPass() {
        mainPage.fillCardData(generator.getCardWithoutYear());
        mainPage.setYear("&*");
        mainPage.clickContinue();
        mainPage.incorrectFormatNote();
    }

    @Test
    void expiredYearShouldNotPass() {
        mainPage.fillCardData(generator.getCardWithoutYear());
        mainPage.setYear("21");
        mainPage.clickContinue();
        mainPage.cardExpiredNote();
    }

    //Владелец
    @Test
    void shouldNotPassWithoutOwner() {
        mainPage.fillCardData(generator.getCardWithoutOwner());
        mainPage.clickContinue();
        mainPage.emptyFieldNote();
    }

    @Test
    void ownerInRussianShouldNotPass() {
        mainPage.fillCardData(generator.getCardWithoutOwner());
        mainPage.setCardOwner("пуч");//TODO
        mainPage.clickContinue();
        mainPage.incorrectFormatNote();
    }

    @Test
    void ownerInSymbolsShouldNotPass() {
        mainPage.fillCardData(generator.getCardWithoutOwner());
        mainPage.setCardOwner("@%#&)");
        mainPage.clickContinue();
        mainPage.incorrectFormatNote();
    }

    @Test
    void ownerInNumbersShouldNotPass() {
        mainPage.fillCardData(generator.getCardWithoutOwner());
        mainPage.setCardOwner("6497532");
        mainPage.clickContinue();
        mainPage.incorrectFormatNote();
    }

    //CVC/CVV
    @Test
    void shouldNotPassWithoutCvv() {
        mainPage.fillCardData(generator.getCardWithoutCvv());
        mainPage.clickContinue();
        mainPage.incorrectFormatNote();
    }

    @Test
    void cvvWithFewerDigitsShouldNotPass() {
        mainPage.fillCardData(generator.getCardWithoutCvv());
        mainPage.setCvv("11");
        mainPage.clickContinue();
        mainPage.incorrectFormatNote();
    }

    @Test
    void cvvWithLettersShouldNotPass() {
        mainPage.fillCardData(generator.getCardWithoutCvv());
        mainPage.setCvv("abc");
        mainPage.clickContinue();
        mainPage.incorrectFormatNote();
    }

    @Test
    void cvvWithSymbolsShouldNotPass() {
        mainPage.fillCardData(generator.getCardWithoutCvv());
        mainPage.setCvv("#$%");
        mainPage.clickContinue();
        mainPage.incorrectFormatNote();
    }
}

