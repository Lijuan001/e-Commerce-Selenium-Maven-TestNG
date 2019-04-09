package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseTest;

public class CheckoutPage extends BaseTest{
	
	@FindBy(id="shipping-address-select")
	WebElement selectShippingAddressDropdown;

	@FindBy(id="billing-address-select")
	WebElement selectBillingAddressDropdown;
	
	@FindBy(css="input[title='Street Address']")
	WebElement addressEle;
	
	@FindBy(css="input[title='City']")
	WebElement cityEle;
	
	@FindBy(css="select[title='State/Province']")
	WebElement stateEle;

	@FindBy(css="input[title='Zip']")
	WebElement zipEle;
	
	@FindBy(css="select[title='Country']")
	WebElement countryEle;
	
	@FindBy(css="input[title='Telephone']")
	WebElement telephoneEle;
	
	@FindBy(xpath="//*[@id='billing-buttons-container']/button")
	WebElement continueButtonBillingAddress;
	
	
	@FindBy(xpath="//*[@id='shipping-buttons-container']/button")
	WebElement continueButtonShippingAddress;
	
	@FindBy(xpath="//*[@id='shipping-method-buttons-container']/button")
	WebElement continueButtonShippingMethod;
	
	@FindBy(xpath="//*[@id='payment-buttons-container']/button")
	WebElement continueButtonPaymentMethod;
	
	@FindBy(xpath="//*[@title='Ship to this address'")
	WebElement shipToThisAddressRadio;
	
	@FindBy(xpath="//*[@title='Ship to different address']")
	WebElement shipToDifferentAddressRadio;
	
	@FindBy(id="p_method_checkmo")
	WebElement clickCheckMoneyOrder;
	
	@FindBy(xpath="//button[@title='Place Order']")
	WebElement clickPlaceOrderButton;
	
	
	@FindBy(xpath="//table[@id='checkout-review-table']/tfoot/tr[3]/td[2]")
	WebElement grandTotal;
	
	@FindBy(xpath="//table[@id='checkout-review-table']/tfoot/tr[1]/td[2]")
	WebElement subTotalLoc;
	
	
	@FindBy(xpath="//table[@id='checkout-review-table']/tfoot/tr[2]/td[2]")
	WebElement shippingLoc;
	
	@FindBy(xpath="//input[@id='shipping:same_as_billing']")
	WebElement useBillingAddress;
	
	public CheckoutPage() {
		PageFactory.initElements(driver, this);
	}
	
	//get page title
	public String getTitle() {
		return driver.getTitle();
	}
	
	//select 'New Address'from dropdown in billing information part
	public void chooseBillingInfo() {
		Select billingInfo=new Select(selectBillingAddressDropdown);
		billingInfo.selectByVisibleText("New Address");
		}
	
	
	
	//fill billing information form
	public void fillBillingInformation(String country,String state,String zip,
			String address,String city,String telephone) {
		addressEle.clear();
		addressEle.sendKeys(address);
		
		cityEle.clear();
		cityEle.sendKeys(city);
		
		//select state from state dropdown eg:Georgia
		Select dropdown=new Select(stateEle);
		dropdown.selectByVisibleText(state);
		
		zipEle.clear();
		zipEle.sendKeys(zip);
		
		Select countryDropdown=new Select(countryEle);//select country from country dropdown,eg:United States
		countryDropdown.selectByVisibleText(country);
		
		telephoneEle.clear();
		telephoneEle.sendKeys(telephone);
		
		
	}
	
	
	
	//choose different shipping address from radio button in billing information part 
		public void clickShippingAddressMethod(String shipAddressMethod) {
			if(shipAddressMethod.equalsIgnoreCase("ship to this address")) {
			shipToThisAddressRadio.click();//click 'ship to this address' radio button
			}else if(shipAddressMethod.equalsIgnoreCase("ship to different address")) {
				shipToDifferentAddressRadio.click();
			}
		}
	
	//choose shipping method from dropdown in shipping information part
		public void selectShippingAddress() {
			Select shippingSelect=new Select(selectShippingAddressDropdown);
			shippingSelect.selectByVisibleText("New Address");
		}
		
		//click 'Use Billing Address' radio button
		public void clickUseBillingAddress() {
			useBillingAddress.click();
		}
		
		
//	//fill the shipping address form
//		public void fillShippingAddressForm(String country,String state,String zip,
//				String address,String city,String telephone) {
//			shippingAddressEle.clear();
//			shippingAddressEle.sendKeys(address);
//			
//			shippingCityEle.clear();
//			shippingCityEle.sendKeys(city);
//			
//			//select state from state dropdown eg:Georgia
//			Select dropdown=new Select(shippingStateEle);
//			dropdown.selectByVisibleText(shippingStateEle);
//			
//			shippingZipEle.clear();
//			shippingZipEle.sendKeys(zip);
//			
//			Select countryDropdown=new Select(shippingCountryEle);//select country from country dropdown,eg:United States
//			countryDropdown.selectByVisibleText(country);
//			
//			ShippingElephoneEle.clear();
//			ShippingElephoneEle.sendKeys(telephone);
//			
//		}
		
		
	//click 'Check/Money order' radio button
	public void clickCheckMoneyOrder() {
		clickCheckMoneyOrder.click();
	}
	
	//click "PLACE ORDER" button
	public void clickPlaceOrder() {
		clickPlaceOrderButton.click();
	}
	
	//to process money be removing dollar sign and change money from String to double.  eg: $1,325.00 to 1,325.00
	public  double processMoneyFormat(String money) {

		double value=Double.parseDouble(money.replaceAll("[^\\d.]", ""));
		System.out.println("grand total after changed format is: "+value);
		return value;
	}
	
	
	//check shipping cost is added to grand total
	public boolean checkShippingCostAddedToGrandTotal() {
		
		String subtotal=subTotalLoc.getText();
		System.out.println("sub total is: "+subtotal);// get subtotal
		double subtotalFormatted=processMoneyFormat(subtotal);
		
		String shipping=shippingLoc.getText();
		System.out.println("shipping is: "+shipping);// get shipping
		double shippingFormatted=processMoneyFormat(shipping);
		
		String total=grandTotal.getText();
		System.out.println("grand total is: "+total);// get grand total
		double totalFormatted=processMoneyFormat(total);
		
		if(totalFormatted==(shippingFormatted+subtotalFormatted)) {
			return true;
		}else {
			return false;
		}
	}
	
	//click 'CONTINUE' button from billing address part and  shipping method part
	public void clickContinueButton(String continueButton) {
		if(continueButton.equalsIgnoreCase("Billing Address Continue")) {
			continueButtonBillingAddress.click();
		}else if(continueButton.equalsIgnoreCase("Shipping address Continue")) {
			continueButtonShippingAddress.click();
		}else if(continueButton.equalsIgnoreCase("Shipping method Continue")) {
			continueButtonShippingMethod.click();
		}else if(continueButton.equalsIgnoreCase("payment method continue")) {
			continueButtonPaymentMethod.click();
		}
	}
}
