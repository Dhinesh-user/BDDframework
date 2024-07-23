package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import base.Base;

public class CustomerLoginPage extends Base {
	static WebDriver driver;
	
	public CustomerLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "userSelect")
	WebElement nameDropdrop;
	
	@FindBy(xpath = "//*[text()='Login']")
	WebElement login;
	
	
	public void selectName(String name) {
		explicitWait(driver, nameDropdrop);
		nameDropdrop.click();
		Select sel = new Select(nameDropdrop);
		sel.selectByVisibleText(name);
		explicitWait(driver, login);
		login.click();
	}
	
	public String returnUrl() {
		explicitWaitUntilUrl(driver, "customer");
		return driver.getCurrentUrl();
	}
}	
