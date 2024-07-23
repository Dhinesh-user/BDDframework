package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class CustomerPage extends Base{
static WebDriver driver;
	
	public CustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[@ng-class='btnClass3']")
	WebElement customer;
	
	@FindBy(xpath = "//*[@placeholder='Search Customer']")
	WebElement searchcust;
	
	@FindBy(xpath = "//*[text()='Delete']")
	WebElement delete;
	
	public void clickOnCustomer() {
		customer.click();
	}
	
	public void searchForCustomer(String cust) {
		explicitWait(driver, searchcust);
		searchcust.sendKeys(cust.split(" ")[0]);
		explicitWaitUntilClick(driver, delete);
		delete.click();
	}
	
	public Boolean validateDelete(String cust) {
		try {
			explicitWait(driver, searchcust);
			searchcust.clear();
			searchcust.sendKeys(cust.split(" ")[0]);
			return delete.isDisplayed();
		}
		catch(NoSuchElementException e) {
			return false;
		}
	}
}
