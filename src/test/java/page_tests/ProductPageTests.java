package page_tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import page_objects.LoginPageObject;
import page_objects.ProductPageObject;

public class ProductPageTests extends BaseTest{
	
	ProductPageObject productPageObject;
	LoginPageObject loginPageObject;
	private static final Logger logger = LogManager.getLogger(ProductPageTests.class);
	
	@Test
	public void testItemName() {
		loginPageObject = new LoginPageObject(driver);
		productPageObject = loginPageObject.userLogin("standard_user", "secret_sauce");
		//System.out.println(productPageObject.getTitleOfPage());
		logger.info(productPageObject.getTitleOfPage());
		//System.out.println(productPageObject.getItemName());
		logger.info(productPageObject.getItemName());
	}
}
