package hesias.selenium.practices.correction.tests;

import hesias.selenium.practices.correction.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Feature("Search action")
public class SearchTest extends BaseTest {

    @Test
    @Story("Recherche simple par nom")
    @Severity(SeverityLevel.NORMAL)
    @Description("Vérifie que la recherche du jeu 'Cyberpunk' affiche bien la page de résultats.")
    public void testSearchCyberpunk() {
        boolean pageResultIsDisplayed = new HomePage(driver)
                .searchGame("Cyberpunk")
                .isDisplayed();

        assertTrue(pageResultIsDisplayed);
    }

    @Test
    @Story("Recherche par mots-clés partiels")
    @Severity(SeverityLevel.NORMAL)
    @Description("Vérifie que la recherche avec les termes 'Binding of' retourne une liste de résultats.")
    public void testSearchBindingOf() {
        boolean pageResultIsDisplayed = new HomePage(driver)
                .searchGame("Binding of")
                .isDisplayed();

        assertTrue(pageResultIsDisplayed);
    }

    @Test
    @Story("Pertinence des résultats de recherche")
    @Severity(SeverityLevel.NORMAL)
    @Description("Vérifie que le premier résultat d'une recherche sur 'Witcher' contient bien ce mot dans son titre.")
    public void testFirstResultContainsWitcher() {
        String firstGameTitle = new HomePage(driver)
                .searchGame("Witcher")
                .getFirstGameTitle();

        assertTrue(firstGameTitle.contains("Witcher"));
    }

    @Test
    @Story("Utilisation des filtres de recherche")
    @Severity(SeverityLevel.NORMAL)
    @Description("Vérifie que le filtre 'Good Old Games' est actif ou peut être sélectionné après une recherche.")
    public void testFilterGoodOldGames() {
        boolean isFilterActive = new HomePage(driver)
                .searchGame("Binding of")
                .isFilterGoodOldGamesActive();

        assertTrue(isFilterActive);
    }

    @Test
    @Story("Réinitialisation des filtres")
    @Severity(SeverityLevel.NORMAL)
    @Description("Vérifie que le bouton de réinitialisation des filtres fonctionne correctement.")
    public void testResetFilters() {
        boolean hasResetFiler = new HomePage(driver)
                .searchGame("Binding of")
                .hasResetFilters();

        assertTrue(hasResetFiler);
    }

}