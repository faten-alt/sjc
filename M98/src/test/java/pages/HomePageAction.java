package pages;

import com.Base;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.File;
import java.time.Duration;

public class HomePageAction extends Base {
    private WebDriverWait wait;
    private JavascriptExecutor js;

    // ===== Constructor =====
    public HomePageAction(WebDriver driver) {
        this.driver = driver;  // assign the driver from the test to this page
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        this.js = (JavascriptExecutor) driver;
       }


    // ===== Locators =====
    private By myServiceCard =  By.cssSelector("div.select-card div.title");
    private By certificateBefore1998 = By.xpath("//div[contains(@class,'float')]//div[contains(@class,'ar_text') and contains(text(),'1998')]");
    private By nextButton = By.xpath("//button[contains(@class,'btn') and normalize-space()='التـــــــالـــــي']");
    private By dropdown = By.xpath("(//span[contains(@class,'select2-selection__arrow')])[1]");
    private By firstOption = By.xpath("(//ul[contains(@class,'select2-results__options')]//li[contains(@class,'select2-results__option')])[1]");
    private By qidField = By.id("PersonalInfo.qid");
    private By dateField = By.xpath("//input[@type='date' and contains(@class,'dater')]");

    // ===== Generic Safe Click =====
    public void safeClick(By locator) {
        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(locator)
            );

            js.executeScript(
                    "arguments[0].scrollIntoView({block:'center'});",
                    element
            );

            //  لا تستخدم elementToBeClickable
            //  Angular ما بحبه

            js.executeScript("arguments[0].click();", element);

        } catch (Exception e) {
            throw new RuntimeException(" Failed to click element: " + locator, e);
        }
    }

    // ===== Actions =====
    public void clickOnMyService() {

        waitForLoader();
        // استخدم نفس الـ locator المعرّف فوق
        WebElement card = wait.until(
                ExpectedConditions.visibilityOfElementLocated(myServiceCard)
        );

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                card
        );

        js.executeScript("arguments[0].click();", card);
    }
    public void clickOnCertificateBefore1998() {
        waitForLoader();
        safeClick(certificateBefore1998);
    }

    public void submitService() throws InterruptedException, AWTException {
        String fileName = "no.png"; // your file inside src/test/resources/image
        String filePath = new File(
                getClass().getClassLoader().getResource("image/" + fileName).getFile()
        ).getAbsolutePath();
        // انتظر اللودر يختفي
        waitForLoader();
        waitForPageReady();
        Thread.sleep(4500);
        WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        // scroll + click
        js.executeScript("arguments[0].scrollIntoView({block:'center'});", nextBtn);
        js.executeScript("arguments[0].click();", nextBtn);
        waitForLoader();
        waitForPageReady();
        Thread.sleep(2500);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        By loader = By.cssSelector(".loader-overlay");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
        By select2Box = By.xpath("(//span[contains(@class,'select2-selection--single')])[1]");
        wait.until(ExpectedConditions.elementToBeClickable(select2Box)).click();
        By firstOption = By.xpath(
                "//ul[contains(@class,'select2-results__options')]" +
                        "//li[contains(@class,'select2-results__option') and not(contains(@class,'select2-results__option--disabled'))][1]");
        WebElement option = wait.until(ExpectedConditions.visibilityOfElementLocated(firstOption));
        option.click();
        // QID
        waitForPageReady();
        Thread.sleep(2500);
        WebElement qidField = driver.findElement(By.id("PersonalInfo.qid"));
        WebElement dateField = driver.findElement(By.cssSelector("input[type='date']"));
        WebElement firstName = driver.findElement(By.id("PersonalData.first_name"));
// ادخال البيانات
        qidField.clear();
        qidField.sendKeys("29363405463");
        dateField.clear();
        dateField.sendKeys("2029-12-20"); // صيغة صحيحة yyyy-MM-dd
// ارسل event change لكل الحقول
        js.executeScript("arguments[0].dispatchEvent(new Event('change'))", qidField);
        js.executeScript("arguments[0].dispatchEvent(new Event('change'))", dateField);
// انتظر الاسم يرجع
        wait.until(d -> !firstName.getAttribute("value").isEmpty());
// اطبع الاسم
        System.out.println("الاسم المسترجع: " + firstName.getAttribute("value"));
        Thread.sleep(2800);
        By qualification = By.xpath("(//span[contains(@class,'select2-selection--single')])[2]");
        wait.until(ExpectedConditions.elementToBeClickable(qualification)).click();
        By secondoption = By.xpath(
                "(//ul[contains(@class,'select2-results__options')]" +
                        "//li[contains(@class,'select2-results__option') and not(contains(@class,'select2-results__option--disabled'))])[2]");
        WebElement optionsecond = wait.until(ExpectedConditions.visibilityOfElementLocated(secondoption));
        optionsecond.click();
        By job = By.xpath("(//span[contains(@class,'select2-selection__arrow')])[3]");
        wait.until(ExpectedConditions.elementToBeClickable(job)).click();
        By thirdoption = By.xpath("(//ul[contains(@class,'select2-results__options')]//li[contains(@class,'select2-results__option')])[3]");
        WebElement optionthird = wait.until(ExpectedConditions.visibilityOfElementLocated(thirdoption));
        optionthird.click();
        By secondnext = By.xpath("//button[normalize-space()='التـــــــالـــــي']");
        wait.until(ExpectedConditions.elementToBeClickable(secondnext)).click();
        waitForLoader();
        waitForPageReady();
        By marriageDate = By.id("actual_marriage_date");
        WebElement date2 = wait.until(
                ExpectedConditions.visibilityOfElementLocated(marriageDate)
        );
// امسح القيمة أولًا
        js.executeScript("arguments[0].value='';", date2);
// أدخل التاريخ (YYYY-MM-DD)
        js.executeScript("arguments[0].value='1997-06-15';", date2);
        driver.findElement(By.xpath("(//span[contains(@class,'select2-selection--single')])[1]")).click();
        WebElement Qatar = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[text()='قطر']")
        ));
        Qatar.click();
        driver.findElement(By.name("contract_number")).sendKeys("6001");
        driver.findElement(By.xpath("//button[normalize-space()='التـــــــالـــــي']")).click();
        waitForLoader();
        waitForPageReady();
        Thread.sleep(1200);
        WebElement uploadInput = driver.findElement(By.id("hsbIdentifiaction"));
        uploadInput.sendKeys(filePath);
        Thread.sleep(1000);
        WebElement uploadInput2 = driver.findElement(By.id("wifeIdentifiaction"));
        uploadInput2.sendKeys(filePath);
        Thread.sleep(2000);
        WebElement yesLabel = driver.findElement(By.cssSelector("label[for='yes']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", yesLabel);
        yesLabel.click();
        Thread.sleep(2000);
        WebElement uploadInput3 = driver.findElement(By.id("mrgCOntract"));
        js.executeScript("arguments[0].scrollIntoView(true);", uploadInput3);
        uploadInput3.sendKeys(filePath);
        Thread.sleep(1000);
        driver.findElement(By.id("confirmPopup")).click();
        Thread.sleep(2000);
    }
}
