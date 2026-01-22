package hesias.selenium.practices.correction.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WorkPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private final String WORK_URL = "https://www.gog.com/work";

    private By jobOfferInputLocator = By.cssSelector("input[placeholder*='Search']");

    public WorkPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WorkPage goToJobOfferInput() {

        driver.get(WORK_URL);

        WebElement searchInput = wait.until(ExpectedConditions.presenceOfElementLocated(jobOfferInputLocator));

        new Actions(driver)
                .scrollToElement(searchInput)
                .perform();

        return this;
    }

    public WorkPage searchForJobOffer(String job) {
        goToJobOfferInput();

        WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(jobOfferInputLocator));

        searchInput.clear();
        searchInput.sendKeys(job);

        return this;
    }
}