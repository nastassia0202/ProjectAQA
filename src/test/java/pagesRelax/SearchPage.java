package pagesRelax;

import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class SearchPage extends Form {

    public SearchPage() {
        super(By.xpath("//*[contains(@class, 'catalog-masthead__title')]"), "Product Title");
    }

    private final ITextBox txtAddress = getElementFactory().getTextBox(By.xpath("//*[contains(@class, 'PersonalContacts__address')]//*[@class= 'Button__text']"),"");
    private final ITextBox txtPhone = getElementFactory().getTextBox(By.xpath("//*[@class='Button__phone']"),"");
    private final ITextBox txtWorkingHours = getElementFactory().getTextBox(By.xpath("//*[contains(@class, 'PersonalContacts__worktime')]//*[@class= 'Button__text']"),"");

    public String addressOnThePage(){
        String address = txtAddress.getText();
        return address;
    }

    public String phoneNumberOnThePage(){
        String phoneNumber = txtPhone.getText();
        return phoneNumber;
    }

    public String workingHoursOnThePage(){
        String workingHours = txtWorkingHours.getText();
        return workingHours;
    }
}
