package gog.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage {

    // Bouton loupe
    private final By submitBtn = By.cssSelector(".menu-icon-svg--search");

    // Champ de recherche
    private final By searchInput = By.cssSelector(".menu-search-input__field");

    //Bouton cookie
    private final By refuseCookiesBtn = By.cssSelector("button#CybotCookiebotDialogBodyButtonDecline");

    //nextslidebtn
    private final By nextSlideBtn = By.cssSelector("div[aria-label='Next slide']");
    //prevslidebtn
    private final By prevSlideBtn = By.cssSelector("div[aria-label='Previous slide']");
    //pagination
    private final By paginationBullets = By.cssSelector(".swiper-pagination-bullet");

    //gametitle
    private final By firstGameTitle = By.cssSelector("a[selenium-id='productTile']");

    //Swiper
    private final By SwiperIndex = By.cssSelector("item-slider[selenium-id='productsSectionSlider']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage open() {
        driver.get("https://www.gog.com/fr/");
        waitClick(refuseCookiesBtn).click();
        return this; // Retourne this pour le chaînage
    }

    public SearchResultsPage searchGame(String gameName) {
        waitClick(submitBtn).click();
        type(searchInput, gameName + Keys.ENTER);
        return new SearchResultsPage(driver);
    }

    public HomePage changeLanguage(String locale) {
        driver.get("https://www.gog.com/" + locale + "/");
        return this;
    }

    public HomePage clickNextSlide() {
        waitClick(nextSlideBtn).click();
        return this;
    }

    public HomePage clickPreviousSlide() {
        waitClick(prevSlideBtn).click();
        return this;
    }

    public int getActiveSlideIndex() {
        List<WebElement> bullets = driver.findElements(paginationBullets);

        for (int i = 0; i < bullets.size(); i++) {
            String className = bullets.get(i).getAttribute("class");
            if (className.contains("swiper-pagination-bullet-active")) {
                return i;
            }
        }
        return -1;
    }

    public GamePage clickFirstGame() {
        scrollTo(SwiperIndex);
        waitClick(firstGameTitle).click();
        return new GamePage(driver);
    }


}