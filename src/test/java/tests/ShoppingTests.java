package tests;

import aquality.selenium.browser.AqualityServices;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import page.CartMenuPage;
import page.HomePage;
import page.ProductPage;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class ShoppingTests{

    private final String productTitle = "Смартфон Samsung Galaxy A52 SM-A525F/DS 4GB/128GB (черный)";

    HomePage homePage = new HomePage();
    ProductPage productPage = new ProductPage();
    CartMenuPage cartMenuPage = new CartMenuPage();

    @Test
    public void shoppingTest(){
        getBrowser().getDriver().manage().window().maximize();
        homePage.openHomePage();
        homePage.findProduct(productTitle, "1");

        Assert.assertEquals(productPage.getProductTitle(), productTitle);
        productPage.isDisplayedDescriptionLabel();
        String price = productPage.getTxtPrice();
        productPage.showMorePrices();
        productPage.showMorePrices();
        Assert.assertEquals(productPage.addToCartProductWithMinPrice(), price);

        cartMenuPage.openCartPage();
        cartMenuPage.deleteProductFromCart();
        cartMenuPage.checkTheCartIsEmpty();

        getBrowser().getDriver().quit();
    }

}
