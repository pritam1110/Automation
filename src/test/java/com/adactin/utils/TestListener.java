package com.adactin.utils;

import com.adactin.base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        if (testClass instanceof BaseTest) {
            WebDriver driver = ((BaseTest) testClass).getDriver();
            String testMethodName = result.getName();
            ScreenshotUtil.captureScreenshot(driver, testMethodName);
        }
    }
}
