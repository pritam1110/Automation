package com.adactin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SearchHotelPage {

    private final WebDriver driver;

    private final By locationDropdown = By.id("location");
    private final By hotelsDropdown = By.id("hotels");
    private final By roomTypeDropdown = By.id("room_type");
    private final By roomNosDropdown = By.id("room_nos");
    private final By datepickInInput = By.id("datepick_in");
    private final By datepickOutInput = By.id("datepick_out");
    private final By adultRoomDropdown = By.id("adult_room");
    private final By childRoomDropdown = By.id("child_room");
    private final By searchBtn = By.id("Submit");
    private final By welcomeUsername = By.id("username_show");

    public SearchHotelPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectLocation(String location) {
        new Select(driver.findElement(locationDropdown)).selectByValue(location);
    }

    public void selectHotel(String hotel) {
        new Select(driver.findElement(hotelsDropdown)).selectByValue(hotel);
    }

    public void selectRoomType(String roomType) {
        new Select(driver.findElement(roomTypeDropdown)).selectByValue(roomType);
    }

    public void selectRooms(String numberOfRooms) {
        new Select(driver.findElement(roomNosDropdown)).selectByValue(numberOfRooms);
    }

    public void enterCheckIn(String checkInDate) {
        WebElement element = driver.findElement(datepickInInput);
        element.clear();
        element.sendKeys(checkInDate);
    }

    public void enterCheckOut(String checkOutDate) {
        WebElement element = driver.findElement(datepickOutInput);
        element.clear();
        element.sendKeys(checkOutDate);
    }

    public void selectAdults(String adults) {
        new Select(driver.findElement(adultRoomDropdown)).selectByValue(adults);
    }

    public void selectChildren(String children) {
        new Select(driver.findElement(childRoomDropdown)).selectByValue(children);
    }

    public void clickSearch() {
        driver.findElement(searchBtn).click();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public String getWelcomeUsername() {
        return driver.findElement(welcomeUsername).getAttribute("value");
    }

    public boolean isSearchPageDisplayed() {
        return driver.findElement(locationDropdown).isDisplayed();
    }
}
