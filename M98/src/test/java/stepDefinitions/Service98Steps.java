package stepDefinitions;

import com.Base;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.HomePageAction;
import pages.LoginPageAction;

import java.awt.*;
import java.sql.DriverManager;

public class Service98Steps  extends Base {
  HomePageAction homePage = new HomePageAction(driver);

    public Service98Steps() {
        // constructor بدون arguments
    }
    @When("press on MyServices")
    public void press_on_my_services() throws InterruptedException {

        homePage.clickOnMyService();
    }


    @Then("navigated to the EightyNine Service")
    public void navigated_to_the_eighty_nine_service() {
        homePage.clickOnCertificateBefore1998();
    }

    @And("submit the service with pleasure")
    public void submit_the_service_with_pleasure() throws InterruptedException, AWTException {
        homePage.submitService();
    }
}
