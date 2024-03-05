package stepdefinitions.filmdetails;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.FilmDetails;
import org.assertj.core.api.Assertions;
import service.FilmDetailsService;

import java.util.*;

public class FilmDetailsSteps {

    FilmDetailsService service = new FilmDetailsService();
    List<FilmDetails> listOfFilmDetails;

    FilmDetails filmDetails;
    @When("I request the film details records")
    public void iRequestTheFilmDetailsRecords() {
        listOfFilmDetails = service.getAllFilmDetails();
    }

    @When("I request the film details with title {string}")
    public void iRequestTheFilmDetailsWithTitle(String title) {
        filmDetails = service.getFilmByTitle(title);
    }

    @Then("Validate that the size of film details records is {int}")
    public void validateThatTheSizeOfFilmDetailsRecordsIs(int sizeOfFilmDetails) {
        Assertions.assertThat(listOfFilmDetails.size()).isEqualTo(sizeOfFilmDetails);
    }

    @And("Validate that for all films number of actors should be greater than zero")
    public void validateThatForAllFilmsNumberOfActorsShouldBeGreaterThan() {
        for (FilmDetails listOfFilmDetail : listOfFilmDetails) {
            List<String> actors = Arrays.asList(((String) (listOfFilmDetail.getListOfColumnObjects().get("actor_list")))
                    .trim().split(", "));

            System.out.println(actors);

            Assertions.assertThat(actors.size()).isGreaterThan(0);
        }

    }

    @And("Validate that release years of films can not be null")
    public void validateThatReleaseYearsOfFilmsCanNotBeNull() {
        for (FilmDetails listOfFilmDetail : listOfFilmDetails) {
            Assertions.assertThat(listOfFilmDetail.getListOfColumnObjects().get("release_year")).isNotNull();
        }
    }

    @And("Validate that category of films can not be null")
    public void validateThatCategoryOfFilmsCanNotBeNull() {
        for (FilmDetails listOfFilmDetail : listOfFilmDetails) {
            Assertions.assertThat(listOfFilmDetail.getListOfColumnObjects().get("category")).isNotNull();
        }
    }

    @And("Validate that film titles should be unique")
    public void validateThatFilmTitlesShouldBeUnique() {
        List<String> filmTitles = new ArrayList<>();
        for (FilmDetails listOfFilmDetail : listOfFilmDetails) {
           filmTitles.add((String) listOfFilmDetail.getListOfColumnObjects().get("title"));
        }
        Set<String> titleOfFilms = new HashSet<>(filmTitles);

        Assertions.assertThat(filmTitles.size()).isEqualTo(titleOfFilms.size());
    }

    @And("Validate that for specified film film actors list should be like {string}")
    public void validateThatForSpecifiedFilmFilmActorsListShouldBeLike(String actors) {
        List<String> actualActors = Arrays.asList(((String) (filmDetails.getListOfColumnObjects().get("actor_list")))
                    .trim().split(", "));
        List<String> expectedActors = Arrays.asList(actors.split(", "));

        Assertions.assertThat(actualActors).isEqualTo(expectedActors);
    }
}
