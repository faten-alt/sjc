package com;

import io.cucumber.java.After;

import static com.Base.driver;

public class Hooks {
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
