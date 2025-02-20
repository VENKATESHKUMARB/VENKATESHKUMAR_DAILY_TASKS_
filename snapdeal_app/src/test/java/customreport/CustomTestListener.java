package customreport;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomTestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        Reporter.log("Test Started: " + result.getName() + " at " + getCurrentTime(), true);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Reporter.log("Test Passed: " + result.getName() + " at " + getCurrentTime(), true);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Reporter.log("Test Failed: " + result.getName() + " at " + getCurrentTime(), true);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        Reporter.log("Test Skipped: " + result.getName() + " at " + getCurrentTime(), true);
    }

    @Override
    public void onStart(ITestContext context) {
        Reporter.log("Test Suite Started: " + context.getName() + " at " + getCurrentTime(), true);
    }

    @Override
    public void onFinish(ITestContext context) {
        Reporter.log("Test Suite Finished: " + context.getName() + " at " + getCurrentTime(), true);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        Reporter.log("Test Failed but within success percentage: " + result.getName() + " at " + getCurrentTime(), true);
    }

    private String getCurrentTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}