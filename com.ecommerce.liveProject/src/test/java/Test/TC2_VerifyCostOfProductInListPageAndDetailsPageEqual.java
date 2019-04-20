
/***
 * Verify that cost of product in list page and details page are equal
 * 
 * Test Steps:
 * 1. Goto http://live.guru99.com/
 * 2.Click on 'MOBILE' menu
 * 3.In the list of all mobile, read the cost of Sony Xperia mobile.Note this value
 * 4.Click on Sony Xperia mobile
 * 5.Read the cost of Sony Xperia mobile from detail page.
 * 6.Compare value in step 3 & step5
 * 
 * 
 * Expected outcomes:
 * 1. Product Value is list and details page should be equal($100)
 */





package Test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.Index;
import Pages.MobilePage;
import Pages.SonyXperiaDetailPage;

public class TC2_VerifyCostOfProductInListPageAndDetailsPageEqual extends BaseTest{

	
	@BeforeMethod
	//1.open browser and Goto http://live.guru99.com
	public void setUp() {
		initBrowser();
		driver.get(prop.getProperty("baseURL"));
		//System.out.println("set up prop is: "+prop);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
	Index index;
	MobilePage mobilepage;
	SonyXperiaDetailPage sonyXperiaDetail;
	
	@Test
	public void VerifyCostOfProductInListPageAndDetailsPageEqual() {
		
		index=new Index();
		
		//verify heading of the page
		String expectedHeading="THIS IS DEMO SITE";
		System.out.println("index page heading is: "+index.getHeading());
		Assert.assertTrue(index.getHeading().contains(expectedHeading));
		
		//2.Click On 'MOBILE' menu
		index.goToMobilePage();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mobilepage=new MobilePage();
		//verify Title of the page
		String actual=mobilepage.getTitle();
		String expectedMobilePageTitle="Mobile";
		
		System.out.println("mobile page heading is: "+actual);
		Assert.assertEquals(actual, expectedMobilePageTitle);
		
		//3.In the list of all mobile,read the cost of SONY Xperia mobile
		String sonyXperiaPriceListPage=mobilepage.getCostOfSonyXperiaMobile();
		System.out.println("sonyXperiaPriceListPage"+sonyXperiaPriceListPage);
		
		//4.Click on Sony Xperia mobile
		mobilepage.goToSonyXperiaDetailPage();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//5.Read the Sony Xperia mobile from detail page
		sonyXperiaDetail=new SonyXperiaDetailPage();
		String sonyXperiaPriceFromDetailPage=sonyXperiaDetail.getSonyXperiaPrice();
		System.out.println("sonyXperiaPriceFromDetailPage: "+sonyXperiaPriceFromDetailPage);
		
		//6.compare value in Step 3 & Step 5
		Assert.assertEquals(sonyXperiaPriceListPage, sonyXperiaPriceFromDetailPage);
	}
}
