package com.accesshq.tests;

import com.accesshq.datamodels.State;
import com.accesshq.model.Form;
import com.accesshq.model.NavBar;
import com.accesshq.model.PlanetPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class TestPlaygroundSuite {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://d18u5zoaatmpxx.cloudfront.net/");
    }

    @Test
    public void SubmitNameTest() {
        driver.findElement(By.id("forename")).sendKeys("Mark");
        driver.findElement(By.id("submit")).click();

        var popupElement = driver.findElement(By.className("popup-message"));
        new WebDriverWait(driver, Duration.ofSeconds(1)).until(ExpectedConditions.visibilityOf(popupElement));
        Assertions.assertEquals("Hello Mark", popupElement.getText());
    }

    @Test
    public void VerifySubmitForm() {
        // Arrange
        driver.findElement(By.cssSelector("[aria-label=forms]")).click();

        var form = new Form(driver);

        form.setName("Mark");
        form.setEmail("mark.arnold@accesshq.com");
        form.setState(State.QLD);
        form.clickAgree();

        // Act
        form.clickSubmit();

        // Assert
        Assertions.assertEquals("Thanks for your feedback Mark", form.getPopupText());
    }

    @Test
    public void VerifyExploringEarth() {
        // Arrange
        new NavBar(driver).clickPlanets();

        // Act
        var planetPage = new PlanetPage(driver);
        planetPage.clickExplore("Earth");

        // Assert
        Assertions.assertEquals("Exploring Earth", planetPage.getPopupText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}