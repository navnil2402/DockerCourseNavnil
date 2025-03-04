package ReportUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportProvider {
	
	public static ExtentReports reports;
	
	static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_hh_mm_ss");
	
	public static ExtentReports getReportObject() {
		
		String reportPath = "./reports/" + dateTimeFormatter.format(LocalDateTime.now());
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
		
		sparkReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Automation Results");
		sparkReporter.config().setDocumentTitle("Test Results");
		sparkReporter.config().setJs("document.getElementsByClassName('col-sm-12 col-md-4')[0].style.setProperty('min-inline-size','-webkit-fill-available');");
		
		reports = new ExtentReports();
		reports.attachReporter(sparkReporter);
		
		reports.setSystemInfo("Automation Engineer", "Navnil Niraj");
		return reports;
	}
}
