package com.accesshq.model;

import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PlanetPage {
    private final WebDriver driver;

    public PlanetPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getPopupText() {
        return getPopupMessage().getText();
    }

    private WebElement getPopupMessage() {
        return driver.findElement(By.className("popup-message"));
    }

    private void waitForPopupMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(1)).until(d ->
                d.findElement(By.className("popup-message")).isDisplayed());
    }

    private List<Planet> getPlanets() {
        var planets = new ArrayList<Planet>();
        for (var planetElement : driver.findElements(By.className("planet"))) {
            planets.add(new Planet(planetElement));
        }
        return planets;
    }

    public void clickExplore(Planet planet) {
        planet.clickExplore();
        new WebDriverWait(driver, Duration.ofSeconds(1)).until(d ->
                d.findElement(By.className("popup-message")).isDisplayed());
    }

    public Planet getPlanet(Predicate<Planet> testLogic) {
        for (WebElement planetElement : driver.findElements(By.className("planet"))) {
            var planet = new Planet(planetElement);
            if (testLogic.test(planet)) {
                return planet;
            }
        }

        throw new NotFoundException("Could not find planet");
    }
}
