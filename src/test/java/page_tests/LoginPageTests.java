package page_tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import page_objects.LoginPageObject;
import page_objects.ProductPageObject;

public class LoginPageTests extends BaseTest{
	
	LoginPageObject loginPageObject;
	ProductPageObject productPageObject;
	
	private static final Logger logger = LogManager.getLogger(LoginPageTests.class);
	
	@Test
	public void userLoginTest() {
		loginPageObject = new LoginPageObject(driver);
		productPageObject = loginPageObject.userLogin("standard_user", "secret_sauce");
		//System.out.println(productPageObject.getTitleOfPage());
		logger.info(productPageObject.getTitleOfPage());
		
	}

}
