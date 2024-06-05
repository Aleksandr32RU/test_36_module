package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pageobjects.ContactPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class ContactPageTest extends BaseTest {
    public static ContactPage contactPage;

    @Test
    public void testContactPageHeader() {
        ContactPage contactPage = new ContactPage(driver);
        contactPage.goToContactPage();
        String headerText = contactPage.getMainHeader();
        assertEquals("Контактная информация", headerText);
    }

    @Test
    public void testClaimCount() {
        ContactPage contactPage = new ContactPage(driver);
        contactPage.goToContactPage();
        int expectedCount = 4;
        int actualCount = contactPage.getUniqueHrefValues().size();
        assertEquals(expectedCount, actualCount);
    }

    @Test
    public void testContactPhoneNumberPresence() {
        ContactPage contactPage = new ContactPage(driver);
        contactPage.goToContactPage();
        WebElement phoneNumberElement = driver.findElement(By.xpath("//div[contains(text(), 'Контактные телефоны')]"));
        assertTrue(phoneNumberElement.isDisplayed());
    }

    @Test
    public void testLegalAddressPresence() {
        ContactPage contactPage = new ContactPage(driver);
        contactPage.goToContactPage();
        WebElement legalAddressElement = driver.findElement(By.xpath("//div[contains(text(), 'Юридический адрес')]"));
        assertTrue(legalAddressElement.isDisplayed());
    }


    @Test
    public void testContactFormSubmission() {
        String name = "John Doe";
        String email = "john.doe@example.com";
        String phone = "9003687736";
        String message = "This is a test message.";
        ContactPage contactPage = new ContactPage(driver);
        contactPage.goToContactPage();
        WebElement contactFormLink = driver.findElement(By.xpath("//a[@href='#popup:1']"));
        contactFormLink.click();
        contactPage.fillContactForm(name, email, phone, message);
        contactPage.submitContactForm();

        boolean isSuccessAlertDisplayed = contactPage.isSuccessAlertDisplayed();
        assertTrue(isSuccessAlertDisplayed);
    }

    @Test
    public void testContactFormValidationEmail() {
        String invalidEmail = "invalid-email";
        contactPage.fillContactForm("John Doe", invalidEmail, "9003687736","This is a test message.");
        contactPage.submitContactForm();

        boolean isSuccessAlertDisplayed = contactPage.isSuccessAlertDisplayed();
        assertFalse(isSuccessAlertDisplayed);
    }

    @Test
    public void testContactFormValidationName() {
        contactPage.fillContactForm("", "john.doe@example.com", "9003687736","This is a test message.");
        contactPage.submitContactForm();

        boolean isSuccessAlertDisplayed = contactPage.isSuccessAlertDisplayed();
        assertFalse(isSuccessAlertDisplayed);
    }

    @Test
    public void testContactFormValidationMessage() {
        contactPage.fillContactForm("John Joe", "john.doe@example.com", "9003687736","");
        contactPage.submitContactForm();

        boolean isSuccessAlertDisplayed = contactPage.isSuccessAlertDisplayed();
        assertFalse(isSuccessAlertDisplayed);
    }

    @Test
    public void testContactFormValidationPhone() {
        contactPage.fillContactForm("John Joe", "john.doe@example.com", "","123123123");
        contactPage.submitContactForm();

        boolean isSuccessAlertDisplayed = contactPage.isSuccessAlertDisplayed();
        assertFalse(isSuccessAlertDisplayed);
    }
}

