package com.accesshq.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class PlanetPage {
    private final WebDriver driver;

    public PlanetPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickExplore(String planetName) {
        for (Planet planet : getPlanets()) {
            if (planet.getName().equalsIgnoreCase(planetName)) {
                planet.clickExplore();
                waitForPopupMessage();
                break;
            }
        }
    }

    public String getPopupText() {
        return getPopupMessage().getText();
    }

    private WebElement getPopupMessage() {
        return driver.findElement(By.className("popup-message"));
    }

    private void waitForPopupMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOf(
                driver.findElement(By.className("popup-message"))));
    }

    private List<Planet> getPlanets() {
        var planets = new ArrayList<Planet>();
        for (var planetElement : driver.findElements(By.className("planet"))) {
            planets.add(new Planet(planetElement));
        }
        return planets;
    }
}
