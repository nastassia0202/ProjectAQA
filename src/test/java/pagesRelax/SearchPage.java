package pagesRelax;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.Element;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static aquality.selenium.browser.AqualityServices.getBrowser;
import static aquality.selenium.browser.AqualityServices.getElementFactory;


public class SearchPage extends Form {

    public SearchPage() {
        super(By.xpath("//*[contains(@class, 'catalog-masthead__title')]"), "Product Title");
    }

    private final ITextBox txtAddress = getElementFactory().getTextBox(By.xpath("//*[contains(@class, 'PersonalContacts__address')]//*[@class= 'Button__text']"),"");
    private final ITextBox txtPhone = getElementFactory().getTextBox(By.xpath("//*[@class='Button__phone']"),"");
    private final ITextBox txtWorkingHours = getElementFactory().getTextBox(By.xpath("//*[contains(@class, 'PersonalContacts__worktime')]//*[@class= 'Button__text']"),"");

    @Step("Получение адреса со страницы заведения")
    public String addressOnThePage(){
        String address = txtAddress.getText();
        return address;
    }

    @Step("Получение номера со страницы заведения")
    public String phoneNumberOnThePage(){
        String phoneNumber = txtPhone.getText();
        return phoneNumber;
    }

    @Step("Получение времени работы со страницы заведения")
    public String workingHoursOnThePage(){
        String workingHours = txtWorkingHours.getText();
        return workingHours;
    }

    @Step("Получение времени работы со страницы заведения")
    public String workingHoursOnThPage(){
        String workingHours = txtWorkingHours.getText();
        return workingHours;
    }

    public String titleDeliveryBox() throws InterruptedException {
        ITextBox deliveryBox = getElementFactory().getTextBox(By.xpath("//*[@class=\"PersonalDelivery__block\"]"), "");
        deliveryBox.click();
        String title = getElementFactory().getTextBox(By.xpath("//*[contains(@class, 'Popup__content')]//*[1]"), "").getText();
        getElementFactory().getButton(By.xpath("//*[contains(@class, 'Icon--close Icon--massive')]"),"").click();
        return title;
    }

    //*[contains(@class, 'ContentBox__footer --divided')]
    public void showMoreParamsInContentBox() throws InterruptedException {
        ITextBox txt = getElementFactory().getTextBox(By.xpath("//*[contains(@class, 'ContentBox Features')]//*[text()='Показать ещё']"), "");

        txt.clickAndWait();
    }

    public String checkFilterValue(String section){
        String XPATH_Section = "//*[@class='Features__itemTitle' and text()='"+section+"']";
        ITextBox txtSection = getElementFactory().getTextBox(By.xpath(XPATH_Section.concat("/parent::*/parent::*/parent::*//*[@class='Features__itemValue']")), "");
        String jhkl = txtSection.getText();


        return jhkl;
    }

    public static boolean foo (String line, String reg) {
        String[] strings = line.split(", "); // делим строку на отдельные слова
        for (String word : strings) {
            if (word.matches(reg)) {  // проверяем в цикле каждое отдельное слово
                return true;
            }
        }
        return false;
    }

    //*[@class='Features__itemTitle' and text()='Район']/parent::*/parent::*/parent::*//*[@class="Features__itemValue"]
}
