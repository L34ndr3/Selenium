package gog.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GamePage extends BasePage {

    private final By gameTitleH1 = By.cssSelector("h1.productcard-basics__title, h1.product-card__title");


    public GamePage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return getText(gameTitleH1);
    }


}