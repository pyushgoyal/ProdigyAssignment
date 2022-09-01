package com.prodigylabs.assignment.testScripts;



import com.prodigylabs.assignment.locators.ContactUsPage;
import com.prodigylabs.assignment.locators.HomePage;
import com.prodigylabs.assignment.utils.TestBase;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;


@Listeners(com.prodigylabs.assignment.utils.Listener.class)
public class prodigylabsAutomationTask extends TestBase {
    SoftAssert softAssert;

    @BeforeMethod
    public void launch ()  {
        softAssert = new SoftAssert();
        HomePage.get().clickacceptCookiesButton();

    }

    @Test
    public void prodigyLabAssignmentTask() {
        HomePage.get().navbar_ClickContactLink();
        //validate email address
        assertEquals(softAssert,ContactUsPage.get().getContactInfoEmail(),"info@prodigylabs.net","Email is not as expected.");
        //validate address
        assertEquals(softAssert,ContactUsPage.get().getContactInfoAddress(),"80 Richmond St. West, Suite 1401 Toronto, ON","Address is not as expected.");
        ContactUsPage.get().enterFirstName("Pyush");
        ContactUsPage.get().enterLastName("Goyal");
        ContactUsPage.get().enterEmail("InavalidEmail");
        //validate email field error message
        assertEquals(softAssert,ContactUsPage.get().getEmailErrorMessage(),"Email must be formatted correctly.","Email Error message is not as expected.");
        ContactUsPage.get().enterEmail("validemail@abc.com");
        ContactUsPage.get().selectIndustry("Banking");
        ContactUsPage.get().enterCity("Toronto");
        ContactUsPage.get().enterMessage("Random Message");
        ContactUsPage.get().clickSubmit();
        softAssert.assertAll();
    }

}
