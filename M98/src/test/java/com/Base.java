package com;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;


public class Base {
    protected static  WebDriver driver;
    private WebDriverWait wait;

    protected void waitForPageReady() {
        new WebDriverWait(driver, Duration.ofSeconds(40))
                .until(d ->
                        ((JavascriptExecutor) d)
                                .executeScript("return document.readyState")
                                .equals("complete")
                );
    }

    // انتظر اللودر لو موجود (وإذا مش موجود ما يعلق)
    protected void waitForLoader() {
        try {
            // إذا فيه loader أو overlay
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".spinner,.loading,.overlay")));
        } catch (Exception ignored) {
            // لو ما لقي شيء نتجاهل
        }
    }

    // كليك ذكي (JS)
    protected void jsClick(By locator) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(40))
                .until(d -> d.findElement(locator));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    public void uploadFileWithRobot(String filePath) throws AWTException, InterruptedException {
        // Copy the file path to clipboard
        StringSelection stringSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

        Robot robot = new Robot();
        robot.setAutoDelay(500);

        // Press CTRL+V to paste the path
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        // Press Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}