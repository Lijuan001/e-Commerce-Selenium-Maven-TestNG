package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.BaseTest;

public class WishlistFormPage  extends BaseTest{
	
	
	
	@FindBy(id="email_address")
	WebElement email_address;
	
	@FindBy(xpath="//button[@title='Share Wishlist']")
	WebElement shareWishListButton;
	
	@FindBy(id="message")
	WebElement messageTextarea;
	
	public WishlistFormPage() {
		PageFactory.initElements(driver, this);
	}
	
	//get page title
	public String getTitle() {
		return driver.getTitle();
	}

	//fill share wishlist form and submit
	public void clickShareWashlistButton(String emailAddress,String message) throws InterruptedException {
		email_address.clear();
		email_address.sendKeys(emailAddress);
		
		messageTextarea.clear();
		messageTextarea.sendKeys(message);
		
		Thread.sleep(3000);
		
		shareWishListButton.click();
	}
}
