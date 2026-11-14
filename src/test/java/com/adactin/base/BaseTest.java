package com.adactin.base;

import com.adactin.utils.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;

public abstract class BaseTest {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    @Parameters({"browser"})
    public void setUp(@Optional("") String xmlBrowser) {
        // Priority order: TestNG XML parameter -> System Property (-Dbrowser) -> config.properties
        String targetBrowser;
        if (xmlBrowser != null && !xmlBrowser.trim().isEmpty()) {
            targetBrowser = xmlBrowser.trim();
        } else {
            targetBrowser = ConfigReader.getProperty("browser");
        }

        String browser = targetBrowser.split(",")[0].trim().toLowerCase();
        String baseUrl = ConfigReader.getProperty("baseUrl");
        String implicitWaitStr = ConfigReader.getProperty("implicitWait");
        long implicitWait = implicitWaitStr.isEmpty() ? 5 : Long.parseLong(implicitWaitStr);

        WebDriver driver;

        if ("chrome".equalsIgnoreCase(browser)) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
        } else if ("firefox".equalsIgnoreCase(browser)) {
            FirefoxOptions options = new FirefoxOptions();
            driver = new FirefoxDriver(options);
        } else if ("edge".equalsIgnoreCase(browser)) {
            EdgeOptions options = new EdgeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new EdgeDriver(options);
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser + ". Please use 'chrome', 'firefox', or 'edge'.");
        }

        driverThreadLocal.set(driver);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.get(baseUrl);
    }

    public WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        WebDriver driver = getDriver();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}
