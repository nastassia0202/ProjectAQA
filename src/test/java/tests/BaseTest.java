package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;

public class BaseTest {

//    public WebDriver driver;
//    protected Browser browser;
//    protected Waits waits;
//
//    protected final IElementFactory elementFactory;
//
//    protected BaseTest() {
//        elementFactory = AqualityServices.getElementFactory();
//    }
//
//    @BeforeMethod
//    public void setUp() {
//
//    }
//
//    @AfterMethod
//    public void closePage() {
//        if (AqualityServices.isBrowserStarted()) {
//
//        }
//    }

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

    public Browser getBrowser() {
        return AqualityServices.getBrowser();
    }
}
