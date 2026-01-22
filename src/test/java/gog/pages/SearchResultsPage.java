package gog.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage extends BasePage{
    private final By resultsGrid = By.cssSelector(".product-tile, app-product-tile, .list-row__title");

    private final By gameTitle = By.cssSelector(".product-tile__title, .list-row__title");

    private final By GoodOldGames = By.cssSelector("input[selenium-id='showOnlyPreservedGamesCheckbox']");
    private final By Checkbox = By.cssSelector("input[name='onlypreservedgames']");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDisplayed() {
        try {
            return waitUntil(resultsGrid).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getFirstGameTitle() {
        return getText(gameTitle);
    }

    public SearchResultsPage filterByGoodOldGames() {
        waitClick(Checkbox).click();
        return this;
    }

    public boolean isGoodOldGamesFilterDisplayed() {
        try {
            return waitUntil(GoodOldGames).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
