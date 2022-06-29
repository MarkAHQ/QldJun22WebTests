package com.accesshq.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavBar {
    private final WebDriver driver;

    public NavBar(WebDriver driver) {
        this.driver = driver;
    }

    public void clickPlanets() {
        driver.findElement(By.cssSelector("[aria-label=planets]")).click();
    }
}
