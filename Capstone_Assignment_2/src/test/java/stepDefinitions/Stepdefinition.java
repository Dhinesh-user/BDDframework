package stepDefinitions;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.*;
import base.Base;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import junit.framework.Assert;
import pages.AccountPage;
import pages.AddCustomerPage;
import pages.CustomerLoginPage;
import pages.CustomerPage;
import pages.DepositPage;
import pages.LoginPage;
import pages.OpenAccountPage;
import pages.TransactionsPage;
import pages.WithdrawalPage;

public class Stepdefinition extends Base{
	
	static WebDriver driver;
	LoginPage login;
	CustomerLoginPage custLogin;
	AccountPage accLogin;
	DepositPage deposit;
	WithdrawalPage withdraw;
	TransactionsPage tran;
	AddCustomerPage addCust;
	OpenAccountPage openAcc;
	CustomerPage cust;
	static Properties p;
	public int balanceInitial, amount;
	public static String custId, alertMsg, custName;
	public int accId;
	
	@Before("@login or @addCustomer")
	public void initProperty() throws IOException {
		FileInputStream fin = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config\\config.properties");
		p=new Properties();
		p.load(fin);
	}
	@Given("user is on login page")
	public void user_is_on_login_page() {
		driver = launchBrowser();
		windowMaximize();
		getUrl(p.getProperty("loginPageUrl"));
		implicitWait(driver);
	}

	@When("user is on customer login")
	public void user_is_on_customer_login() {
		login = new LoginPage(driver);
		login.clickOnHomeButton();
		login.clickOnCustomerLogin();
	}

	@When("selects the customer name")
	public void selects_the_customer_name() {
	    custLogin = new CustomerLoginPage(driver);
	    custLogin.selectName("Hermoine Granger");
	}

	@Then("account details should be displayed")
	public void account_details_should_be_displayed() {
		accLogin = new AccountPage(driver);
	    Assert.assertEquals(p.getProperty("accountPageUrl"), accLogin.returnUrl());
	}

	@Given("user is on deposit page")
	public void user_is_on_deposit_page() {
		accLogin = new AccountPage(driver);
	    accLogin.clickDeposit();
	}

	@When("user gives {int} and clicks on deposit button")
	public void user_gives_and_clicks_on_deposit_button(Integer amt) {
		this.amount = amt;
	    deposit = new DepositPage(driver);
	    this.balanceInitial = deposit.returnBalance();
	    deposit.amountDeposit(amt);
	}

	@Then("amount should be deposited succesfully")
	public void amount_should_be_deposited_succesfully() {
		int balanceAfterDeposit = deposit.returnBalance();
	    if(amount>0) {
	    	Assert.assertEquals(balanceInitial+amount, balanceAfterDeposit);
	    	Assert.assertEquals(deposit.returnDepositMessage(), p.getProperty("depositMsg"));
	    }
	    if(amount==0) {
	    	Assert.assertEquals(balanceInitial, balanceAfterDeposit);
	    }
	    if(amount<0) {
	    	Assert.assertEquals(balanceInitial, balanceAfterDeposit);
	    }
	}

	@Given("user is on withdrawal page")
	public void user_is_on_withdrawal_page() {
		accLogin = new AccountPage(driver);
		accLogin.clickWithdrawl();
	}

	@When("user gives {int} and clicks on withdrawal button")
	public void user_gives_and_clicks_on_withdrawal_button(Integer amt) {
		this.amount = amt;
		withdraw = new WithdrawalPage(driver);
	    this.balanceInitial = withdraw.returnBalance();
	    withdraw.amountWithdraw(amt);
	}

	@Then("amount should be withdrawn succesfully")
	public void amount_should_be_withdrawn_succesfully() {
		int balanceAfterWithdraw = withdraw.returnBalance();
	    if(amount>0) {
	    	Assert.assertEquals(balanceInitial-amount, balanceAfterWithdraw);
	    	Assert.assertEquals(withdraw.returnWithdrawMessage(),  p.getProperty("transactionMsg"));
	    }
	    if(amount==0) {
	    	Assert.assertEquals(balanceInitial, balanceAfterWithdraw);
	    }
	    if(amount<0) {
	    	Assert.assertEquals(balanceInitial, balanceAfterWithdraw);
	    }
	}

	@When("user clicks on logout button")
	public void user_clicks_on_logout_button() {
		tran = new TransactionsPage(driver);
	    tran.customerLogout();
	}

	@Then("user should get logged out")
	public void user_should_get_logged_out() {
		 custLogin = new CustomerLoginPage(driver);
		 Assert.assertEquals(p.getProperty("customerPageUrl"), custLogin.returnUrl());
	}
	
	@When("user clicks on transactions button")
	public void user_clicks_on_transactions_button() {
		withdraw = new WithdrawalPage(driver);
	    withdraw.clickTransactions();
	}

	@Then("user should be able to see transactions")
	public void user_should_be_able_to_see_transactions() {
		tran = new TransactionsPage(driver);
		Assert.assertEquals(p.getProperty("transactionPageUrl"), tran.returnUrl());
	}
	
	@Given("user is on manager login page")
	public void user_is_on_manager_login_page() {
	    login = new LoginPage(driver);
	    String url = login.clickOnBankManagerLogin(driver);
	    Assert.assertTrue(url.contains("manager"));
	}

	@When("user clicks on add customer button")
	public void user_clicks_on_add_customer_button() {
	    addCust = new AddCustomerPage(driver);
	    addCust.clickOnAddCustomer();
	}

	@When("^user gives (.*) (.*) (.*) as input$")
	public void user_gives_anna_marie_as_input(String fname, String lname, Integer zipcode) {
		this.custName= fname+" "+lname;
		addCust.inputsToAddCustomer(fname, lname, Integer.toString(zipcode));
	}

	@Then("user should be able to add")
	public void user_should_be_able_to_add() {
	   String[] alertNotify = addCust.alertRetrieval();
	   this.custId = alertNotify[1];
	   this.alertMsg = alertNotify[0];
	   Assert.assertTrue(alertMsg.contains(p.getProperty("addCustomerAlertMsg")));
	   Assert.assertEquals(custId, "6");
	}
	
	@Given("user is on Open Account page")
	public void user_is_on_open_account_page() {
	   openAcc = new OpenAccountPage(driver);
	   openAcc.clickOnOpenAccount();
	}

	@When("user gives customer name and currency details")
	public void user_gives_customer_name_and_currency_details() {
		System.out.println(custName);
		openAcc.inputsToOpenAccount(custName, p.getProperty("currency"));
	}

	@Then("account should be opened")
	public void account_should_be_opened() {
	   String[] alertNotify = openAcc.alertRetrieval();
	   this.accId = Integer.parseInt(alertNotify[1]);
	   this.alertMsg = alertNotify[0];
	   Assert.assertTrue(alertMsg.contains(p.getProperty("accountCreationAlertMsg")));
	}
	
	@Given("user is on customers page")
	public void user_is_on_customers_page() {
		cust = new CustomerPage(driver);
		cust.clickOnCustomer();
	}

	@When("user gives customer name")
	public void user_gives_customer_name() {
	    cust.searchForCustomer(custName);
	}

	@Then("account should be deleted")
	public void account_should_be_deleted() {
	    Assert.assertFalse(cust.validateDelete(custName));
	}
	
	@After("@customerDelete or @logout")
	public void teardown() {
		close();
	}

}
