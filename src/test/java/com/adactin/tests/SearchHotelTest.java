package com.adactin.tests;

import com.adactin.base.BaseTest;
import com.adactin.pages.LoginPage;
import com.adactin.pages.SearchHotelPage;
import com.adactin.utils.ConfigReader;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class SearchHotelTest extends BaseTest {

    @Test(priority = 1, groups = {"regression", "e2e"})
    public void verifySearchHotelWithValidCredentials() {
        String username = ConfigReader.getProperty("username");
        String password = ConfigReader.getProperty("password");

        if (username.isEmpty() || password.isEmpty()) {
            throw new SkipException("Skipping test 'verifySearchHotelWithValidCredentials' because username or password configuration is missing.");
        }

        LoginPage loginPage = new LoginPage(getDriver());
        SearchHotelPage searchHotelPage = loginPage.login(username, password);

        Assert.assertTrue(searchHotelPage.isSearchPageDisplayed(), "Search Hotel page was not displayed after login.");

        searchHotelPage.selectLocation("Sydney");
        searchHotelPage.selectHotel("Hotel Creek");
        searchHotelPage.selectRoomType("Standard");
        searchHotelPage.selectRooms("1");
        searchHotelPage.enterCheckIn("25/10/2026");
        searchHotelPage.enterCheckOut("26/10/2026");
        searchHotelPage.selectAdults("1");
        searchHotelPage.selectChildren("0");
        
        searchHotelPage.clickSearch();
        
        Assert.assertTrue(getDriver().getTitle().contains("Select Hotel"), "Failed to navigate to Select Hotel page.");
    }
}
