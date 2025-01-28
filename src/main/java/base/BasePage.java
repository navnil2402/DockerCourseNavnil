package base;import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class BasePage {

	public static String getScreenshot(String imageName, WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File file = ts.getScreenshotAs(OutputType.FILE);
		String filePath = "./screenshot/" + imageName;
		try {
			FileUtils.copyFile(file, new File((filePath)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filePath;
	}
	
	public static String convertToBase64(String screenshotPath) {
		byte[] file = new byte[0];
		try {
			file = FileUtils.readFileToByteArray(new File(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String base64Image = Base64.getEncoder().encodeToString(file);
		return base64Image;
	}
}
