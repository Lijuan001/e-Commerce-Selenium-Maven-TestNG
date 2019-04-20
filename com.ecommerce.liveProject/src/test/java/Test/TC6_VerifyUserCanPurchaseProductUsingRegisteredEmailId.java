/***
 * Verify user is able to purchase product using registered email id
 * 
 * Test Steps:
 * 1.Go to http://live.guru99.com/
 * 2.Click on my acccount link.
 * 3.Login in application using previously created credential
 * 4.Click on My WishList link
 * 5.In next page,Click 'ADD TO CART' link
 * 6.Enter general shipping country, state/province and zip for the shipping cost estimate
 * 7. Click Estimate 
* 8. Verify Shipping cost generated 
* 9. Select Shipping Cost, Update Total 
* 10. Verify shipping cost is added to total 
* 11. Click "Proceed to Checkout"
* 12a. Enter Billing Information, and click Continue
* 12b. Enter Shipping Information, and click Continue
* 13. In Shipping Method, Click Continue
* 14. In Payment Information select 'Check/Money Order' radio button. Click Continue
* 15. Click 'PLACE ORDER' button 
* 16. Verify Oder is generated. Note the order number
 * 
 * Test Data:
 * 1)User credentials created in test case 05
 * 2)Shipping information Country=United States,State=New York,Zip=542896,Address=ABC,City=New York,Telephone=12345678
 * 
 * 
 * Expected outcomes:
 * 1)Flat Rate Shipping of $5 is generate
 * 2)Shipping cost is added to total product cost
 * 3)Order is placed.order number is generated
 *
 */

package Test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.CheckoutConfirmPage;
import Pages.CheckoutPage;
import Pages.CustomerHomePage;
import Pages.Index;
import Pages.LoginOrRegisterPage;
import Pages.MyWishlistPage;
import Pages.ShoppingCart;
import Util.Constants;

public class TC6_VerifyUserCanPurchaseProductUsingRegisteredEmailId  extends BaseTest{

	
	
	Index index;
	LoginOrRegisterPage loginOrRegisterPage;
	CustomerHomePage customerHomePage;
	MyWishlistPage myWishlistPage;
	ShoppingCart shoppingCart;
	CheckoutPage checkoutPage;
	CheckoutConfirmPage checkoutConfirmPage;
	
	@BeforeMethod
	//1.open browser and Goto http://live.guru99.com
	public void setUp() {
		initBrowser();
		driver.get(prop.getProperty("baseURL"));
		System.out.println("set up prop is: "+prop);
	}
	
	@AfterMethod
	public void tearDown() {
		//driver.quit();
	}
	
	
	@DataProvider(name="loginData")
	public Object[][] getLoginData(){

		return new Object[][] {
			//String password,country, state, zip,address,city,telephone,countryForEstimate,stateForEstimate,zipcodeForEstimate
			{"Today@123","United States","New York","542896","ABC","New York","12345678","United States","New York","542896","Fixed - $5.00"}
		};

	}
	
	@Test(dataProvider="loginData")
	public void VerifyUserCanPurchaseProductUsingRegisteredEmailId(
			String password,String country, String state, String zip,String   
			address,String city,String  telephone,String countryForEstimate,String  stateForEstimate,String  zipcodeForEstimate,String sFlatRatePrice) throws Exception {
		index=new Index();
		
		//verify heading of the page
		String expectedHeading="THIS IS DEMO SITE";
		System.out.println("index page heading is: "+index.getHeading());
		Assert.assertTrue(index.getHeading().contains(expectedHeading));
		
		//2.click on my account link
		index.clickMyAccount("my account footer");
		
		//3.Login in application using previously created credential
		loginOrRegisterPage=new LoginOrRegisterPage();
		loginOrRegisterPage.loginForm(Constants.emailAddress, password);
		
		//loginOrRegisterPage.loginForm("shenlijuan004@gmail.com", password);
		
		//4.click on 'MY WISHLIST' link
		customerHomePage=new CustomerHomePage();
		customerHomePage.clickSideBar("my wishlist");
		
		//5.In next page,click 'ADD TO CART' link
		myWishlistPage=new MyWishlistPage();
		String expectedMyWishlistPageTitle="My Wishlist";
		System.out.println("MY wishlist page title is: "+myWishlistPage.getTitle());
		Assert.assertEquals(myWishlistPage.getTitle(), expectedMyWishlistPageTitle);
		
		myWishlistPage.clickButtonOrLink("add to cart");
		
		
		//6.Enter general shipping country,state/province and zip for the shipping 
		//cost estimate
		shoppingCart=new ShoppingCart();
		System.out.println("countryForEstimate is:"+countryForEstimate);
		shoppingCart.enterInformationForExtimateShippingCost(countryForEstimate, stateForEstimate, zipcodeForEstimate);
		
		//7.Click Estimate
		shoppingCart.clickButtonOrLink("estimate");
		
		//8.Verify shipping cost generated
		String sFlatRate="Flat Rate";
		String flatRate=shoppingCart.getText("flat rate");
		System.out.println("sFlatRate="+sFlatRate);
		System.out.println("flatRate="+flatRate);
		Assert.assertEquals(flatRate, sFlatRate);
		
		
		String flatRatePrice=shoppingCart.getText("flat rate price");
		System.out.println("sFlatRatePrice="+sFlatRatePrice);
		System.out.println("flatRatePrice="+flatRatePrice);
		Assert.assertEquals(flatRatePrice, sFlatRatePrice);
		
		
		
		//9.Select Shipping Cost(already selected as default),Updata total
		shoppingCart.clickButtonOrLink("select shipping cost radio button");
		
		shoppingCart.clickButtonOrLink("update total");
		
		
		
		//10.click 'PROCEED TO CHECKOUT', the method value is "proceed to checkout top"
		//or "proceed to checkout bottom"
		
		shoppingCart.clickButtonOrLink("proceed to checkout top");
		
		//11.enter shipping information
		checkoutPage=new CheckoutPage();
		checkoutPage.fillBillingInformation(country, state, zip, address, city, telephone);
		
		//12.In Billing Address, click 'Ship to this address' AND click 'CONTINUE'button
		checkoutPage.clickShippingAddressMethod("ship to this address");
		checkoutPage.clickContinueButton("Billing Address Continue");
		
		
		
		//13.In shipping method,click continue
		checkoutPage.clickContinueButton("Shipping method Continue");
		
		//14.In payment information select 'check/Money Order' radio button
		checkoutPage.clickCheckMoneyOrder();
		
		//15.In ORDER REVIEW part, check shipping cost is added to total product cost
		boolean checkShippingCostAddedToGrand=checkoutPage.checkShippingCostAddedToGrandTotal();
		Assert.assertTrue(checkShippingCostAddedToGrand);
		
		//15.click 'place order' button
		checkoutPage.clickContinueButton("payment method continue");
		
		//16.verify order is generated.Note the order number
		checkoutPage.clickPlaceOrder();
		
		//17.verify order is generated. note order number
		checkoutConfirmPage=new CheckoutConfirmPage();
		String orderNumber=checkoutConfirmPage.getOrderNumber();
		System.out.println("the order number is: "+orderNumber);
		
	
		
	}
}
