package Test;

import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
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
		System.out.println("set up prop is: "+prop);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
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
		
		//3.In mobile products list,click on 'Add To Compare' for 2 mobiles(Sony Xperia and Iphone)
		//mobilepage.
		String mainWindow=driver.getWindowHandle();
		System.out.println("main window is : "+mainWindow);
		
		//get get mainMobile name1
		String sonyXperiaName=mobilepage.getSonyXperiaName();
		System.out.println("mainMobile1 = "+sonyXperiaName);// text captured - upperCase "SONY XPERIA"
		
		//get mainMobile name2
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String iphoneName=mobilepage.getIphoneName();
		System.out.println("mainMobile2 = "+iphoneName);// text captured - upperCase "IPHONE"
		
		mobilepage.compareTwoPhoneXperiaAndIphone();
		
		//4.Verify the popup window and check that the products are reflected in it
		
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
		Assert.assertEquals(sonyXperiaName, mobilepage.getProduct1NamePopup());
		
		//to check the 2 mobiles selected are the two in the popup - this is to check iphone 
		Assert.assertEquals(iphoneName, mobilepage.getProduct2NamePop());
		
		//5.close the pop up window
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
