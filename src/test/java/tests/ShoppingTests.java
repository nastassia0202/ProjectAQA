package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.CartMenuPage;
import page.HomePage;
import page.ProductPage;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class ShoppingTests {

    private final String productTitle = "Ноутбук Apple Macbook Pro 14\" M1 Pro 2021 MKGR3";

    HomePage homePage = new HomePage();
    ProductPage productPage = new ProductPage();
    CartMenuPage cartMenuPage = new CartMenuPage();


    @Test
    public void shoppingTest() throws InterruptedException {
        getBrowser().getDriver().manage().window().maximize();
        homePage.openHomePage();
        homePage.findProduct(productTitle);
        homePage.getProductPageFromSearchList("1");

        Assert.assertEquals(productPage.getProductTitle(), productTitle);
        productPage.isDisplayedDescriptionLabel();
        String price = productPage.getTxtPrice();
        productPage.showMorePrices();

        Assert.assertEquals(productPage.addToCartProductWithMinPrice(), price);
        Thread.sleep(5000);

        productPage.openCartPage();
        cartMenuPage.deleteProductFromCart(productTitle);
        cartMenuPage.checkTheCartIsEmpty();

        getBrowser().getDriver().quit();
    }

}
