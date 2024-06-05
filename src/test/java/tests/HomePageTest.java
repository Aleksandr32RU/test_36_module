package tests;

import org.junit.Assert;

import org.junit.Test;
import pageobjects.HomePage;

public class HomePageTest extends BaseTest {

    @Test
    public void testLogoIsDisplayed() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        Assert.assertTrue(homePage.isLogoDisplayed());
    }
    @Test
    public void testLogoTextIsCorrect() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        String expectedLogoText = "SkillFactory - научитесь работать в IT";
        String actualLogoText = homePage.getLogoText();
        Assert.assertEquals(expectedLogoText, actualLogoText);
    }
    @Test
    public void testStartAllCoursesTextAndOpen(){
        HomePage homePage = new HomePage(driver);
        homePage.open();
        String expectedButtonText = "Все курсы";
        String actualButtonText = homePage.getStartLearningButtonText();
        Assert.assertEquals(expectedButtonText, actualButtonText);
        System.out.println("Текст кнопки 'Все курсы': " + actualButtonText);
        homePage.clickStartLearningButtonWithActions();
        Assert.assertTrue(driver.getCurrentUrl().contains("/courses"));
    }

    @Test
    public void testHigherEducationSectionText() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        String expectedText = "Помогаем получить высшее образование";
        String actualText = homePage.getHigherEducationSectionText();
        Assert.assertEquals(expectedText, actualText);
    }

    @Test
    public void testCourseCardsAreDisplayed() {
        HomePage homePage = new HomePage(driver);
        homePage.open();
        Assert.assertEquals(homePage.getDirectionOfCourseCards(), 7);
    }
}
