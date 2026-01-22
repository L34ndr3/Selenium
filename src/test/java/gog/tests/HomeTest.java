package gog.tests;

import gog.pages.GamePage;
import gog.pages.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeTest extends BaseTest {

    @Test
    public void testHomePageIsDisplayed() {
        // Instanciation et ouverture (Chaînage grâce au return this dans open())
        HomePage homePage = new HomePage(driver).open();

        // "Vérifier que la page s'affiche correctement"
        assertTrue(driver.getCurrentUrl().contains("gog.com"),
                "L'URL devrait contenir gog.com");
    }

    @Test
    public void testSliderLeft() throws InterruptedException {
        HomePage homePage = new HomePage(driver).open();
        int indexBefore = homePage.getActiveSlideIndex();
        homePage.clickPreviousSlide();
        int indexAfter = homePage.getActiveSlideIndex();
        Assertions.assertNotEquals(indexBefore, indexAfter,
                "Le slider aurait dû changer de slide, mais l'index est resté le même.");
    }

    @Test
    public void testGoToGamePage() {
        GamePage gamePage = new HomePage(driver)
                .open()
                .clickFirstGame();

        String title = gamePage.getTitle();

        Assertions.assertFalse(title.isEmpty(), "La page du jeu devrait afficher un titre");
    }


}