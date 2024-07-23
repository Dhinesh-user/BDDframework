package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class WithdrawalPage extends Base{
static WebDriver driver;
	
	public WithdrawalPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[@placeholder='amount']")
	WebElement amountText;
	
	@FindBy(xpath = "//*[@type='submit']")
	WebElement deposit;
	
	@FindBy(xpath = "(//*[@class='ng-binding'])[2]")
	WebElement balance;
	
	@FindBy(xpath = "//*[@ng-show='message']")
	WebElement withdrawMessage;
	
	@FindBy(xpath = "//*[text()='Amount to be Withdrawn :']")
	WebElement withdrawLocator;
	
	@FindBy(xpath = "//*[text()='Logout']")
	WebElement logout;
	
	@FindBy(xpath = "//*[@ng-class='btnClass1']")
	WebElement transactions;
	
	public void amountWithdraw(int amt) {
		explicitWait(driver, amountText);
		amountText.clear();
		amountText.sendKeys(Integer.toString(amt));
		deposit.click();
	}
	
	public int returnBalance() {
		explicitWait(driver,withdrawLocator);
		return Integer.parseInt(balance.getText());
	}
	
	public String returnWithdrawMessage() {
		return withdrawMessage.getText();
	}
	
	public void clickTransactions() {
		transactions.click();
	}
	
	public void customerLogout() {
		logout.click();
	}
	
	
}
