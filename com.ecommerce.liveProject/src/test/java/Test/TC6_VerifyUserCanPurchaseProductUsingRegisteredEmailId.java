package Test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.BillingInfoFormPage;
import Pages.CustomerHomePage;
import Pages.Index;
import Pages.LoginOrRegisterPage;
import Pages.MyWishlistPage;
import Pages.ShoppingCart;

public class TC6_VerifyUserCanPurchaseProductUsingRegisteredEmailId  extends BaseTest{
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
	
	@DataProvider(name="loginData")
	public Object[][] getLoginData(){
		return new Object[][] {
			{"shenlijuan004@gmail.com","Today@123","United States","New York"
				,"542896","ABC","New York","12345678"}
		};
	}
	
	Index index;
	LoginOrRegisterPage loginOrRegisterPage;
	CustomerHomePage customerHomePage;
	MyWishlistPage myWishlistPage;
	ShoppingCart shoppingCart;
	BillingInfoFormPage billingInfoFormPage;
	
	@Test(dataProvider="loginData")
	
	public void VerifyUserCanPurchaseProductUsingRegisteredEmailId(String loginEmailAddress,
			String loginPassword,String country, String state, String zip,String   
			address,String city,String  telephone) throws InterruptedException {
		index=new Index();
		
		//verify heading of the page
		String expectedHeading="THIS IS DEMO SITE";
		System.out.println("index page heading is: "+index.getHeading());
		Assert.assertTrue(index.getHeading().contains(expectedHeading));
		
		//2.click on my account link
		index.clickMyAccount("my account footer");
		
		//3.Login in application using previously created credential
		loginOrRegisterPage=new LoginOrRegisterPage();
		loginOrRegisterPage.loginForm(loginEmailAddress, loginPassword);
		
		//4.click on 'MY WISHLIST' link
		customerHomePage=new CustomerHomePage();
		customerHomePage.clickSideBar("my wishlist");
		
		//5.In next page,click 'ADD TO CART' link
		myWishlistPage=new MyWishlistPage();
		String expectedMyWishlistPageTitle="My Wishlist";
		System.out.println("MY wishlist page title is: "+myWishlistPage.getTitle());
		Assert.assertEquals(myWishlistPage.getTitle(), expectedMyWishlistPageTitle);
		
		myWishlistPage.clickButtonOrLink("add to cart");
		
		//6.click 'PROCEED TO CHECKOUT', the method value is "proceed to checkout top"
		//or "proceed to checkout bottom"
		shoppingCart=new ShoppingCart();
		shoppingCart.clickButtonOrLink("proceed to checkout top");
		
		//7.enter shipping information
		billingInfoFormPage=new BillingInfoFormPage();
		billingInfoFormPage.fillBillingInformation(country, state, zip, address, city, telephone);
		//8.click estimate
		
		//9.verify shipping cost generated
		
		//10.select shipping cost,update total
		
		//11.verify shipping cost is added to total
		
		//12.click "Proceed to Checkout"
		
		//13.enter billing information
		
		//14.In shipping method,click continue
		
		//15.In payment information select 'check/Money Order' radio button
		
		//16.click 'place order' button
		
		//17.verify order is generated.Note the order number
	}
}
