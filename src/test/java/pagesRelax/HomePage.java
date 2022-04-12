package pagesRelax;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class HomePage extends Form {

    public HomePage() {
        super(By.xpath("//*[contains(@class, 'search__input')]"), "Search");
    }

    public final ITextBox txtSearchBox = getElementFactory().getTextBox(By.id("search_open"), "Search");

    public void openHomePage() {
        getBrowser().goTo("https://www.relax.by");
    }

    public void inputInSearchBox(String title) {
        txtSearchBox.sendKeys(title);
        txtSearchBox.submit();
    }

    public String getAddressInstitution(String title , String typeOfInstitution) {
        final String XPATH_SearchResult = "//*[@class='SearchResults__item--title h6 small' and text()='" + title + "']/parent::*";
        ILabel lblType = getElementFactory().getLabel(By.xpath(XPATH_SearchResult.concat("/child::*[2]")), "");

        if (lblType.getText().equals(typeOfInstitution) == true) {
            ITextBox txtAddressInstitution = getElementFactory().getTextBox(By.xpath(XPATH_SearchResult.concat("//*[@class='SearchResults__address']")), "");
            return txtAddressInstitution.getText();
        }else
            return null;
    }

    public String getWorkingHours(String title , String typeOfInstitution){
        final String XPATH_SearchResult = "//*[@class='SearchResults__item--title h6 small' and text()='" + title + "']/parent::*";
        ILabel lblType = getElementFactory().getLabel(By.xpath(XPATH_SearchResult.concat("/child::*[2]")), "");

        if (lblType.getText().equals(typeOfInstitution) == true) {
            ITextBox txtWorkingHours = getElementFactory().getTextBox(By.xpath(XPATH_SearchResult.concat("//*[@class='SearchResults__item--time']")), "");
            return txtWorkingHours.getText();
        }else
            return null;
    }

    public void openPageWithExpectedResult(String title, String typeOfInstitution) {
        final String XPATH_SearchResult = "//*[@class='SearchResults__item--title h6 small' and text()='" + title + "']/parent::*";

        getElementFactory().getLabel(By.xpath(XPATH_SearchResult), "").clickAndWait();
    }

}