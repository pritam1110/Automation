package com.adactin.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public final class ScreenshotUtil {

    private ScreenshotUtil() {
        // Prevent instantiation
    }

    public static void captureScreenshot(WebDriver driver, String testName) {
        if (driver == null) {
            System.err.println("WebDriver instance is null. Cannot capture screenshot.");
            return;
        }

        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File targetDir = new File("target/screenshots");

        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        File destination = new File(targetDir, testName + ".png");

        try {
            Files.copy(screenshot.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved to: " + destination.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
