package hesias.selenium.practices.correction.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext; // Important pour détecter l'échec
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    protected void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        // Gestion du mode Headless (sans interface graphique)
        String headless = System.getenv("HEADLESS");
        if ("true".equalsIgnoreCase(headless)) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
        }

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // CORRECTION IMPORTANTE : Initialisation du Wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    protected void down(ExtensionContext context) {
        // On vérifie si une exception a eu lieu (test échoué)
        if (context.getExecutionException().isPresent()) {
            saveScreenshot();
        }

        if (driver != null) {
            driver.quit();
        }
    }

    // Annotation Allure pour attacher le retour de la méthode au rapport
    @Attachment(value = "Screenshot on Failure", type = "image/png")
    public byte[] saveScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    protected WebElement waitUntil(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected String getText(By locator) {
        return waitUntil(locator).getText();
    }

    protected void type(By locator, String text) {
        WebElement element = waitUntil(locator);
        element.clear();
        element.sendKeys(text);
    }
}