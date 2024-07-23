package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import base.Base;

public class OpenAccountPage extends Base{
static WebDriver driver;
	
	public OpenAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[@ng-class='btnClass2']")
	WebElement openaccount;
	
	@FindBy(id = "userSelect")
	WebElement userselect;
	
	@FindBy(id = "currency")
	WebElement currency;
	
	@FindBy(xpath = "//*[@type='submit']")
	WebElement submit;
	
	public void clickOnOpenAccount() {
		openaccount.click();
	}
	
	public void inputsToOpenAccount(String name, String Currency) {
		explicitWait(driver, userselect);
		
		Select selUser = new Select(userselect);
		selUser.selectByVisibleText(name.toString());
		
		Select selCurrency = new Select(currency);
		selCurrency.selectByVisibleText(Currency);

		submit.click();
	}
	
	public String[] alertRetrieval() {
		Alert alert =driver.switchTo().alert();
		String alertMsg = alert.getText();;
		String[] alertNotify = alertMsg.split(":");
		alert.accept();
		return alertNotify;
	}
}
