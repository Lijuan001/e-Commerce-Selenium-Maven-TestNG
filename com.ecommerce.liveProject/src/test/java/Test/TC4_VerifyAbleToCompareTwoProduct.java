/***
 * Verify that you are able to compare to compare two product
 * 
 * Test Steps:
 * 1. Goto http://live.guru99.com/
 * 2.Click on 'MOBILE' menu
 * 3.In the list of all mobile, click on 'Add To Compare' for two mobiles
 * 4.Click on 'COMPARE' button.
 * 5.Verify the pop-up windwo and check that the products are reflected in it.
 * 6.close the popup windows.
 * 
 * 
 * Test Data:
 * Phone 1-Sony Xperia, Phone 2-IPHONE
 * 
 * 
 * 
 * Expected outcomes:
 * 1.A popup window opens with heading as 'COMPARE PRODUCTS' and the selected products are present in it.
 * 2.popup window is closed
 */


package Test;

import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.Index;
import Pages.MobilePage;
import Pages.ShoppingCart;
import Pages.SonyXperiaDetailPage;

public class TC4_VerifyAbleToCompareTwoProduct  extends BaseTest{
	@BeforeMethod
	//1.open browser and Goto http://live.guru99.com
	public void setUp() {
		initBrowser();
		driver.get(prop.getProperty("baseURL"));
		
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	Index index;
	MobilePage mobilepage;
	SonyXperiaDetailPage sonyXperiaDetail;
	ShoppingCart shoppingCart;
	
	@Parameters({"mobile1","mobile2"})
	@Test
	public void VerifyCostOfProductInListPageAndDetailsPageEqual(String mobile1,String mobile2) throws InterruptedException {
		
		index=new Index();
		
		//verify heading of the page
		String expectedHeading="THIS IS DEMO SITE";
		System.out.println("index page heading is: "+index.getHeading());
		Assert.assertTrue(index.getHeading().contains(expectedHeading));
		
		Thread.sleep(2000);
		
		//2.Click On 'MOBILE' menu
		index.goToMobilePage();
		
		mobilepage=new MobilePage();
		//verify Title of the page
		String actual=mobilepage.getTitle();
		String expectedMobilePageTitle="Mobile";
		
		System.out.println("mobile page heading is: "+actual);
		Assert.assertEquals(actual, expectedMobilePageTitle);
		
		//3.In mobile products list,click on 'Add To Compare' for 2 mobiles(Sony Xperia and Iphone)
		//mobilepage.
		String mainWindow=driver.getWindowHandle();
		System.out.println("main window is : "+mainWindow);
		
		//get mainMobile name1
		String phone1=mobilepage.getPhoneName(mobile1);
		System.out.println("mainMobile1 = "+phone1);// text captured - upperCase "SONY XPERIA"
		
		//get mainMobile name2
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String phone2=mobilepage.getPhoneName(mobile2);
		System.out.println("mainMobile2 = "+phone2);// text captured - upperCase "IPHONE"
		
		//4.Click 'Add to Compare' for phone1 and phone2 AND Click on 'COMPARE' button
		mobilepage.compareTwoPhoneXperiaAndIphone();
		
		//5.Verify the popup window and check that the products are reflected in it
		Set<String> windows=driver.getWindowHandles();
		
		for (String window : windows) {
			if(!window.contains(mainWindow)) {
				driver.switchTo().window(window);
				System.out.println("pop up window is : "+window);
				
				System.out.println("popup title is: "+driver.getTitle());
				
			}
		}
		
		// to check the popup heading/title
		Assert.assertEquals(mobilepage.getHeadingPopup(), "COMPARE PRODUCTS");
		
		//to check the 2 mobiles selected are the two in the popup - this is to check the sony xperia 
		Assert.assertEquals(phone1, mobilepage.getProduct1NamePopup());
		
		//to check the 2 mobiles selected are the two in the popup - this is to check iphone 
		Assert.assertEquals(phone2, mobilepage.getProduct2NamePop());
		
		Thread.sleep(2000);
		
		//6.close the pop up window
		mobilepage.closeCompareProductsPopup();
		
		//switch to main window
		for (String window : windows) {
				
			if(window.contains(mainWindow)) {
				driver.switchTo().window(window);
				System.out.println("redirect to window : "+window);
				
				System.out.println("redirect to window title is:  "+driver.getTitle());
				
			}
		}
	}
}
