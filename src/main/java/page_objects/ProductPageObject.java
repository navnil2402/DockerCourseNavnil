package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility_methods.WebElementsInteractions;

public class ProductPageObject extends WebElementsInteractions {
	
	WebDriver driver;
	
	public ProductPageObject(WebDriver driver) {
        super(driver);
    }
	private final By getTitleOfProductPage = By.xpath("//span[contains(text(), 'Products')]");
	private final By getTextOf1stItem = By.xpath("//a[@id='item_4_title_link']/div");
	
	public String getTitleOfPage() {
		return getTextData(getTitleOfProductPage);
	}
	public String getItemName()
    {
        return getTextData(getTextOf1stItem);
    }
}
