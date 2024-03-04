package stepdefinitions.rental;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Rental;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import service.RentalService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public class RentalSteps {

    private final RentalService service = new RentalService();

    private List<Rental> listOfRentals;

    private Rental rental;
    @When("I request the rental records")
    public void iRequestTheRentalRecords() {
        listOfRentals = service.getAllRentals();
    }

    @When("I request the rental records with valid id {int}")
    public void iRequestTheRentalRecordsWithValidId(int rentalId) {
       rental = service.getRentalById(rentalId);
    }

    @When("I request for numbers of rentals per staffs")
    public void iRequestForNumbersOfRentalsPerStaffs() {
        listOfRentals = service.getNumberOfRentalsPerStaffs();
    }


    @Then("Validate that rental date is {string} and return date is {string}")
    public void validateThatRentalDateIsAndReturnDateIsReturnDate(String renda, String retda) {
        Timestamp rentalDate = Timestamp.valueOf(renda);
        Timestamp returnDate = Timestamp.valueOf(retda);

        Assertions.assertThat(rental.getListOfColumnObjects().get("rental_date")).isEqualTo(rentalDate);
        Assertions.assertThat(rental.getListOfColumnObjects().get("return_date")).isEqualTo(returnDate);
    }


    @Then("Validate that the size of different staffs is {int}")
    public void validateThatTheSizeOfDifferentStaffsIs(int numberOfDifStaffs) {
        Assertions.assertThat(listOfRentals.size()).isEqualTo(2);
    }

    @And("Validate that each staff must make at least one rental")
    public void validateThatEachStaffMustMakeAtLeastOneRental() {
        SoftAssertions softAssertions = new SoftAssertions();
        listOfRentals.forEach(rental1 -> {
            softAssertions.assertThat((Long) rental1.getListOfColumnObjects().get("count")).isGreaterThan(1);
        });
    }

    @Then("Validate that the size of rental records is {int}")
    public void validateThatTheSizeOfRentalRecordsIs(int sizeOfRental) {
        Assertions.assertThat(listOfRentals.size()).isEqualTo(sizeOfRental);
    }

    @And("Validate that each staff rental details is following:")
    public void validateThatEachStaffRentalDetailsIsFollowing(DataTable dt) {
        List<Map<String, Long>> maps = dt.asMaps(String.class, Long.class);
        SoftAssertions softAssertions = new SoftAssertions();
        maps.forEach(map -> {
            softAssertions.assertThat(listOfRentals.get(maps.indexOf(map)).getListOfColumnObjects().get("staff_id")).isEqualTo(map.get("staff_id").intValue());
            softAssertions.assertThat(listOfRentals.get(maps.indexOf(map)).getListOfColumnObjects().get("count")).isEqualTo(map.get("count"));
        });

        softAssertions.assertAll();

    }
}
