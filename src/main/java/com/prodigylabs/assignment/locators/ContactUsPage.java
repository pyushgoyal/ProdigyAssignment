package com.prodigylabs.assignment.locators;

import com.prodigylabs.assignment.utils.Log;
import com.prodigylabs.assignment.utils.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


/***
 * This is the page factory class for https://prodigylabs.net/contact-us/
 * This class helps in creating the objects of different webelements present on this page.
 *
 */

public class ContactUsPage extends TestBase {

    private static ContactUsPage testObj;
    WebDriver driver = getDriver();

    public synchronized static ContactUsPage get() {
        if(testObj==null){
            testObj= new ContactUsPage();
            return testObj;
        }
        return testObj;
    }

    /***
     * Locators - By objects
     * These By objects are only for elements with Static locator paths
     */

    private By path_firstName = new By.ByCssSelector("input[name='firstname']");
    private By path_lastName = new By.ByName("lastname");
    private By path_email = new By.ByName("email");
    private By path_industry = new By.ByName("industry");
    private By path_city = new By.ByName("city");
    private By path_message = new By.ByName("message");
    private By path_submitButton = new By.ByXPath("//*[@value=\"Submit\"]");
    private By path_emailErrorMessage = new By.ByXPath("//*[@class=\"hs_email hs-email hs-fieldtype-text field hs-form-field\"]/ul/li/label");
    private By path_contactInfo_email = new By.ByXPath("(//*[@class=\"wpb_wrapper\"]/h4)[2]");
    private By path_contactInfo_address = new By.ByXPath("(//*[@class=\"wpb_wrapper\"]/h4)[3]");

    /***
     * Initiating WebElements using the By objects
     *
     */
    public  WebElement firstName (){
        waitForVisibilityOfWebElement(path_firstName,10);
        WebElement firstName = driver.findElement(path_firstName);
        return firstName;
    }

    public  WebElement lastName (){
        waitForVisibilityOfWebElement(path_lastName,10);
        WebElement lastName = driver.findElement(path_lastName);
        return lastName;
    }

    public  WebElement email (){
        waitForVisibilityOfWebElement(path_email,10);
        WebElement email = driver.findElement(path_email);
        return email;
    }

    public  WebElement industryDrpDwn (){
        waitForVisibilityOfWebElement(path_industry,10);
        WebElement industryDrpDwn = driver.findElement(path_industry);
        return industryDrpDwn;
    }

    public  WebElement city (){
        waitForVisibilityOfWebElement(path_city,10);
        WebElement city = driver.findElement(path_city);
        return city;
    }

    public  WebElement message (){
        waitForVisibilityOfWebElement(path_message,10);
        WebElement message = driver.findElement(path_message);
        return message;
    }

    public  WebElement submitButton (){
        waitForVisibilityOfWebElement(path_submitButton,10);
        WebElement submitButton = driver.findElement(path_submitButton);
        return submitButton;
    }


    public  WebElement emailErrorMessage (){
        waitForVisibilityOfWebElement(path_emailErrorMessage,10);
        WebElement emailErrorMessage = driver.findElement(path_emailErrorMessage);
        return emailErrorMessage;
    }

    public  WebElement contactInfoEmail (){
        waitForVisibilityOfWebElement(path_contactInfo_email,10);
        WebElement contactInfoEmail = driver.findElement(path_contactInfo_email);
        return contactInfoEmail;
    }

    public  WebElement contactInfoAddress (){
        waitForVisibilityOfWebElement(path_contactInfo_address,10);
        WebElement contactInfoAddress = driver.findElement(path_contactInfo_address);
        return contactInfoAddress;
    }


    // Basic operations on the objects
    /***
     *  This method verifies the visibility of firstName field and then enters the given value.
     * @param firstName
     */
    public void enterFirstName (String firstName){
        firstName().clear();
        firstName().sendKeys(firstName);
        Log.info("Successfully entered "+firstName+" in the First Name field.");

    }

    /***
     *  This method verifies the visibility of lastName field and then enters the given value.
     * @param lastName
     */
    public void enterLastName (String lastName){
        lastName().clear();
        lastName().sendKeys(lastName);
        Log.info("Successfully entered "+lastName+" in the Last Name field.");

    }

    /***
     *  This method verifies the visibility of  email field and then enters the given value.
     * @param email
     */
    public void enterEmail (String email){
        email().clear();
        email().sendKeys(email);
        Log.info("Successfully entered "+email+" in the  Email field.");

    }

    /***
     *  This method verifies the visibility of industry dropdown field and then enters the given value.
     * @param industry
     */
    public void selectIndustry (String industry){
        Select industryDropDown = new Select(industryDrpDwn());
        industryDropDown.selectByVisibleText(industry);
        Log.info("Successfully entered "+industry+" in the  industry dropdown field.");
    }

    /***
     *  This method verifies the visibility of  city field and then enters the given value.
     * @param city
     */
    public void enterCity (String city){
        city().clear();
        city().sendKeys(city);
        Log.info("Successfully entered "+city+" in the city field.");

    }

    /***
     *  This method verifies the visibility of message field and then enters the given value.
     * @param message
     */
    public void enterMessage (String message){
        message().clear();
        message().sendKeys(message);
        Log.info("Successfully entered "+message+" in the message field.");

    }

    /***
     *  This method verifies the visibility of message field and then enters the given value.
     *
     */
    public void clickSubmit ( ){
        submitButton().click();
        Log.info("Successfully clicked submit button.");

    }

    /***
     *  This method returns the text of email error field
     *
     */
    public String getEmailErrorMessage ( ){
        String errorMessage = emailErrorMessage().getText();
        Log.info("Email Error message is :"+errorMessage);
        return errorMessage;
    }


    /***
     *  This method returns the text contact info email
     *
     */
    public String getContactInfoEmail( ){
        String contactEmail = contactInfoEmail().getText();
        Log.info("Contact info Email is :"+contactEmail);
        return contactEmail;
    }


    /***
     *  This method returns the text contact info Address
     *
     */
    public String getContactInfoAddress( ){
        String contactAddress = contactInfoAddress().getText();
        Log.info("Contact info Address is :"+contactAddress);
        return contactAddress;
    }



}
