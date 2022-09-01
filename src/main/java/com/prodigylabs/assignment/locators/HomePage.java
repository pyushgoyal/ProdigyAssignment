package com.prodigylabs.assignment.locators;

import com.prodigylabs.assignment.utils.Log;
import com.prodigylabs.assignment.utils.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/***
 * This locator class/Page Class has the locators for all the different web elements on the home page of
 * https://prodigylabs.net/
 * This is a page factory class which returns the WebElement objects.
 */

public class HomePage extends TestBase {

    private static HomePage testObj;
    WebDriver driver =  getDriver();

    public synchronized static HomePage get() {
        if(testObj==null){
            testObj= new HomePage();
            return testObj;
        }
        return testObj;
    }


    /***
     * Defining Locators - By objects
     * These By objects are only for elements with Static locator paths
     */
    private By path_AcceptCookiesButton = new By.ByXPath("//*[@id=\"hs-eu-confirmation-button\"]");
    private By path_navbar_ContactLink = new By.ByLinkText("Contact");



    /***
     * Initiating WebElements using the By objects
     *
     */


    /***
     * Initiating WebElements using the By objects
     *
     */
    public  WebElement acceptCookiesButton (){
        waitForVisibilityOfWebElement(path_AcceptCookiesButton,5);
        WebElement acceptCookiesButton = driver.findElement(path_AcceptCookiesButton);
        return acceptCookiesButton;
    }

    public  WebElement navbar_ContactLink (){
        waitForVisibilityOfWebElement(path_navbar_ContactLink,5);
        WebElement navbar_ContactLink = driver.findElement(path_navbar_ContactLink);
        return navbar_ContactLink;
    }




    /****
     * This method waits for the visibility of Accept Cookie button  and then does click operation.
     */
    public void clickacceptCookiesButton (){
        acceptCookiesButton().click();
        Log.info("Successfully clicked on the Accept Cookie button.");

    }

    /****
     * This method waits for the visibility of Contact Link on the Navigation bar  and then does click operation.
     */
    public void navbar_ClickContactLink (){
        navbar_ContactLink().click();
        Log.info("Successfully clicked on the Contact Link.");

    }

    //Rest of the elements can be defined here based on location or sections. eg. footer, navbar, etc
    // This will help in managing the definition webElements within the code.

}
