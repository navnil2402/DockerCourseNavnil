package page_tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import base.AppConstants;
import base.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;

import static ReportUtils.ExtentReportProvider.getReportObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

public class BaseTest {

	protected WebDriver driver;
	protected String browser;
	
	//protected static ThreadLocal<ExtentTest> testLogger = new ThreadLocal<ExtentTest>();
	//private static final ExtentReports reports = getReportObject();

	//private ChromeOptions co;
	//private FirefoxOptions fo;

	private static final Logger logger = LogManager.getLogger(BaseTest.class);
	
	@Parameters({"browserName"})
	@BeforeTest
//	public void setupTest(@Optional String browserName, ITestResult iTestResult) {
	public void setupTest(@Optional String browserName) {
		
		ChromeOptions co = new ChromeOptions();		
		FirefoxOptions fo = new FirefoxOptions();
		if(browserName!=null) {
			browser = browserName;
			//System.out.println("Browser Name is: "+browser);
			logger.info("Browser Name is: "+browser);
		}
		else {
			browser = AppConstants.BROWSERNAME;
		}

		if (browser.equalsIgnoreCase("chrome")) {
			if (AppConstants.platform.equalsIgnoreCase("local")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
			}
			else if(AppConstants.platform.equalsIgnoreCase("remote")) {
				co.setPlatformName("linux");
				co.setPageLoadStrategy(PageLoadStrategy.EAGER);
				try {
					driver = new RemoteWebDriver(new URL("http://localhost:4444"), co);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
			else if(AppConstants.platform.equalsIgnoreCase("remote_git"))
            {
                co.addArguments("--headless"); //for GitHub actions
                co.addArguments("--disable-gpu");
                co.addArguments("--no-sandbox");
                co.addArguments("--remote-allow-origins=*");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(co);
            }
		} else if (browser.equalsIgnoreCase("firefox")) {
			if (AppConstants.platform.equalsIgnoreCase("local")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
			}
			else if(AppConstants.platform.equalsIgnoreCase("remote")) {
				fo.setPlatformName("linux");
				fo.setPageLoadStrategy(PageLoadStrategy.EAGER);
				try {
					driver = new RemoteWebDriver(new URL("http://localhost:4444"), fo);
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
			else if(AppConstants.platform.equalsIgnoreCase("remote_git"))
            {
                fo.addArguments("--headless"); //for GitHub actions
                fo.addArguments("--disable-gpu");
                fo.addArguments("--no-sandbox");
              //  fo.addArguments("--remote-allow-origins=*"); not required for GitHub actions execution flow
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(fo);
            }
			
		}
		else {
			//System.out.println("Browser Name Entered is Not Supported");
			logger.info("Browser Name Entered is Not Supported");
		}
		
		//ExtentTest test = reports.createTest(iTestResult.getMethod().getMethodName());
		//testLogger.set(test);
		//testLogger.get().log(Status.INFO, "Driver start time: "+ LocalDateTime.now());
	}
	
	@AfterTest
	public void teardownTest() {
		/*if(iTestResult.isSuccess()) {
			testLogger.get().log(Status.PASS, MarkupHelper.createLabel(iTestResult.getMethod().getMethodName() + "is successful ", ExtentColor.GREEN));
		}
		else {
			testLogger.get().log(Status.FAIL, "Test failed due to: "+ iTestResult.getThrowable());
			String screenshot = BasePage.getScreenshot(iTestResult.getMethod().getMethodName() + ".jpg", driver);
			testLogger.get().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(BasePage.convertToBase64(screenshot)).build());
			testLogger.get().log(Status.INFO, "Driver End Time: " +  LocalDateTime.now());
			
		}*/
		driver.quit();
	}
	
	@AfterClass
	public void flushTestReporter() {
		//reports.flush(); //details captured till now by reports, all the information will be added in the reports
	}
	
}
