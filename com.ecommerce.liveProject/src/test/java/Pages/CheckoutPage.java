package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Base.BaseTest;

public class CheckoutPage extends BaseTest{

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
	
	
	@FindBy(xpath="//*[@id='shipping-method-buttons-container']/button")
	WebElement continueButtonShippingMethod;
	
	@FindBy(xpath="//*[@id='payment-buttons-container']/button")
	WebElement continueButtonPaymentMethod;
	
	@FindBy(xpath="//*[@name='billing[use_for_shipping]'")
	WebElement shipToThisAddressRadio;
	
	@FindBy(xpath="#p_method_checkmo")
	WebElement clickCheckMoneyOrder;
	
	@FindBy(xpath="//buton[@title='Place Order']")
	WebElement clickPlaceOrderButton;
	
	
	@FindBy(xpath="//table[@id='checkout-review-table']/tfoot/tr[3]/td[2]")
	WebElement grandTotal;
	
	@FindBy(xpath="//table[@id='checkout-review-table']/tfoot/tr[1]/td[2]")
	WebElement subTotalLoc;
	
	
	@FindBy(xpath="//table[@id='checkout-review-table']/tfoot/tr[2]/td[2]")
	WebElement shippingLoc;
	
	public CheckoutPage() {
		PageFactory.initElements(driver, this);
	}
	
	//get page title
	public String getTitle() {
		return driver.getTitle();
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
	
	//click 'ship to this address' radio button
	public void clickShippingToThisAddress() {
		shipToThisAddressRadio.click();
	}
	
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
		
		money=money.substring(1);
		double value=Double.parseDouble(money);
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
		}else if(continueButton.equalsIgnoreCase("Shipping method Continue")) {
			continueButtonShippingMethod.click();
		}else if(continueButton.equalsIgnoreCase("payment method continue")) {
			continueButtonPaymentMethod.click();
		}
	}
}
