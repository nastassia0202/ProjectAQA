package page;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CartMenuPage extends Form {

    private final IButton btnGoingToCart = getElementFactory().getButton(By.xpath("//*[contains(@class, 'auth-bar__item--cart')]"), "Going to Cart");
    private final IButton btnRemoveProduct = getElementFactory().getButton(By.xpath("//*[contains(@class, 'cart-form__button_remove')]"), "Remove Product From Cart");
    private final ITextBox txtCartMessage = getElementFactory().getTextBox(By.xpath("//*[contains(@class, 'cart-message__title_big')]"), "Cart Message");

    public CartMenuPage() {
        super(By.xpath("//*[contains(@class, 'cart-form__title_condensed-additional')]"), "Cart-form Title");
    }

    public void openCartPage() {
        btnGoingToCart.clickAndWait();
    }

    public void deleteProductFromCart() {
        getElementFactory().getLabel(By.xpath("//*[contains(@class, 'cart-form__offers-item_secondary')]"), "").getMouseActions().moveMouseToElement();
        btnRemoveProduct.clickAndWait();
        IButton goToCart = getElementFactory().getButton(By.xpath("//a[text()='Закрыть']"), "Go to empty cart");
        goToCart.clickAndWait();
    }

    public void checkTheCartIsEmpty() {
        Assert.assertEquals(txtCartMessage.getText(), "Ваша корзина пуста");
    }

}
