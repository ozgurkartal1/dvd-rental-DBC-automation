package stepdefinitions.staff;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Staff;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import service.StaffService;

import java.util.ArrayList;
import java.util.List;

public class StaffSteps {

    StaffService service = new StaffService();
    List<Staff> listOfStaffs;
    Staff staff;
    @When("I request the staff records")
    public void iRequestTheStaffRecords() {
        listOfStaffs = service.getAllStaffs();
    }

    @When("I request the staff records with id {int}")
    public void iRequestTheStaffRecordsWithIdStaffId(int staffId) {
       staff = service.getStaffById(staffId);
    }

    @When("I request the activity records for all staffs")
    public void iRequestTheActivityRecordsForAllStaffs() {
        listOfStaffs = service.getActiveFeatureForStaffs();
    }

    @Then("Validate that the size of staff records is {int}")
    public void validateThatTheSizeOfStaffRecordsIs(int sizeOfStaffs) {
        Assertions.assertThat(listOfStaffs.size()).isEqualTo(sizeOfStaffs);
    }

    @And("The staff emails must contain staff firstname and lastname")
    public void theStaffEmailsMustContainStaffFirstnameAndLastname() {
        listOfStaffs.forEach(staff1 -> {
            Assertions.assertThat(((String) (staff1.getListOfColumnObjects().get("email"))).
                    contains(staff1.getListOfColumnObjects().get("first_name") + "." + staff1.getListOfColumnObjects().get("last_name")))
                    .isTrue();
        });
    }

    @Then("Validate that specified staff's firstname {string} and lastname {string}")
    public void validateThatSpecifiedStaffSFirstnameAndLastname(String firstname, String lastname) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(staff.getListOfColumnObjects().get("first_name")).isEqualTo(firstname);
        softAssertions.assertThat(staff.getListOfColumnObjects().get("last_name")).isEqualTo(lastname);

        softAssertions.assertAll();
    }

    @Then("validate that the all staffs are active to work")
    public void validateThatTheAllStaffsAreActiveToWork() {
        listOfStaffs.forEach(staff1 -> {
            Assertions.assertThat((Boolean) staff1.getListOfColumnObjects().get("active")).isTrue();
        });
    }
}
