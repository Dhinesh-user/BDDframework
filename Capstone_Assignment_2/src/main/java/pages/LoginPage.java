package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class LoginPage extends Base{
	static WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[text()='Home']")
	WebElement home;
	
	@FindBy(xpath = "//*[text()='Customer Login']")
	WebElement customerLogin;
	
	@FindBy(xpath = "//*[text()='Bank Manager Login']")
	WebElement bankManagerLogin;
	
	
	public void clickOnHomeButton() {
		home.click();
	}
	
	public void clickOnCustomerLogin() {
		customerLogin.click();
	}
	
	public String clickOnBankManagerLogin(WebDriver driver) {
		home.click();
		bankManagerLogin.click();
		explicitWaitUntilUrl(driver, "manager");
		return driver.getCurrentUrl();
	}
}
