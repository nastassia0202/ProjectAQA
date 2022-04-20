package pagesRelax;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import io.qameta.allure.Step;
import org.openqa.selenium.By;


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

    public String titleDeliveryBox(){
        IButton btnDeliveryBox = getElementFactory().getButton(By.xpath("//*[@class=\"PersonalDelivery__block\"]"), "");

        btnDeliveryBox.click();
        String jhkl = getElementFactory().getTextBox(By.xpath("//*[contains(@class, 'Popup__content')]"), "").getAttribute("title");
        getElementFactory().getButton(By.xpath("//*[contains(@class, 'Icon--close Icon--massive')]"), "").clickAndWait();
        return jhkl;
    }

    public String checkFilterValue(String section){
        String XPATH_Section = "//*[@class='Features__itemTitle' and text()='"+section+"']";
        ITextBox txtSection = getElementFactory().getTextBox(By.xpath(XPATH_Section.concat("/parent::*/parent::*/parent::*//*[@class='Features__itemValue']")), "");
        String jhkl = txtSection.getText();
        return jhkl;
    }

    //*[@class='Features__itemTitle' and text()='Район']/parent::*/parent::*/parent::*//*[@class="Features__itemValue"]
}
