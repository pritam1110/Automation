package com.adactin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;

    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.id("login");
    private final By authErrorMsg = By.cssSelector(".auth_error");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        WebElement userElem = driver.findElement(usernameInput);
        userElem.clear();
        userElem.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement passElem = driver.findElement(passwordInput);
        passElem.clear();
        passElem.sendKeys(password);
    }

    public SearchHotelPage clickLogin() {
        driver.findElement(loginButton).click();
        return new SearchHotelPage(driver);
    }

    public SearchHotelPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLogin();
    }

    public boolean isUsernameDisplayed() {
        return driver.findElement(usernameInput).isDisplayed();
    }

    public boolean isPasswordDisplayed() {
        return driver.findElement(passwordInput).isDisplayed();
    }

    public boolean isLoginButtonDisplayed() {
        return driver.findElement(loginButton).isDisplayed();
    }

    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement errorElem = wait.until(ExpectedConditions.visibilityOfElementLocated(authErrorMsg));
        return errorElem.getText();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
