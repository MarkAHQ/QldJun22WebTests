package com.accesshq.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.NumberFormat;
import java.text.ParseException;

public class Planet {
    private final WebElement planetElement;

    public Planet(WebElement planetElement) {
        this.planetElement = planetElement;
    }

    public String getName() {
        return planetElement.findElement(By.className("name")).getText();
    }

    public void clickExplore() {
        planetElement.findElement(By.tagName("button")).click();
    }

    public double getRadius() {
        var radiusText = planetElement.findElement(By.className("radius")).getText();
        double result;
        try {
            result = NumberFormat.getInstance()
                    .parse(radiusText)
                    .doubleValue();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}
