package ru.netology;

import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BuyTest {
    @BeforeEach
    void openWebsite() {
        open("http://localhost:9999");
        $(By.xpath("//*[text()='Купить']")).click();
    }


}
