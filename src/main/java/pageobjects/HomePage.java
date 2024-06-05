package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;
public class HomePage extends BasePage {
    public void open() {
        driver.get("https://skillfactory.ru/");
    }
    public HomePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@class='main__logo-wrapper']/img")
    private WebElement logo;

    public boolean isLogoDisplayed() {
        return logo.isDisplayed();
    }
    public String getLogoText() {
        return logo.getAttribute("alt");
    }
    @FindBy(xpath = "//a[@class='directions__list-link directions__list-link_green']/span")
    public WebElement startLearningButton;

    public String getStartLearningButtonText() {
        return startLearningButton.getText();
    }

    public void clickStartLearningButtonWithActions() {
        Actions actions = new Actions(driver);
        actions.click(startLearningButton).perform();
    }

    @FindBy(xpath = "//h2[@class='h2-title' and contains(text(), 'Помогаем получить высшее образование')]")
    public WebElement higherEducationSection;

    public String getHigherEducationSectionText() {
        Actions actions = new Actions(driver);
        actions.moveToElement(higherEducationSection).perform();
        return higherEducationSection.getText();
    }
    @FindBy(xpath = "//a[@class='directions__list-link']")
    public List<WebElement> coursCards;
    public int getDirectionOfCourseCards() {
        return coursCards.size();
    }
}