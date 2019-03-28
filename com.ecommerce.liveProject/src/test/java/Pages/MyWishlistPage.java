package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.BaseTest;

public class MyWishlistPage  extends BaseTest{
	
	
	
	@FindBy(xpath="//button[@name='save_and_share']")
	WebElement shareWishlist;
	
	@FindBy(css=".success-msg>ul>li>span")
	WebElement shareWishlistSuccessMessage;
	
	@FindBy(xpath="//table[@id='wishlist-table']/tbody/tr/td[5]/div/button")
	WebElement addToCart;
	
	public MyWishlistPage() {
		PageFactory.initElements(driver, this);
	}
	
	//get page title
	public String getTitle() {
		return driver.getTitle();
	}

	//click 'SHARE WASHLIST' button
	public void clickButtonOrLink(String linkName) {
		if(linkName.equalsIgnoreCase("share wishlist")) {
		shareWishlist.click();
		}else if(linkName.equalsIgnoreCase("add to cart")) {
			addToCart.click();
		}
	}
	
	//click 'ADD TO CART' link()
	
	
	//get share wishlist success message
	public String getShareWishlistSuccessMessage() {
		return shareWishlistSuccessMessage.getText();
	}
}
