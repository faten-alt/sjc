package stepDefinitions;

import com.Base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.LoginPageAction;



public class LoginSteps extends Base {
    private LoginPageAction loginPageAction;

    @Given("user is on login page")
    public void user_is_on_login_page()  throws InterruptedException {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://sjcmv2tweb01.sjc.gov.qa:7443/SJC/#/");
        loginPageAction = new LoginPageAction(driver);
       // loginPageAction.safeClick(loginIntroButton);
        // زر تسجيل الدخول الأول
      loginPageAction.clickIntroLoginButton();

    }


    @When("user enters username {string} and password {string}")
    public void user_enters_username_and_password(String username, String password) throws InterruptedException {

        loginPageAction.enterNationalId(username);
        loginPageAction.enterPassword(password);
        loginPageAction.clickLogin();

        loginPageAction.enterOtp("1234");
        loginPageAction.submitOtp();
    }

    @Then("user should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        loginPageAction.verifyLoginAndClose("28888600066");}


}