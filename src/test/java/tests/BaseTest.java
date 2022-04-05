package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.elements.interfaces.IElementFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import utils.Listener;
import utils.Waits;


@Listeners(Listener.class)
public class BaseTest {

    public WebDriver driver;
    protected Browser browser;
    protected Waits waits;

    protected final IElementFactory elementFactory;

    protected BaseTest() {
        elementFactory = AqualityServices.getElementFactory();
    }

    @BeforeClass
    public void setUp() {
        browser = new Browser(browser.getDriver());
        driver = browser.getDriver();
        waits = new Waits(driver);

        driver.get(browser.getDriver().getCurrentUrl());
    }

    @AfterClass
    public void closePage() {
        driver.quit();
    }

//    @BeforeMethod
//    protected void beforeMethod() {
//        AqualityServices.getBrowser().getDriver().manage().window().maximize();
//    }
//K
//    @AfterMethod
//    public void afterTest() {
//        if (AqualityServices.isBrowserStarted()) {
//            getBrowser().quit();
//        }
//    }

    protected Browser getBrowser() {
        return AqualityServices.getBrowser();
    }
}
