package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pagesRelax.HomePage;
import pagesRelax.SearchPage;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class RelaxTests {

    HomePage homePage = new HomePage();
    SearchPage searchPage = new SearchPage();

    String title = "Luna";
    String typeOfInstitution = "Рестобар";


    @Test
    public void searchTest() throws InterruptedException {
        getBrowser().getDriver().manage().window().maximize();
        homePage.openHomePage();
        homePage.inputInSearchBox(title);
        String actualAddress = homePage.getAddressInstitution(title, typeOfInstitution);
        String actualWorkingHours = homePage.getWorkingHours(title, typeOfInstitution);
        String actualPhone = "+375 29 333-00-74";

        homePage.openPageWithExpectedResult(title, typeOfInstitution);
        Thread.sleep(3000);

        try {
            Assert.assertEquals(actualAddress, searchPage.addressOnThePage());
            Assert.assertEquals(actualPhone, searchPage.phoneNumberOnThePage());
            Assert.assertTrue(actualWorkingHours.equals(searchPage.workingHoursOnThePage()));
        }finally {
            getBrowser().getDriver().quit();
        }
    }
}
