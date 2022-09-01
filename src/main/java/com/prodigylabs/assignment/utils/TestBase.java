package com.prodigylabs.assignment.utils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/***
 * This class contains all the driver specific common util methods. This class is extened by all the
 * workflows and testcases.
 * This class is responsible for creating the driver instance and also loading the properties.
 *
 */

public class TestBase {

    private static WebDriver driver;
    private final String propertiespath=  ".\\src\\main\\resources\\properties\\";
    private final String driverpath=  ".\\src\\main\\resources\\drivers\\";
    public static Properties property = new Properties();
    FileInputStream fs1,fs2;

    @BeforeSuite
    public void loadProperties(){
        try{
            fs1 = new FileInputStream(propertiespath+"environment.properties");
            fs2 = new FileInputStream(propertiespath+"user.properties");
            property.load(fs1);
            property.load(fs2);
        }catch (IOException e){
            Log.error("Exception Occurred while reading property file: "+e.getMessage());
        }

    }

    @BeforeClass
    @Parameters({"browser","environment" })
    public void setUp(@Optional("Chrome") String browser,String environment) {
        setDriver(browser);
        driver = getDriver();
        launchUrl(property.getProperty(environment));
        maximize();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    /***
     * The setDriver method creates instance of Webdriver based on the input parameter
     * which is passed on from calling method.
     * @param browser
     */
    protected void setDriver(String browser) {
        try {
            if (browser.equalsIgnoreCase("Firefox")) {
                System.setProperty("webdriver.firefox.marionette",
                        driverpath+"geckodriver.exe");
                TestBase.driver = new FirefoxDriver();
                Log.info("Created Instance of Firefox WebDriver");
            } else if (browser.equalsIgnoreCase("Chrome")) {
                System.setProperty("webdriver.chrome.driver",
                        driverpath+"chromedriver.exe");
                TestBase.driver = new ChromeDriver();
                Log.info("Created Instance of Chrome WebDriver");
            } else if (browser.equalsIgnoreCase("Edge")) {
                System.setProperty("webdriver.edge.driver",
                        driverpath+"MicrosoftWebDriver.exe");
                TestBase.driver = new EdgeDriver();
                Log.info("Created Instance of Edge WebDriver");
            }
        } catch (Exception e) {
            Log.error("Exception Occurred while initiating the instance of" + browser + " WebDriver" + e.getMessage());
        }
    }


/***
     * This returns the current instance of WebDriver. This is a synchronized
     * method.
     *
     * @return
     */
    protected static synchronized WebDriver getDriver() {
        return driver;
    }


    /***
     * This method launches the given url in the driver instance.
     * @param url
     */
    protected void launchUrl(String url) {
        try{
            driver.get(url);
            Log.info("Successfully launched URL:"+url);
        }catch (Exception e){
            Log.error("Exception occurred while launching URl");
        }
    }


    /***
     * This method maximizes the browser window
     */
    protected void maximize() {driver.manage().window().maximize(); }

    /***
     * This method hovers the control on the WebElement passed as parameter
     *
     * @param testElement
     * @param elementName
     */
    protected  static void hoverOnElement(WebElement testElement, String elementName) {
        try {
            Actions actHover = new Actions(driver);
            if (testElement.isDisplayed()) {
                actHover.moveToElement(testElement).build().perform();
                Log.info("Successfully hovered on element: " + elementName);
            } else {
                Log.info("Unable to hover on element: " + elementName);
            }
        } catch (Exception e) {
            Log.error("Exception occurred while trying to hover on element: " + elementName);
        }

    }


    /***
     * This method click on a given WebElement passed as parameter.
     *
     * @param testElement
     * @param elementName
     */
    protected void clickOnElement(WebElement testElement, String elementName) {
        try {
            if (testElement.isDisplayed()) {
                testElement.click();
                Log.info("Successfully Clicked on element: " + elementName);
            } else {
                Log.info("Unable to click on Element: " + elementName);
            }

        } catch (Exception e) {
            Log.error("Exception occurred while trying to click on element: " + elementName);
        }

    }

    /***
     * This method waits for a clickable Webelement passed on as parameter for a
     * given time.
     *
     * @param driver
     * @param testElement
     * @param maxTimeOutInSeconds
     */
    protected void waitForClickableWebElement(WebDriver driver, WebElement testElement, int maxTimeOutInSeconds) {
        try {
            if (driver != null) {
                FluentWait<WebDriver> wait = new FluentWait(driver);
                wait.pollingEvery(Duration.ofMillis(100L));
                wait.withTimeout(Duration.ofSeconds((long) maxTimeOutInSeconds));
                wait.ignoring(StaleElementReferenceException.class, NoSuchElementException.class);
                wait.until(ExpectedConditions.elementToBeClickable(testElement));
            } else {
                Log.error("Wait for WebElement: Driver is null so could not perform waitForClickableWebElement");
            }
        } catch (Exception e) {
            Log.error("Exception Occurred while waiting for element to be clickable. WebElement :" +testElement.toString());
            Log.error("Exception Message: "+ e.getMessage());
        }

    }


    /***
     * This method waits for visibility of a WebElement passed on as parameter for a
     * given time.
     * @param locator
     * @param maxTimeOutInSeconds
     */
    protected  static void  waitForVisibilityOfWebElement(By locator , int maxTimeOutInSeconds) {
        try {
                WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(maxTimeOutInSeconds));
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            Log.error("Exception Occurred while waiting for visibility of WebElement located by" +locator);
            Log.error("Exception Message: "+ e.getMessage());
        }

    }


