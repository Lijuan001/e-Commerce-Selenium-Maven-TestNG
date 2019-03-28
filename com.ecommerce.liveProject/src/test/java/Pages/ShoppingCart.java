package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.BaseTest;

public class ShoppingCart extends BaseTest{
	@FindBy(xpath="//input[@title='Qty']")
	WebElement qty;
	
	@FindBy(css="input[title='Qty']+button")
	WebElement update;
	
	
	@FindBy(css="p[class='item-msg error']")
	WebElement errorMessageQTY;
	
	@FindBy(css="button[title='Empty Cart']")
	WebElement emptyCart;
	
	@FindBy(css=".page-title>h1")
	WebElement emptyCartMessage;
	
	@FindBy(xpath="//*[@class='checkout-types top']/li/button")
	WebElement proceedToCheckOutTop;
	
	@FindBy(xpath="//*[@class='checkout-types bottom']/li/button")
	WebElement proceedToCheckOutBottom;
	
	public ShoppingCart() {
		PageFactory.initElements(driver, this);
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	//Change 'QTY' value to 1000 and click 'UPDATE' button
	public void changeQTYAndUpdate() {
		qty.clear();
		qty.sendKeys(String.valueOf(1000));
		
		WebDriverWait wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(update));
		update.click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//get the error message 'The maximum quantity allowed for purchase is 500'
	public String getErrorMessageQTY() {
		return errorMessageQTY.getText();
	}
	
	//click links or buttons according to linkName
	public void clickButtonOrLink(String linkName) {
		if(linkName.equalsIgnoreCase("empty cart")) {
			emptyCart.click();//click on 'EMPTY CART' LINK IN THE FOOTER OF LIST OF ALL MOBILES
		}else if(linkName.equalsIgnoreCase("proceed to checkout top")) {
			proceedToCheckOutTop.click();//click on 'PROCEED TO CHECKOUT' link on the top
		}else if(linkName.equalsIgnoreCase("proceed to checkout bottom")) {
			proceedToCheckOutBottom.click();//click on 'PROCEED TO CHECKOUT' link on the bottom
		}
		
	}
	
	//Verify cart is empty
	public String getCartIsEmptyMessage() {
		return emptyCartMessage.getText();
	}
}
