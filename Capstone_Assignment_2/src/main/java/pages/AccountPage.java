package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class AccountPage extends Base{
static WebDriver driver;
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//*[@class='ng-binding'])[2]")
	WebElement balance;
	
	@FindBy(xpath = "//*[@ng-class='btnClass1']")
	WebElement transactions;
	
	@FindBy(xpath = "//*[@ng-class='btnClass2']")
	WebElement deposit;
	
	@FindBy(xpath = "//*[@ng-class='btnClass3']")
	WebElement withdrawl;
	
	
	public String returnUrl() {
		explicitWaitUntilUrl(driver, "account");
		return driver.getCurrentUrl();
	}
	
	public void clickTransactions() {
		transactions.click();
	}
	
	public void clickDeposit() {
		deposit.click();
	}
	
	public void clickWithdrawl() {
		withdrawl.click();
	}

}
