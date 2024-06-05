package tests;

import org.junit.Assert;
import org.junit.Test;
import pageobjects.CoursePage;

import java.util.List;

public class CoursePageTest extends BaseTest {
    @Test
    public void testCourseCategories() {
        CoursePage coursePage = new CoursePage(driver);
        coursePage.goToCoursePage();
        List<String> categories = coursePage.getCourseCategories();
        Assert.assertTrue(categories.contains("Программирование"));
        Assert.assertTrue(categories.contains("Дизайн"));
        Assert.assertTrue(categories.contains("Маркетинг"));
    }

    @Test
    public void testSearchCourses() {
        CoursePage coursePage = new CoursePage(driver);
        coursePage.goToCoursePage();
        coursePage.searchCourses("Java");
        List<String> searchResults = coursePage.getSearchResults();
        Assert.assertTrue(searchResults.stream().anyMatch(result -> result.contains("Java")));
    }

    @Test
    public void testFilterCourses() {
        CoursePage coursePage = new CoursePage(driver);
        coursePage.goToCoursePage();
        coursePage.filterCourses("Программирование");
        List<String> filteredCourses = coursePage.getFilteredCourses();
        Assert.assertTrue(filteredCourses.stream().allMatch(course -> course.contains("Программирование")));
    }

    @Test
    public void testAddCourseToCart() {
        CoursePage coursePage = new CoursePage(driver);
        coursePage.goToCoursePage();
        coursePage.addCourseToCart("Java-разработчик");
        Assert.assertEquals(1, coursePage.getCartItemCount());
    }

    @Test
    public void testCourseDetails() {
        CoursePage coursePage = new CoursePage(driver);
        coursePage.goToCoursePage();
        String expectedTitle = "Java-разработчик";
        String actualTitle = coursePage.getCourseTitle();
        Assert.assertEquals(expectedTitle, actualTitle);

        String expectedDuration = "6 месяцев";
        String actualDuration = coursePage.getCourseDuration();
        Assert.assertEquals(expectedDuration, actualDuration);

        String expectedDescription = "Научитесь создавать приложения на Java с нуля и получите профессию Java-разработчика.";
        String actualDescription = coursePage.getCourseDescription();
        Assert.assertTrue(actualDescription.contains(expectedDescription));
    }

    @Test
    public void testAddCourseToFavorites() {
        CoursePage coursePage = new CoursePage(driver);
        coursePage.goToCoursePage();
        coursePage.addCourseToFavorites("Java-разработчик");

        CoursePage favoritesPage = new CoursePage(driver);
        favoritesPage.openFavoritesPage();

        List<String> favoriteCourses = favoritesPage.getFavoriteCourses();
        Assert.assertTrue(favoriteCourses.contains("Java-разработчик"));
    }
}
