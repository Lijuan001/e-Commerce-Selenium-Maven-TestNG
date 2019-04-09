
/****************************************
 * 
Verify you are able to change or reorder previously added product

Test Data: QTY=10

Test Steps:
1. Go to http://live.guru99.com/
2. Click on my account link
3. Login in application using previously created credential
4. Click on 'REORDER' link , change QTY & click Update
5. Verify Grand Total is changed
6. Complete Billing & Shipping Information
7. Verify order is generated and note the order number

Expected outcomes:
1) Grand Total is Changed
2) Order number is generated
 */


package Test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.CheckoutConfirmPage;
import Pages.CheckoutPage;
import Pages.CustomerHomePage;
import Pages.Index;
import Pages.LoginOrRegisterPage;
import Pages.ShoppingCart;

public class TC8_VerifyBeAbleToChangeOrReorderPreviouslyAddedProduct extends BaseTest{

	@BeforeMethod
	public void setup() {
		initBrowser();
		//1.Go to http://live.guru99.com/
		driver.get(prop.getProperty("baseURL"));
		
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
	
	Index index;
	LoginOrRegisterPage loginOrRegisterPage;
	CustomerHomePage customerHomePage;
	ShoppingCart shoppingCart;
	CheckoutPage checkoutPage;
	CheckoutConfirmPage checkoutConfirmPage;
	
	@Test
	@Parameters({"emailAddress","password","qty","country","state","zip","address","city","telephone"})
	public void verifyBeAbleToChangeOrReorderPreviouslyAddedProduct(String emailAddress, String password,int qty,
			String country,String  state,String  zip, String address,String  city, String telephone) throws InterruptedException {
index=new Index();
		
		//2.Click on 'My Account' link
		index.clickMyAccount("my account footer");
		
		//3.Login in application using previously create credential
		loginOrRegisterPage=new LoginOrRegisterPage();
		loginOrRegisterPage.loginForm(emailAddress, password);
		
		
		
		//4.Click on 'REORDER',change QTY & Click update
		customerHomePage=new CustomerHomePage();
		Thread.sleep(2000);
		customerHomePage.clickReorder();
		System.out.println("my orders page.........");
		Thread.sleep(2000);
		shoppingCart=new ShoppingCart();
		
		//4.1 Get orginal grand Total
		String orginalGrandTotal=shoppingCart.getGrandTotal();
		System.out.println("original grand total is: "+orginalGrandTotal);
		
		shoppingCart.changeQTYAndUpdate(qty);//quality is 10
		
		//5.Verify Grand Total is changed
		String updatedGrandTotal=shoppingCart.getGrandTotal();
		System.out.println("updated grand total is: "+updatedGrandTotal);
		
		Assert.assertNotEquals(updatedGrandTotal, orginalGrandTotal);
		
		//6.Complete Billing & Shipping Information
		//6.1 click 'PROCEED TO CHECKOUT' button
		shoppingCart.clickButtonOrLink("proceed to checkout bottom");
		
		//6.2 choose 'New Address' from Billing Information dropdown
		checkoutPage=new CheckoutPage();
		checkoutPage.chooseBillingInfo();
		Thread.sleep(2000);
		
		//6.3 fill new billing address form
		checkoutPage.fillBillingInformation(country, state, zip, address, city, telephone);
		Thread.sleep(2000);
		
		//6.4 click 'Ship to different address' radio button
		checkoutPage.clickShippingAddressMethod("ship to different address");
		
		//6.5 click 'CONTINUE' button
		checkoutPage.clickContinueButton("Billing Address Continue");
		Thread.sleep(2000);
		
		//6.5 select 'Use Billing Address' rado button in shipping information part
		checkoutPage.clickUseBillingAddress();
		Thread.sleep(2000);
		
		//6.6.In shipping address part,click continue
		checkoutPage.clickContinueButton("Shipping address Continue");
		Thread.sleep(2000);
		
		//6.7.In shipping address part,click continue
		checkoutPage.clickContinueButton("Shipping method Continue");
		Thread.sleep(2000);
		
		//6.8.In payment information select 'check/Money Order' radio button
		checkoutPage.clickCheckMoneyOrder();
		Thread.sleep(2000);	
		
		checkoutPage.clickContinueButton("payment method continue");
		Thread.sleep(2000);
		
		//6.9.In ORDER REVIEW part, check shipping cost is added to total product cost
		boolean checkShippingCostAddedToGrand=checkoutPage.checkShippingCostAddedToGrandTotal();
		Assert.assertTrue(checkShippingCostAddedToGrand);
				
		//6.9.click 'place order' button
		checkoutPage.clickPlaceOrder();
		
				
		//7.verify order is generated. note order number
		checkoutConfirmPage=new CheckoutConfirmPage();
		String orderNumber=checkoutConfirmPage.getOrderNumber();
		System.out.println("the order number is: "+orderNumber);
				
		
		
	}
}
