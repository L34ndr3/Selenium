package gog.tests;

import gog.pages.HomePage;
import gog.pages.SearchResultsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SearchTest extends BaseTest {

    // Exercice 3.4 : Test recherche "Cyberpunk"
    @Test
    public void testSearchCyberpunk() {

        String currentUrl = new HomePage(driver)
                .open()
                .searchGame("Cyberpunk")
                .getCurrentUrl();

        Assertions.assertTrue(currentUrl.contains("Cyberpunk"),
                "L'URL de la page de résultats devrait contenir 'Cyberpunk'");
    }

    // Exercice 3.5 : Test recherche "Binding of"
    @Test
    public void testSearchBindingOf() {
        Assertions.assertTrue(
                new HomePage(driver)
                        .open()
                        .searchGame("Binding of")
                        .isDisplayed(),
                "La grille de résultats devrait être visible après la recherche"
        );
    }

    //Exercice 4.2 : Test avec vérification du titre
    @Test
    public void testFirstResultContainsWitcher() {
        SearchResultsPage resultsPage = new HomePage(driver)
                .open()
                .searchGame("Witcher");

        String actualTitle = resultsPage.getFirstGameTitle();

        Assertions.assertTrue(actualTitle.toLowerCase().contains("witcher"),
                "Le titre du premier résultat devrait contenir 'Witcher', mais a trouvé : " + actualTitle);
    }

    @Test
    public void testFilterGoodOldGames() {
        SearchResultsPage resultsPage = new HomePage(driver)
                .open()
                .searchGame("RPG");

        resultsPage.filterByGoodOldGames();

        Assertions.assertTrue(resultsPage.isGoodOldGamesFilterDisplayed(),
                "Le filtre 'Good Old Games' devrait être visible dans les filtres actifs en haut de page");
    }
}