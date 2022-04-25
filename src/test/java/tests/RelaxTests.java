package tests;


import aquality.selenium.elements.interfaces.ITextBox;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import pagesRelax.CatalogPage;
import pagesRelax.HomePage;
import pagesRelax.SearchPage;
import utils.ScreenshotExtension;

import static aquality.selenium.browser.AqualityServices.getBrowser;
import static aquality.selenium.browser.AqualityServices.getElementFactory;

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

        String productLink = catalogPage.getProductLinkFromSearchList("1");
        Thread.sleep(1000);

        getBrowser().goTo(productLink);
        Assert.assertEquals(searchPage.titleDeliveryBox(), "Заказать еду");
        searchPage.showMoreParamsInContentBox();
        Assert.assertTrue(searchPage.foo(searchPage.checkFilterValue("Район"), "Заводской"));
        Assert.assertTrue(searchPage.foo(searchPage.checkFilterValue("Кухня"), "Белорусская"));

        Thread.sleep(3000);
        getBrowser().getDriver().quit();
    }


    @Test
    public void posterValidationTest() throws InterruptedException {
        getBrowser().getDriver().manage().window().maximize();
        homePage.openHomePage();
        homePage.selectMenuCategory("Афиша, кино");

        String productLink = catalogPage.getProductLinkFromSearchListAfisha("2");
        Thread.sleep(1000);

        getBrowser().goTo(productLink);

        ITextBox feedbacksIcon = getElementFactory().getTextBox(By.xpath("//*[contains(@class, \"js-feedbacks b-feedbacks\")]"), "");
        Assert.assertTrue(feedbacksIcon.getElement().isDisplayed());
        getBrowser().getDriver().quit();
    }
}
