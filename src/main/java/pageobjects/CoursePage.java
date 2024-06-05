package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import java.util.List;
import java.util.stream.Collectors;

public class CoursePage extends BasePage {

    public CoursePage(WebDriver driver) {
        super(driver);
    }

    public void goToCoursePage() {
        driver.get("https://skillfactory.ru/courses");
    }
    @FindBy(css = ".course-categories")
    public WebElement courseCategoriesElement;
    @FindBy(css = ".search-input")
    public WebElement searchInput;
    @FindBy(css = ".filter-dropdown")
    public WebElement filterDropdown;
    @FindBy(css = ".add-to-cart-btn")
    public List<WebElement> addToCartButtons;
    @FindBy(css = ".cart-item-count")
    public WebElement cartItemCount;
    public List<String> getCourseCategories() {
        return List.of(courseCategoriesElement.getText().split("\n"));
    }
    public void searchCourses(String keyword) {
        searchInput.sendKeys(keyword);
        searchInput.submit();
    }
    public void filterCourses(String category) {
        filterDropdown.click();
        driver.findElement(By.xpath("//option[text()='" + category + "']")).click();
    }
    public void addCourseToCart(String courseName) {
        WebElement courseCard = driver.findElement(By.xpath("//h3[text()='" + courseName + "']/../.."));
        courseCard.findElement(By.cssSelector(".add-to-cart-btn")).click();
    }
    public int getCartItemCount() {
        return Integer.parseInt(cartItemCount.getText());
    }
    public List<String> getSearchResults() {
        return driver.findElements(By.cssSelector(".course-card h3"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
    public List<String> getFilteredCourses() {
        return driver.findElements(By.cssSelector(".course-card h3"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
    @FindBy(css = ".course-title")
    private WebElement courseTitleElement;
    @FindBy(css = ".course-duration")
    private WebElement courseDurationElement;
    @FindBy(css = ".course-description")
    private WebElement courseDescriptionElement;
    public String getCourseTitle() {
        return courseTitleElement.getText();
    }
    public String getCourseDuration() {
        return courseDurationElement.getText();
    }
    public String getCourseDescription() {
        return courseDescriptionElement.getText();
    }
    @FindBy(css = ".add-to-favorites-btn")
    private List<WebElement> addToFavoritesButtons;
    public void addCourseToFavorites(String courseName) {
        WebElement courseCard = driver.findElement(By.xpath("//h3[text()='" + courseName + "']/../.."));
        courseCard.findElement(By.cssSelector(".add-to-favorites-btn")).click();
    }
    @FindBy(css = ".favorites-link")
    private WebElement favoritesLink;
    @FindBy(css = ".favorite-course-card h3")
    private List<WebElement> favoriteCourseNames;
    public void openFavoritesPage() {
        favoritesLink.click();
    }
    public List<String> getFavoriteCourses() {
        return favoriteCourseNames.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
