package Test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.Index;
import Pages.MobilePage;
import Pages.ShoppingCart;
import Pages.SonyXperiaDetailPage;

public class TC3_VerifyCannotAddMoreProductInCartThanAvailableInStore extends BaseTest{

	@BeforeMethod
	//1.open browser and Goto http://live.guru99.com
	public void setUp() {
		initBrowser();
		driver.get(prop.getProperty("baseURL"));
		System.out.println("set up prop is: "+prop);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
	Index index;
	MobilePage mobilepage;
	SonyXperiaDetailPage sonyXperiaDetail;
	ShoppingCart shoppingCart;
	
	@Test
	public void VerifyCostOfProductInListPageAndDetailsPageEqual() {
		
index=new Index();
		
		//verify heading of the page
		String expectedHeading="THIS IS DEMO SITE";
		System.out.println("index page heading is: "+index.getHeading());
		Assert.assertTrue(index.getHeading().contains(expectedHeading));
		
		//2.Click On 'MOBILE' menu
		index.goToMobilePage();
		
		mobilepage=new MobilePage();
		//verify Title of the page
		String actual=mobilepage.getTitle();
		String expectedMobilePageTitle="Mobile";
		
		System.out.println("mobile page heading is: "+actual);
		Assert.assertEquals(actual, expectedMobilePageTitle);
		
		//3.In the list of all mobile,click on "ADD TO CART" for Sony Xperia mobile
		mobilepage.addToCartSonyXperia();
		
		//4.Change 'QTY' value to 1000 and click 'UPDATE' button
		shoppingCart=new ShoppingCart();
		shoppingCart.changeQTYAndUpdate(1000);
		
		//5.Verify the error message
		Assert.assertTrue(shoppingCart.getErrorMessageQTY().contains("The maximum quantity allowed for purchase is 500"));
		
		//6.Then click on 'EMPTY CART' LINK IN THE FOOTER OF LIST OF ALL MOBILES
		shoppingCart.clickButtonOrLink("empty cart");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//7.Verify cart is empty
		Assert.assertEquals(shoppingCart.getCartIsEmptyMessage(), "SHOPPING CART IS EMPTY");
}
}
