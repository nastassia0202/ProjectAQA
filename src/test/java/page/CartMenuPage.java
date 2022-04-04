package page;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CartMenuPage extends Form {

    String XPATH_btnToRemoveProduct = "//*[contains(@class, 'cart-form__button_remove')]";
    private final ITextBox txtCartMessage = getElementFactory().getTextBox(By.xpath("//*[contains(@class, 'cart-message__title_big')]"), "Cart Message");

    public CartMenuPage() {
        super(By.xpath("//*[contains(@class, 'cart-form__title_condensed-additional')]"), "Cart-form Title");
    }

    public void deleteProductFromCart(String productTitle) {
        String XPATH_PRODUCT_LABEl = "//*[contains(@class, 'cart-form__offers-item_secondary')][.//*[contains(text(), '" + productTitle + "')]]";
        getElementFactory().getLabel(By.xpath(XPATH_PRODUCT_LABEl), "").getMouseActions().moveMouseToElement();

        IButton btnToRemove = getElementFactory().getButton(By.xpath(XPATH_PRODUCT_LABEl.concat(XPATH_btnToRemoveProduct)), "Remove Product From Cart");
        btnToRemove.clickAndWait();

        IButton goToCart = getElementFactory().getButton(By.xpath("//a[text()='Закрыть']"), "Go to empty cart");
        goToCart.clickAndWait();
    }

    public void checkTheCartIsEmpty() {
        Assert.assertEquals(txtCartMessage.getText(), "Ваша корзина пуста");
    }

}
