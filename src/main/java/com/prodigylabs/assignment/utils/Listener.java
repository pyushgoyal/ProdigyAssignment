package com.prodigylabs.assignment.utils;
import org.testng.ITestListener;
import org.testng.ITestContext;
import org.testng.ITestResult;


public class Listener extends TestBase implements ITestListener {

    public void onTestStart(ITestResult result) {
        Log.info("************************   Started Method" + result.getName() + "  ***********************************");
    }

    public void onTestSuccess(ITestResult result) {
        String name = "Pass_"+result.getName();
        takeScreenshot(name);
        Log.info("Test Case method " + result.getName() + " Passed");
    }

    public void onTestFailure(ITestResult result) {
        String name = "Failure_"+result.getName();
        takeScreenshot(name);
        Log.warn("Test Case method " + result.getName() + " Failed");
    }

    public void onTestSkipped(ITestResult result) {
        Log.info("Test Case method " + result.getName() + " Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        Log.info("Test Case  " + result.getName() + " failed but within success percentage.");
    }

    public void onStart(ITestContext context) {
        Log.info("************************   Started" + context.getName() + "  ***********************************");
    }

    public void onFinish(ITestContext context) {
        Log.info("************************   END" + context.getName() + "  ***********************************");
    }

}
