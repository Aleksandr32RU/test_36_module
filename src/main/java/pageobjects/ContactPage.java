package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactPage extends BasePage {

    public void goToContactPage() {
        driver.get("https://skillfactory.ru/contacts");
    }

    @FindBy(xpath = "//h1[contains(@class, 'tn-atom')]")
    public WebElement mainHeader;

    public Set<String> getUniqueHrefValues() {
        Set<String> hrefValues = new HashSet<>();
        for (int i = 1; i <= 4; i++) {
            String xpath = String.format("//a[@href='#popup:%d']", i);
            List<WebElement> elements = driver.findElements(By.xpath(xpath));
            hrefValues.addAll(getHrefValues(elements));
        }
        return hrefValues;
    }
    private Set<String> getHrefValues(List<WebElement> elements) {
        Set<String> hrefValues = new HashSet<>();
        for (WebElement element : elements) {
            hrefValues.add(element.getAttribute("href"));
        }
        return hrefValues;
    }

    @FindBy(xpath = "//input[@name='name' and @class='t-input js-tilda-rule' and @placeholder='Ваше имя']")
    public WebElement nameInput;
    @FindBy(xpath = "//input[@name='email' and @class='t-input js-tilda-rule' and @placeholder='Электронная почта']")
    public WebElement emailInput;
    @FindBy(xpath = "//input[@name='tildaspec-phone-part[]' and @data-gtm-form-interact-field-id='3']")
    public WebElement phoneInput;
    @FindBy(xpath = "//textarea[@name='comment' and @class='t-input js-tilda-rule' and @placeholder='Ваш вопрос']")
    public WebElement messageTextArea;
    @FindBy(xpath = "//button[contains(text(), 'Отправить')]")
    public WebElement sendButton;
    @FindBy(xpath = "//div[@data-success-message='Спасибо! Скоро мы с Вами свяжемся!']")
    public WebElement successAlert;
    public ContactPage(WebDriver driver) {
        super(driver);
    }
    public String   getMainHeader() {
        return mainHeader.getText();
    }
    public void fillContactForm(String name, String email, String phone, String message) {
        nameInput.sendKeys(name);
        emailInput.sendKeys(email);
        phoneInput.sendKeys(phone);
        messageTextArea.sendKeys(message);
    }
    public void submitContactForm() {
        sendButton.click();
    }
    public boolean isSuccessAlertDisplayed() {
        return successAlert.isDisplayed();
    }

}

