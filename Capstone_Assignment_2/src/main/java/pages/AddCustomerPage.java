package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class AddCustomerPage extends Base{
static WebDriver driver;
	
	public AddCustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[@ng-class='btnClass1']")
	WebElement addcustomer;
	
	@FindBy(xpath = "//*[@placeholder='First Name']")
	WebElement firstname;
	
	@FindBy(xpath = "//*[@placeholder='Last Name']")
	WebElement lastname;
	
	@FindBy(xpath = "//*[@placeholder='Post Code']")
	WebElement zip;
	
	@FindBy(xpath = "//*[@type='submit']")
	WebElement submit;
	
	public void clickOnAddCustomer() {
		addcustomer.click();
	}
	
	public void inputsToAddCustomer(String fname, String lname, String zipcode) {
		explicitWait(driver, firstname);
		firstname.sendKeys(fname);
		lastname.sendKeys(lname);
		zip.sendKeys(zipcode);
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
