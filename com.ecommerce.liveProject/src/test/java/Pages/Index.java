package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.BaseTest;

public class Index extends BaseTest{
	@FindBy(xpath="//nav[@id='nav']/ol/li/a")
	WebElement mobile;
	
	@FindBy(tagName="h2")
	WebElement heading;
	
	@FindBy(css="a[href='http://live.guru99.com/index.php/customer/account/']")
	WebElement account;
	
	@FindBy(css="a[title='My Account']")
	WebElement myAccount;
	
	@FindBy(xpath="//div[@class='footer']/div[4]/ul/li/a[@title='My Account']")
	WebElement myAccountFooter;
	
	public Index() {
		PageFactory.initElements(driver, this);
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	//get page heading 'THIS IS DEMO SITE FOR...'
	public String getHeading() {
		return heading.getText();
	}
	
	//Click 'MOBILE' link  
	public void goToMobilePage() {
		mobile.click();
	}
	
	
	
	//click "Account" link
	public void clickAccountLink() {
		account.click();
	}
	
	//Click "MY Account" link . one is 'Account'dropdown, the other is in thefooter
	public void clickMyAccount(String linkName) {
		if(linkName.equalsIgnoreCase("my account")) {
			WebDriverWait wait=new WebDriverWait(driver,20);
			wait.until(ExpectedConditions.visibilityOf(myAccount));
			myAccount.click();
		}else if(linkName.equalsIgnoreCase("my account footer")) {
			myAccountFooter.click();
		}
	}
}
