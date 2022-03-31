package forms.tests;

import aquality.selenium.elements.interfaces.ITextBox;
import org.openqa.selenium.By;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tests.BaseTest;

import static aquality.selenium.browser.AqualityServices.getElementFactory;

public class ShoppingCartTest extends BaseTest {

    @BeforeMethod
    @AfterMethod

    @Test
    public void testShoppingCart() throws InterruptedException {
        getBrowser().goTo("https://catalog.onliner.by");
        ITextBox txtSearchBox = getElementFactory().getTextBox(By.className("fast-search__input"), "Search Box");

        txtSearchBox.sendKeys("Samsung");
        txtSearchBox.submit();
        Thread.sleep(8000);




        getBrowser().getDriver().quit();
    }
}
