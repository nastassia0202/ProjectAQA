package page;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductPage extends Form {

    private final String XPATH_ADD_TO_CART_BUTTON = "(//*[contains(@class, 'product-aside__offers-item')]//*[contains(@class, 'button-style_expletive')])";

    private final ITextBox txtProductTitle = getElementFactory().getTextBox(By.xpath("//*[contains(@class, 'catalog-masthead__title')]"), "Product Title");
    private final ITextBox txtPrice = getElementFactory().getTextBox(By.xpath("//*[contains(@class, 'js-description-price-link')]"), "Price");
    private final ILabel lblProductDescription = getElementFactory().getLabel(By.xpath("//*[contains(@class, 'js-product-specs')]"), "Description label");
    private final IButton btnGetPageWithAllPrices = getElementFactory().getButton(By.xpath("//*[contains(@class, 'js-product-prices-count-link')]"), "Product Prices");
    private final IButton btnShowMorePrices = getElementFactory().getButton(By.xpath("//*[contains(@class, 'offers-list__button offers-list__button_show')]"), "Show More");
    private final ITextBox txtProductAddingToCArt = getElementFactory().getTextBox(By.xpath("(//*[contains(@class, 'product-recommended__subheader')])[1]"), "Product adding to cart");

    public ProductPage() {
        super(By.xpath("//*[contains(@class, 'catalog-masthead__title')]"), "Product Title");
    }

    private boolean isCheckProductTitle() {
        if (txtProductTitle.getElement().isDisplayed()) {
            return true;
        } else
            return false;
    }

    public String getTxtPrice() {
        return txtPrice.getText();
    }

    public String getProductTitle() {
        if (isCheckProductTitle() == true) {
            return txtProductTitle.getText();
        } else return "The title not found";
    }

    public boolean isDisplayedDescriptionLabel() {
        if (lblProductDescription.getElement().isDisplayed()) {
            return true;
        } else
            return false;
    }

    public void showMorePrices() {
        btnGetPageWithAllPrices.click();
        if (btnShowMorePrices.getElement().isDisplayed()) {
            btnShowMorePrices.clickAndWait();
        }
    }

    public String addToCartProductWithMinPrice() {
        List<ITextBox> txtGetPrices = getElementFactory().findElements(By.xpath("//*[contains(@class, 'offers-list__description_nodecor')]"), ElementType.TEXTBOX);
        List<Double> prices = txtGetPrices
                .stream()
                .map(string -> Double.parseDouble(string.getText().replaceAll("р.", "").replace(" ", "").replace(",", ".").trim())).collect(Collectors.toList());
        Collections.sort(prices);
        String minPrice = String.valueOf(prices.get(0)).replace(".", ",").concat("0 р.");
        String XPATH_BUTTON_ADD_TO_CART = "(//*[@class = 'offers-list__flex'][.//div[contains(text() ,'" + minPrice + "')]]//*[contains(@class, 'button-style_expletive')])[2]";
        getElementFactory().getLabel(By.xpath(XPATH_BUTTON_ADD_TO_CART), "Add to cart product with the min price").clickAndWait();
        return minPrice;
    }

    private boolean checkLabelToAddingProductToCart() {
        ILabel productAddingToCartMessage = getElementFactory().getLabel(By.xpath("//*[contains(@class, 'roduct-recommended__sidebar-overflow')]"), "Status massage");
        if (productAddingToCartMessage.getElement().isDisplayed()) {
            Assert.assertEquals(txtProductAddingToCArt.getText(), "Товар добавлен в корзину");
//            getElementFactory().getButton(By.xpath("//*[contains(@class, 'product-recommended__sidebar-close')]"), "Exit").click();
            return true;
        }
        return false;
    }

    private final IButton btnGoingToCartFromHomePage = getElementFactory().getButton(By.xpath("//*[contains(@class, 'auth-bar__item--cart')]"), "Going to Cart");
    private final IButton btnGoingToCartFromProductPage = getElementFactory().getButton(By.xpath("//*[contains(@class, 'button-style_another button-style_base-alter')]"), "Going to Cart");


    public void openCartPage() {
        if (checkLabelToAddingProductToCart()==false)
        btnGoingToCartFromHomePage.clickAndWait();
        else
            btnGoingToCartFromProductPage.clickAndWait();
    }

//    public void addToCartProductWithFirstPrice() {
//        getElementFactory().getLabel(By.xpath(XPATH_ADD_TO_CART_BUTTON.concat("[1]")), "Add to cart product with the first price")
//                .clickAndWait();
//    }
}
