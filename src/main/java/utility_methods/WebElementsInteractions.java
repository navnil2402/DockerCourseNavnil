package utility_methods;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WebElementsInteractions {
	
	protected WebDriver driver;
	
	//custom keywords that help us to implement keyword driven strategy
	
	public WebElementsInteractions(WebDriver driver)
    {
        this.driver = driver;
    }
	
	public void clickElement(By locator) {
		driver.findElement(locator).click();
	}
	
	public void sendText(By locator,String text) {
		driver.findElement(locator).sendKeys(text);
	}
	
	public void goToApplication(String url) {
		driver.get(url);
	}
	
	public String getTextData(By locator) {
		return driver.findElement(locator).getText();
	}
}
