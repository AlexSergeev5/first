package org.example;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {
    private static WebDriver driver = new ChromeDriver();

    @Test
    public void test() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.mts.by/");
        String telephoneNumber = "291032519";
        String amount = "111.22";
        String email = "test@test.ru";
        driver.findElement(By.id("connection-phone")).sendKeys(telephoneNumber);
        driver.findElement(By.id("connection-sum")).sendKeys(amount);
        driver.findElement(By.id("connection-email")).sendKeys(email);
        driver.findElement(By.id("cookie-agree")).click();
        driver.findElement(By.xpath("//button[@type='submit']")).click();


        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@src='https://checkout.bepaid.by/widget_v2/index.html']")));
        String amountInModal = driver.findElement(By.cssSelector(".header__payment-amount")).getText();
        String amountInButtonModal = driver.findElement(By.xpath("//button[@type='submit']")).getText();
        String numberInModal = driver.findElement(By.cssSelector(".header__payment-info")).getText();

        assertTrue(amountInModal.contains(amount));
        assertTrue(amountInButtonModal.contains(amount));
        assertTrue(numberInModal.contains(telephoneNumber));
        driver.quit();
    }
}