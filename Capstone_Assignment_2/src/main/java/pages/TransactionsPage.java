package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class TransactionsPage extends Base {
static WebDriver driver;
	
	public TransactionsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[text()='Logout']")
	WebElement logout;
	
	public void customerLogout() {
		logout.click();
	}
	
	public String returnUrl() {
		explicitWaitUntilUrl(driver, "listTx");
		return driver.getCurrentUrl();
	}
}
