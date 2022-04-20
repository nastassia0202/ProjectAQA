package pagesRelax;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class HomePage extends Form {

    public final ITextBox txtSearchBox = getElementFactory().getTextBox(By.id("search_open"), "Search");

    public HomePage() {
        super(By.xpath("//*[contains(@class, 'Logo--header')]"), "Logo");
    }

    @Step("Открытие главной страницы")
    public void openHomePage() {
        getBrowser().goTo("https://www.relax.by/");
    }

    @Step("Ввод {title} в поле ввода")
    public void inputInSearchBox(String title) {
        txtSearchBox.sendKeys(title);
        txtSearchBox.submit();
    }

    @Step("Получение адреса заведения")
    public String getAddressInstitution(String title, String typeOfInstitution) {
        final String XPATH_SearchResult = "//*[@class='SearchResults__item--title h6 small' and text()='" + title + "']/parent::*";
        ILabel lblType = getElementFactory().getLabel(By.xpath(XPATH_SearchResult.concat("/child::*[2]")), "");

        if (lblType.getText().equals(typeOfInstitution) == true) {
            ITextBox txtAddressInstitution = getElementFactory().getTextBox(By.xpath(XPATH_SearchResult.concat("//*[@class='SearchResults__address']")), "");
            return txtAddressInstitution.getText();
        } else
            return "Address not found";
    }

    @Step("Получение времени работы заведения")
    public String getWorkingHours(String title, String typeOfInstitution) {
        final String XPATH_SearchResult = "//*[@class='SearchResults__item--title h6 small' and text()='" + title + "']/parent::*";
        ILabel lblType = getElementFactory().getLabel(By.xpath(XPATH_SearchResult.concat("/child::*[2]")), "");

        if (lblType.getText().equals(typeOfInstitution) == true) {
            ITextBox txtWorkingHours = getElementFactory().getTextBox(By.xpath(XPATH_SearchResult.concat("//*[@class='SearchResults__item--time']")), "");
            return txtWorkingHours.getText();
        } else
            return "WorkingHours not found";
    }

    @Step("Открытие страницы заведения")
    public void openPageWithExpectedResult(String title, String typeOfInstitution) {
        final String XPATH_SearchResult = "//*[@class='SearchResults__item--title h6 small' and text()='" + title + "']/parent::*";
        ILabel lblType = getElementFactory().getLabel(By.xpath(XPATH_SearchResult.concat("/child::*[2]")), "");

        if (lblType.getText().equals(typeOfInstitution) == true) {
            getElementFactory().getLabel(By.xpath(XPATH_SearchResult), "").clickAndWait();
        } else
            System.out.println("Error");
    }

    @Step("Выбор категории на главной странице")
    public void selectMenuCategory(String category, String typeOfInstitution) {
        if (category == "Еда") {
            ITextBox txtCategoryBox = getElementFactory().getTextBox(By.xpath("//*[@class=\"CategoriesMainMenu__item isActive\" and @title='" + category + "']"), "Category");
            txtCategoryBox.clickAndWait();
        } else {
            ITextBox txtCategoryBox = getElementFactory().getTextBox(By.xpath("//*[@class=\"CategoriesMainMenu__item\" and @title='" + category + "']"), "Category");
            txtCategoryBox.clickAndWait();
        }
            ITextBox txtCategorySubmenu = getElementFactory().getTextBox(By.xpath("//*[text()='" + typeOfInstitution + "']"), "Category");
            txtCategorySubmenu.clickAndWait();
    }

    @Step("Выбор категории на главной странице")
    public void selectMenuCategory(String category) {
        ITextBox txtCategoryBox = getElementFactory().getTextBox(By.xpath("//*[@class=\"CategoriesMainMenu__item\" and @title='" + category + "']"), "Category");
        txtCategoryBox.clickAndWait();
    }
}