    /***
     * This method compares the Text of a given WebElement with expected value.
     *
     * @param testElement
     * @param elementName
     * @param expectedValue
     * @return
     */
    protected boolean compareText(WebElement testElement, String elementName, String expectedValue) {
        boolean isMatch = false;
        try {
            String actualValue = testElement.getText();
            if (actualValue.equalsIgnoreCase(expectedValue)) {
                isMatch = true;
                Log.info("Compare Text: Expected Value: " + expectedValue + " is matching with Actual Value: "
                        + actualValue + " For WebElement: " + elementName);
            } else {
                Log.info("Compare Text: Expected Value: " + expectedValue + " does not match with Actual Value: "
                        + actualValue + " For WebElement: " + elementName);
            }
        } catch (Exception e) {
            Log.error("Exception Occurred while executing CompareText for WebElement: " + elementName);
        }
        return isMatch;

    }


    /***
     * This method takes Screenshot when it is invoked.
     */
    protected static void takeScreenshot(String name) {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy'-'MM'-'dd");
        String folderName = dateFormat1.format(new Date());
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("HH'_'mm'_'ss");
        String fileSuffix = "_"+ dateFormat2.format(new Date());
        try {
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File("./test-output\\FailureScreenShots\\"+folderName+"\\"+name+fileSuffix+".png");
            FileUtils.copyFile(SrcFile, DestFile);
            Log.info("Screenshot Captured.");
        } catch (Exception e) {
            Log.error("Exception Occurred while Taking ScreenShot: " + e.getMessage());
        }
    }

    /***
     * This method holds the execution of the script for a given number of seconds in the input.
     * @param timeInSeconds
     */
    protected  static void holdForNSeconds(int timeInSeconds){
        try{
            TimeUnit.SECONDS.sleep(timeInSeconds);
        }catch (Exception e){
            Log.error("Exception occurred in holdForNSeconds method");
        }

    }


    /***
     * This method holds the execution of the script for a given number of seconds in the input.
     * @param time
     */
    protected  static void waitForPageLoad(int time){
        Log.info("Starting wait for page load");
        JavascriptExecutor js = (JavascriptExecutor)driver;
        long start = System.currentTimeMillis();
        long end = start + time*1000;

        try{
            holdForNSeconds(2);
            while(!((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equalsIgnoreCase("complete")){
                if(System.currentTimeMillis() >=end){
                    break;
                }
            }
        }catch (Throwable error){
            Log.error("Error Occurred while waiting for page load");
        }

    }

    protected static void  pressEnterKey(WebElement testElement) {
        try{
            testElement.sendKeys(Keys.ENTER);
            waitForPageLoad(5);
        }
        catch(Throwable error){
            Log.error("Error Occurred while pressing enter key");
        }
    }

    /**
     * Softassert check for boolean values.
     *
     * @param softAssert The SoftAssert instance that will collect any failures.
     * @param actual     The value we got.
     * @param expected   The value we are hoping for
     * @param message    A note about what we are testing.
     */
    public static void assertEquals(SoftAssert softAssert, String actual, String expected, String message) {
        softAssert.assertEquals(actual, expected, ">>" + message + ": ");
    }


}








