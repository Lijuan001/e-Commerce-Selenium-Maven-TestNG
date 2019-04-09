package Test;



import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.CheckoutConfirmPage;
import Pages.CheckoutPage;
import Pages.CustomerHomePage;
import Pages.Index;
import Pages.LoginOrRegisterPage;
import Pages.MyWishlistPage;
import Pages.ShoppingCart;

public class TC6_VerifyUserCanPurchaseProductUsingRegisteredEmailId  extends BaseTest{
	
	
	
	
	@DataProvider(name="loginData")
	public Object[][] getLoginData(){
//		String fileLocation="";
//		
//		Object[][] arr=new Object[1][];
//		
//		File file=new File(fileLocation);//create a new file using the file location
//		FileInputStream fis=new FileInputStream();
		
		
		return new Object[][] {
			{"United States","New York"
				,"542896","ABC","New York","12345678"}
		};
//		{"shenlijuan004@gmail.com","Today@123","United States","New York"
//			,"542896","ABC","New York","12345678"}
//	};
	}
	
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
		driver.quit();
	}
	
	@Test(dataProvider="loginData")
	
	public void VerifyUserCanPurchaseProductUsingRegisteredEmailId(String emailAddress,
			String password,String country, String state, String zip,String   
			address,String city,String  telephone,String countryForEstimate,String  stateForEstimate,String  zipcodeForEstimate) throws Exception {
		index=new Index();
		
		//verify heading of the page
		String expectedHeading="THIS IS DEMO SITE";
		System.out.println("index page heading is: "+index.getHeading());
		Assert.assertTrue(index.getHeading().contains(expectedHeading));
		
		//2.click on my account link
		index.clickMyAccount("my account footer");
		
		//3.Login in application using previously created credential
		loginOrRegisterPage=new LoginOrRegisterPage();
		loginOrRegisterPage.loginForm(emailAddress, password);
		
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
		shoppingCart.enterInformationForExtimateShippingCost(countryForEstimate, stateForEstimate, zipcodeForEstimate);
		
		//7.Click Estimate
		shoppingCart.clickButtonOrLink("estimate");
		
		//8.Verify shipping cost generated
		String sFlatRate="Flat Rate";
		String flatRate=shoppingCart.getText("flat rate");
		System.out.println("sFlatRate="+sFlatRate);
		System.out.println("flatRate="+flatRate);
		Assert.assertEquals(flatRate, sFlatRate);
		
		String sFlatRatePrice="Fixed - $10.00";
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
