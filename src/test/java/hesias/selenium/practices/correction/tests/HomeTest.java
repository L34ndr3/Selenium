package hesias.selenium.practices.correction.tests;

import hesias.selenium.practices.correction.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Home action")
public class HomeTest extends BaseTest {

    @Test
    @Story("Accès à la page d'accueil")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Ouvre le site et vérifie que la page d'accueil s'affiche correctement.")
    public void testHomePageIsDisplayed() {
        boolean homeIsDisplayed = new HomePage(driver)
                .open()
                .isDisplayed();

        assertTrue(homeIsDisplayed);
    }

    @Test
    @Story("Gestion de la langue (Internationalisation)")
    @Severity(SeverityLevel.NORMAL)
    @Description("Change la langue du site en anglais et vérifie que la page reste accessible.")
    public void testHomePageChangeLanguageToEn() {
        boolean homeIsDisplayed = new HomePage(driver)
                .changeLanguage("en")
                .isDisplayed();

        assertTrue(homeIsDisplayed);
    }

    @Test
    @Story("Interaction avec le Carrousel (Slider)")
    @Severity(SeverityLevel.NORMAL)
    @Description("Vérifie que le clic sur la flèche gauche du slider active le dernier élément de la liste.")
    public void testSliderLeft() {
        boolean lastElementIsActive = new HomePage(driver)
                .open()
                .clickSliderLeftArrow();

        assertTrue(lastElementIsActive);
    }

    @Test
    @Story("Navigation vers une fiche jeu")
    @Severity(SeverityLevel.NORMAL)
    @Description("Clique sur le premier jeu du slider et vérifie la redirection vers la page de détails du jeu.")
    public void testClickFirstGameInSwiper() {
        boolean isOnGamePage = new HomePage(driver)
                .open()
                .clickFirstItemSlider()
                .isDisplayed();

        assertTrue(isOnGamePage);
    }

}