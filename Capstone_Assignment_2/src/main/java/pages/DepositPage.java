package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.Base;

public class DepositPage extends Base{
static WebDriver driver;
	
	public DepositPage(WebDriver driver) {
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
	WebElement depositMessage;
	
	public void amountDeposit(int amt) {
		explicitWait(driver, amountText);
		amountText.clear();
		amountText.sendKeys(Integer.toString(amt));
		deposit.click();
	}
	
	public int returnBalance() {
		return Integer.parseInt(balance.getText());
	}
	
	public String returnDepositMessage() {
		return depositMessage.getText();
	}
	
}
