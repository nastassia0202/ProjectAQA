package pagesRelax;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CatalogPage extends Form {

    public CatalogPage() {
        super(By.xpath("//*[contains(@class, 'CatalogNav__heading')]"), "Header");
    }

    public void openFilter() {
        IButton btnFilterShow = getElementFactory().getButton(By.xpath("//*[@class='MenuItem__text' and text()='Фильтры']"), "Filters");
        btnFilterShow.clickAndWait();
    }

    public void filters(String section, String value) {
        switch (section) {
            case ("Район"):
                String XPATH_txtFilter1 = "//*[@class='FilterSidebar__itemTitle' and text()='" + section + "']/parent::*//*[@class='Icon Icon--menu']";
                ITextBox txtFilter1 = getElementFactory().getTextBox(By.xpath(XPATH_txtFilter1), "");
                txtFilter1.clickAndWait();
                getElementFactory().getTextBox(By.xpath("//*[text()='" + value + "']"), "Filters").clickAndWait();
                break;

            case ("Кухня"):
                String XPATH_txtFilter2 = "//*[@class='FilterSidebar__itemTitle' and text()='" + section + "']/parent::*//*[@class='CompositeButtons__toggle']";
                ITextBox txtFilter2 = getElementFactory().getTextBox(By.xpath(XPATH_txtFilter2), "");
                txtFilter2.clickAndWait();
                getElementFactory().getTextBox(By.xpath("//*[text()='" + value + "']"), "Filters").clickAndWait();
                break;

            case ("Еда навынос"):
                String XPATH_txtFilter3 = "//*[@class='ToggleSwitch__text' and text()='" + section + "']/parent::*//*[@class='ToggleSwitch__slider']";
                ITextBox txtFilter3 = getElementFactory().getTextBox(By.xpath(XPATH_txtFilter3), "");
                switch (value) {
                    case ("Да"):
                        txtFilter3.clickAndWait();
                        break;
                    case ("Нет"):
                        break;
                }
                break;

            case ("Меню навынос"):
                String XPATH_txtFilter4 = "//*[@class='FilterSidebar__itemTitle' and text()='"+section+"']/parent::*//*[@class='CheckButton__span' and text()='"+value+"']";
                ITextBox txtFilter4 = getElementFactory().getTextBox(By.xpath(XPATH_txtFilter4), "");
                txtFilter4.clickAndWait();
                break;
        }
    }

    public void applyFilterAndShowResult() {
        IButton btnFilterShow = getElementFactory().getButton(By.xpath("//*[@class= 'Button__text' and text() = 'Показать']"), "Filters");
        btnFilterShow.clickAndWait();
    }

    public String getProductLinkFromSearchListAfisha(String serialNumberInTheList) {
        String XPATH_PRODUCT = "(//*[@class='b-afisha_blocks-strap_item_lnk_img'])";
        String linkPageOfTheFirstProduct = getElementFactory().getTextBox(By.xpath(XPATH_PRODUCT.concat("[" + serialNumberInTheList + "]").concat("/child::*[1]")), "Open productPage").getAttribute("href");
        return linkPageOfTheFirstProduct;
    }

    public String getProductLinkFromSearchList(String serialNumberInTheList) {
        String XPATH_PRODUCT = "(//*[contains(@class, 'Place__headerLink')])";
        String linkPageOfTheFirstProduct = getElementFactory().getTextBox(By.xpath(XPATH_PRODUCT.concat("[" + serialNumberInTheList + "]")), "Open productPage").getAttribute("href");
        return linkPageOfTheFirstProduct;
    }
}
//(//*[@class='b-afisha_blocks-strap_item_lnk_img'])[2]