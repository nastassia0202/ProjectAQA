package page;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class HomePage extends Form {

    public final ITextBox txtSearchBox = getElementFactory().getTextBox(By.className("fast-search__input"), "Search");

    private ILabel iframe = getElementFactory().getLabel(By.cssSelector("#fast-search-modal iframe"), "SearchDialog");
    private final String XPATH_PRODUCT = "(//a[contains(@class, 'product__title-link')])";

    public HomePage() {
        super(By.xpath("//*[contains(@class, 'search__input')]"), "Search");
    }

    public void openHomePage() {
        getBrowser().goTo("https://www.onliner.by");
    }

    public void findProduct(String productName, String serialNumberInTheList) {
        txtSearchBox.sendKeys(productName);
        txtSearchBox.submit();

        getBrowser().getDriver().switchTo().frame(iframe.getElement());
        getElementFactory().getTextBox(By.xpath(XPATH_PRODUCT.concat("[" + serialNumberInTheList + "]")), "Open productPage").clickAndWait();
    }
}
