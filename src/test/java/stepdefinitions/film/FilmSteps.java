package stepdefinitions.film;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Film;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import service.FilmService;
import utils.DBUtils;

import java.util.List;

public class FilmSteps {

    private static final Logger LOGGER = LogManager.getLogger(FilmSteps.class);

    private List<Film> filmList;

    private Film film;

    private final FilmService service = new FilmService();
    @Given("The database is accessible")
    public void theDatabaseIsAccessible() {
        DBUtils.getConnection();
        LOGGER.info("The connection is successful");
    }

    @When("I request the film records")
    public void iRequestTheFilmRecords() {
        filmList = service.getAllFilms();
    }

    @Then("Validate that the size of film records is {int}")
    public void validateThatTheSizeOfFilmRecordsIs(int sizeOfRecords) {
        Assertions.assertThat(filmList.size()).isEqualTo(sizeOfRecords);
    }

    @When("I request the film records with valid id {int}")
    public void iRequestTheFilmRecordsWithValidId(int filmId) {
        film = service.getAllFilmById(filmId);
    }

    @And("Validate that film title is {string} and release year is {int}")
    public void validateThatFilmTitleIsAndReleaseYearIs(String title, int releaseYear) {
        Assertions.assertThat(film.getListOfColumnObjects().get("title")).isEqualTo(title);
        Assertions.assertThat(film.getListOfColumnObjects().get("release_year")).isEqualTo(releaseYear);
    }

    @When("I request for the last updates of film records")
    public void iRequestForTheLastUpdatesOfFilmRecords() {
        filmList = service.getLastUpdateOfFilms();
    }

    @Then("All records of last updates size should be {int}")
    public void allRecordsOfLastUpdatesSizeShouldBe(int sizeOfLastUpdates) {
        Assertions.assertThat(filmList.size()).isEqualTo(sizeOfLastUpdates);
    }

    @And("Validate that all last updates can not be null")
    public void validateThatAllLastUpdatesCanNotBeNull() {
        SoftAssertions softAssertions = new SoftAssertions();
        filmList.forEach(film1 -> {
            softAssertions.assertThat(film1.getListOfColumnObjects().get("last_update")).isNotNull();
        });

        softAssertions.assertAll();
    }
}
