package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
	
	@FindBy(css="select[name='country_id']")
	WebElement countryDropDown;
	
	@FindBy(css="select[name='region_id']")
	WebElement stateDropDown;
	
	@FindBy(css="input[name='estimate_postcode']")
	WebElement zipcodeEle;
	
	@FindBy(css="button[title='Estimate']")
	WebElement estimate;
	
	@FindBy(xpath="//form[@id='co-shipping-method-form']/dl/dt")
	WebElement shippingMehod;
	
	@FindBy(css="input[name='estimate_method']+label")
	WebElement shippingPrice;
	
	@FindBy(css="input[name='estimate_method']")
	WebElement selectShippingCostRadioButton;
	
	@FindBy(css="button[title='Update Total']")
	WebElement updateTotal;
	
	public ShoppingCart() {
		PageFactory.initElements(driver, this);
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	//get shipping method, this should be 'Flat Rate'
	public String getText(String elementName) throws Exception {
		if(elementName.equalsIgnoreCase("flat rate")) {
			return shippingMehod.getText();//get shipping method, this should be 'Flat Rate'
		}else if(elementName.equalsIgnoreCase("flat rate price")) 
		{//get shipping price
			return shippingPrice.getText();
		}else {
			throw new Exception("please enter correct element name");
		}
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
		}else if(linkName.equalsIgnoreCase("estimate")) {
			estimate.click();//click 'ESTIMATE' button
		}else if(linkName.equalsIgnoreCase("select shipping cost radio button")) {
			selectShippingCostRadioButton.click();//select shipping cost radio button
		}else if(linkName.equalsIgnoreCase("update total")) {
			updateTotal.click();
		}
		
	}
	
	//Verify cart is empty
	public String getCartIsEmptyMessage() {
		return emptyCartMessage.getText();
	}
	
	//Enter general shipping country,state/province and zip for the shipping 
	//cost estimate
	public void enterInformationForExtimateShippingCost(String country,String state,String zipcode) {
		Select select=new Select(countryDropDown);
		select.selectByVisibleText(country);
		
		Select select2=new Select(stateDropDown);
		select2.selectByVisibleText(country);
		
		zipcodeEle.sendKeys(zipcode);
	}
}
