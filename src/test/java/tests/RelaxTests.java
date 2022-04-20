package tests;


import org.junit.jupiter.api.extension.ExtendWith;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import pagesRelax.CatalogPage;
import pagesRelax.HomePage;
import pagesRelax.SearchPage;
import utils.ScreenshotExtension;

import static aquality.selenium.browser.AqualityServices.getBrowser;

@ExtendWith(ScreenshotExtension.class)
public class RelaxTests {

    HomePage homePage = new HomePage();
    SearchPage searchPage = new SearchPage();
    CatalogPage catalogPage = new CatalogPage();

    @Test
    public void searchTest() {

        String title = "Luna";
        String typeOfInstitution = "Рестобар";

        getBrowser().getDriver().manage().window().maximize();
        homePage.openHomePage();
        homePage.inputInSearchBox(title);
        String actualAddress = homePage.getAddressInstitution(title, typeOfInstitution);
//        String actualWorkingHours = "до 00:00";
        String actualWorkingHours = homePage.getWorkingHours(title, typeOfInstitution);
        String actualPhone = "+375 29 333-00-74";

        homePage.openPageWithExpectedResult(title, typeOfInstitution);

        Assert.assertEquals(actualAddress, searchPage.addressOnThePage());
        Assert.assertEquals(actualPhone, searchPage.phoneNumberOnThePage());
        Assert.assertTrue(actualWorkingHours.equals(searchPage.workingHoursOnThePage()));

        getBrowser().getDriver().quit();

    }

    @Test
    public void filtersValidationTest() throws InterruptedException {
        String categoryFromMenu = "Еда";
        String typeOfInstitution = "Рестораны";

        getBrowser().getDriver().manage().window().maximize();
        homePage.openHomePage();
        homePage.selectMenuCategory(categoryFromMenu, typeOfInstitution);
        catalogPage.openFilter();
        catalogPage.filters("Район", "Заводской");
        catalogPage.filters("Кухня", "Белорусская");
        catalogPage.filters("Еда навынос", "Да");
        catalogPage.filters("Меню навынос", "Да");
        Thread.sleep(1000);
        catalogPage.applyFilterAndShowResult();

        catalogPage.getProductPageFromSearchList("1");
        Assert.assertEquals(searchPage.titleDeliveryBox(), "Заказать еду");
//        Assert.assertEquals(searchPage.checkFilterValue("Район"), "Заводской");

//        ITextBox hgj = getElementFactory().getTextBox(By.xpath("//*[@class=\"ContentBox__header --divided\"]/parent::*[@class='ContentBox__content']"), "");
//        Assert.assertTrue(hgj.getElement().isDisplayed());

        Thread.sleep(3000);
        getBrowser().getDriver().quit();
    }

    @Test
    public void posterValidationTest() {
        getBrowser().getDriver().manage().window().maximize();
        homePage.openHomePage();
        homePage.selectMenuCategory("Афиша, кино");


        getBrowser().getDriver().quit();
    }
}
