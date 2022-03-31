package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import aquality.selenium.elements.interfaces.IElementFactory;
import forms.OnlinerCatalogPage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected final IElementFactory elementFactory;

    protected BaseTest() {
        elementFactory = AqualityServices.getElementFactory();
    }

    @BeforeMethod
    protected void beforeMethod() {
        AqualityServices.getBrowser().getDriver().manage().window().maximize();
    }

    @AfterMethod
    public void afterTest() {
        if (AqualityServices.isBrowserStarted()) {
            getBrowser().quit();
        }
    }

    protected void navigate(OnlinerCatalogPage page) {
        getBrowser().goTo(page.getAddress());
    }

    protected Browser getBrowser() {
        return AqualityServices.getBrowser();
    }
}
