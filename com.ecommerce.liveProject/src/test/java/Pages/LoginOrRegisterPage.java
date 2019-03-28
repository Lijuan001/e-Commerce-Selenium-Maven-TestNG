package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.BaseTest;

public class LoginOrRegisterPage extends BaseTest{
	@FindBy(css="a[title='Create an Account']")
	WebElement createAnAccount;
	
	//login webElements
	@FindBy(css="input[name='login[username]']")
	WebElement loginEmailAddress;
	
	@FindBy(css="input[name='login[password]']")
	WebElement loginPassword;
	
	@FindBy(css="button[title='Login']")
	WebElement loginButton;
	
	public LoginOrRegisterPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	//click 'CREATE AN ACCOUNT' button
	public void clickCreateAnAccount() {
		createAnAccount.click();
	}
	
	//login in application
	public void loginForm(String emailAddress,String password) throws InterruptedException {
		loginEmailAddress.clear();
		loginEmailAddress.sendKeys(emailAddress);
		
		loginPassword.clear();
		loginPassword.sendKeys(password);
		
		Thread.sleep(3000);
		loginButton.click();
	}
}
