package com.adactin.tests;

import com.adactin.base.BaseTest;
import com.adactin.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(priority = 2, groups = {"smoke"})
    public void verifyLoginFields() {
        LoginPage loginPage = new LoginPage(getDriver());
        Assert.assertTrue(loginPage.isUsernameDisplayed(), "Username field is not displayed");
        Assert.assertTrue(loginPage.isPasswordDisplayed(), "Password field is not displayed");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button is not displayed");
    }

    @Test(priority = 3, groups = {"regression"})
    public void verifyInvalidLogin() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login("invalid_user_999", "invalid_password_999");
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertTrue(errorMessage.contains("Invalid Login details or Your Password might have expired."), "Expected error message not found. Actual: " + errorMessage);
    }
}
