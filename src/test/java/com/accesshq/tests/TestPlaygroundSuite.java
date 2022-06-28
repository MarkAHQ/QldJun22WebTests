package com.accesshq.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestPlaygroundSuite {

    @Test
    public void SubmitNameTest() {
        var driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://d18u5zoaatmpxx.cloudfront.net/");

        driver.findElement(By.id("forename")).sendKeys("Mark");
        driver.findElement(By.id("submit")).click();

        driver.quit();
    }
}