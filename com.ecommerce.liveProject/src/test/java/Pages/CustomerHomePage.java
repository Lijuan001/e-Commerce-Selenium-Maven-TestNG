package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.BaseTest;


public class CustomerHomePage  extends BaseTest{
	
	@FindBy(css="p[class='hello']")
	WebElement welcomeText;
	
	@FindBy(linkText="TV")
	WebElement tvLink;
	
	
	//sidebar menu
	@FindBy(xpath="//div[@class='block-content']/ul/li[8]/a")
	WebElement myWishlist;
	
	//sidebar menu
		@FindBy(xpath="//div[@class='block-content']/ul/li[4]/a")
		WebElement myOrders;
	
	@FindBy(xpath="//a[@title='LG LCD']/../div/div[3]/ul/li/a")
	WebElement lgLCDAddToWishlist;
	
	@FindBy(xpath="//a[@title='Samsung LCD']/../div/div[3]/ul/li/a")
	WebElement sumsungLCDAddToWishlist;
	
	@FindBy(xpath="//table[@id='my-orders-table']/tbody/tr[1]/td[6]/span/a[2]")
	WebElement reorder;
	
	
	public CustomerHomePage() {
		PageFactory.initElements(driver, this);
	}
	
	//get page title
	public String getTitle(){
		return driver.getTitle();
	}
	
	//get welcome text, eg:' Hello, jane shen!'
	public String getWelcomeText() {
		return welcomeText.getText();
	}
	
	//click 'TV' LINK
	public void clickTVLink() {
		tvLink.click();
	}
	
	//click side bar menu
	public void clickSideBar(String sidebarMenu) {
		if(sidebarMenu.equalsIgnoreCase("my wishlist")) {//click 'MY WISHLIST'
			System.out.println("should click my wishlist: "+myWishlist.getText());
			myWishlist.click();
		}else if(sidebarMenu.equalsIgnoreCase("my orders")) {//click 'MY ORDERS'
			myOrders.click();
		}
	}
	
	//Add product in your wish list
	public void addProductToWishList(String productName) {
		if(productName.equalsIgnoreCase("LG LCD")) {
			lgLCDAddToWishlist.click();
		}else if(productName.equalsIgnoreCase("SAMSUNG LCD"))
			sumsungLCDAddToWishlist.click();	
	}
	
	//click 'REORDER' link
	public void clickReorder() {
		reorder.click();
	}
}
