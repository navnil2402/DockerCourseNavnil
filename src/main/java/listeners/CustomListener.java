package listeners;

import static ReportUtils.ExtentReportProvider.getReportObject;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class CustomListener implements ITestListener{

	protected static ThreadLocal<ExtentTest> testLogger = new ThreadLocal<ExtentTest>();
	private static final ExtentReports reports = getReportObject();
	
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = reports.createTest(result.getMethod().getMethodName());
        testLogger.set(test);
        testLogger.get().log(Status.INFO, "Test started: " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testLogger.get().log(Status.PASS, "Test passed!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        testLogger.get().log(Status.FAIL, "Test failed: " + result.getThrowable());
      //  String screenshot = BasePage.getScreenshot(result.getMethod().getMethodName() + ".jpg", driver);
	//	testLogger.get().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(BasePage.convertToBase64(screenshot)).build());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testLogger.get().log(Status.SKIP, "Test skipped: " + result.getThrowable());
        //custom
    }

    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onFinish(ITestContext context) {
    	reports.flush();
    }
}


