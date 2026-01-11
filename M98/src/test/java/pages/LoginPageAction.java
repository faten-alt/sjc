package pages;

import com.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPageAction extends Base {

    private WebDriverWait wait;
    private JavascriptExecutor js;

    public LoginPageAction(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        this.js = (JavascriptExecutor) driver;
    }

    // ================= LOCATORS =================

    private By loginIntroButton = By.xpath("//div[contains(@class,'step') and contains(@class,'intro')]//button[contains(@class,'alpha')]");
    private By nationalIdInput = By.xpath("//input[@id='id']");
    private By passwordInput = By.xpath("//input[@id='password']");
    private By loginButton = By.xpath("//button[contains(@class,'alpha') and normalize-space()='تسجيل الدخول']");
    private By otpInputs = By.xpath("//input[contains(@class,'otp-input')]");
    private By submitOtpButton = By.xpath("//button[normalize-space()='تقديــــم']");

    // ================= ACTIONS =================

    public void clickIntroLoginButton() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(loginIntroButton));
        btn.click();
       // jsClick(introLoginButton);
    }

    public void enterNationalId(String id) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(nationalIdInput));
        input.clear();
        safeSendKeys(nationalIdInput,id);
    }

    public void enterPassword(String password) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        input.clear();
        safeSendKeys(passwordInput,password);
    }

    public void clickLogin() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        btn.click();
    }

    public void enterOtp(String otp) {
        List<WebElement> inputs = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(otpInputs)
        );

        for (int i = 0; i < inputs.size(); i++) {
            inputs.get(i).sendKeys(String.valueOf(otp.charAt(i)));
        }
    }

    public void submitOtp() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(submitOtpButton));
        btn.click();
    }
    public void safeClick(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        for (int i = 0; i < 3; i++) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
                return;
            } catch (Exception e) {
                try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
            }
        }
        throw new RuntimeException("Element not clickable: " + locator);
    }

    public void safeSendKeys(By locator, String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        for (int i = 0; i < 3; i++) {
            try {
                WebElement element = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(locator)
                );

                element.clear();
                element.click();
                element.sendKeys(text);
                return;

            } catch (Exception e) {
                try { Thread.sleep(1500); } catch (InterruptedException ignored) {}
            }
        }
        throw new RuntimeException("Unable to send keys to: " + locator);
    }


    public void verifyLoginAndClose(String nationalId) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            WebElement userIdSpan = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//span[contains(@class,'ar_text') and contains(text(),'" + nationalId + "')]")
                    )
            );

            if (userIdSpan != null) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Login failed!");
            }
        } catch (Exception e) {
            System.out.println("Login failed! Element not found or timeout occurred.");
        } finally {
            if (driver != null) {
            }
        }
    }

}
