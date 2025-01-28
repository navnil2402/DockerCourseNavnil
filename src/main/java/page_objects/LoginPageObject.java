package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utility_methods.WebElementsInteractions;

public class LoginPageObject extends WebElementsInteractions{

	
	private final By userNameTextField = By.id("user-name");
	private final By passwordTextField = By.id("password");
	private final By loginButton = By.id("login-button");
	
	
	public LoginPageObject(WebDriver driver) {
		super(driver);
	}
	
	public ProductPageObject userLogin(String userName, String password) {
		goToApplication("https://www.saucedemo.com/");
		sendText(userNameTextField, userName);
		sendText(passwordTextField, password);
		clickElement(loginButton);
		return new ProductPageObject(driver);
	}
}
