package stepdefinitions.payment;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Payment;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import service.PaymentService;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;

public class PaymentSteps {
    private PaymentService paymentService = new PaymentService();

    private List<Payment> payments;
    private Payment payment;

    @When("I request the payment records")
    public void iRequestThePaymentRecords() {
        payments = paymentService.getAllPayments();
    }

    @Then("the size of payment records should be {int}")
    public void theSizeOfPaymentRecordsShouldBe(int countOfPayments) {
        Assertions.assertThat(payments.size()).isEqualTo(countOfPayments);
    }

    @And("payment ids should be unique")
    public void paymentIdsShouldBeUnique() {
        List<Integer> paymentIds = new ArrayList<>();
        payments.forEach(payment1 -> {
            paymentIds.add((Integer)(payment1.getListOfColumnObjects().get("payment_id")));
        });
        Set<Integer> setPaymentIds = new HashSet<>(paymentIds);
        Assertions.assertThat(paymentIds.size()).isEqualTo(setPaymentIds.size());
    }

    @When("I request with three payment id as  {int}, {int}, {int}")
    public void iRequestWithThreePaymentIdAs(int paymentId1, int paymentId2, int paymentId3) {
        List<Integer> paymentIds = List.of(paymentId1,paymentId2,paymentId3);
        payments = paymentService.getPaymentByIds(paymentIds);
    }

    @Then("the payment record is in the following:")
    public void thePaymentRecordIsInTheFollowing(DataTable dt) {
        List<Map<String , String>> expectedPaymentsInfo = dt.asMaps(String.class, String.class);
        SoftAssertions softAssertions = new SoftAssertions();
        expectedPaymentsInfo.forEach(paymentInfo -> {
            softAssertions.assertThat(payments.get(expectedPaymentsInfo.indexOf(paymentInfo)).getListOfColumnObjects().get("payment_id"))
                    .isEqualTo(Integer.parseInt(paymentInfo.get("payment_id")));
            softAssertions.assertThat(payments.get(expectedPaymentsInfo.indexOf(paymentInfo)).getListOfColumnObjects().get("customer_id"))
                    .isEqualTo(Integer.parseInt(paymentInfo.get("customer_id")));
            softAssertions.assertThat(payments.get(expectedPaymentsInfo.indexOf(paymentInfo)).getListOfColumnObjects().get("staff_id"))
                    .isEqualTo(Integer.parseInt(paymentInfo.get("staff_id")));
            softAssertions.assertThat(payments.get(expectedPaymentsInfo.indexOf(paymentInfo)).getListOfColumnObjects().get("rental_id"))
                    .isEqualTo(Integer.parseInt(paymentInfo.get("rental_id")));
            softAssertions.assertThat(payments.get(expectedPaymentsInfo.indexOf(paymentInfo)).getListOfColumnObjects().get("amount"))
                    .isEqualTo(new BigDecimal(paymentInfo.get("amount")));
            softAssertions.assertThat(payments.get(expectedPaymentsInfo.indexOf(paymentInfo)).getListOfColumnObjects().get("payment_date"))
                    .isEqualTo(Timestamp.valueOf(paymentInfo.get("payment_date")));

        });
        softAssertions.assertAll();
    }

    @When("I request the count of payment records for different months name")
    public void iRequestTheCountOfPaymentRecordsForDifferentMonthsName() {
       payments = paymentService.getCountOfPaymentForDifferentMonth();
    }

    @And("the payment records should match with following details:")
    public void thePaymentRecordsShouldMatchWithFollowingDetails(DataTable dt) {
        List<Map<String , String>> expectedPaymentsInfo = dt.asMaps(String.class, String.class);
        SoftAssertions softAssertions = new SoftAssertions();
        expectedPaymentsInfo.forEach(paymentInfo -> {
            softAssertions.assertThat((payments.get(expectedPaymentsInfo.indexOf(paymentInfo)).getListOfColumnObjects().get("month") + "").trim())
                    .isEqualTo(paymentInfo.get("month"));
            softAssertions.assertThat(payments.get(expectedPaymentsInfo.indexOf(paymentInfo)).getListOfColumnObjects().get("count"))
                    .isEqualTo(Long.parseLong(paymentInfo.get("count")));

        });
        softAssertions.assertAll();
    }
}
