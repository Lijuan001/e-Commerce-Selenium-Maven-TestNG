/***
 * Verify Item in Mobile List Page can be sorted by 'Name'
 * 
 * Test Steps:
 * 1. Goto http://live.guru99.com/
 * 2.Verify Heading Message of the page
 * 3.Click on 'MOBILE' menu
 * 4.Verify Title of the page
 * 5.In the list of all mobile, select 'SORT BY' dropdown as 'name'
 * 6.Verify all products are sorted by name
 * 
 * 
 * Expected outcomes:
 * 1. Text 'THIS IS DEMO SITE' shown in home page
 * 2.Title 'MOBILE' is shown on mobile list page.
 * 3.All 3 products sorted by name
 */



package Test;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.Index;
import Pages.MobilePage;
import Util.CommonMethods;

public class TC1_VerifyItemInMobileListPageCanBeSortedByName extends BaseTest{
	
	

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
	
	@Test
	public void VerifyItemInMobileListPageCanBeSortedByName() {
		index=new Index();
		
		//2.verify heading of the page
		String expectedHeading="THIS IS DEMO SITE";
		System.out.println("index page heading is: "+index.getHeading());
		Assert.assertTrue(index.getHeading().contains(expectedHeading));
		
		//3.Click On 'MOBILE' menu
		index.goToMobilePage();
		
		mobilepage=new MobilePage();
		//4.verify Title of the page
		String actual=mobilepage.getTitle();
		String expectedMobilePageTitle="Mobile";
		System.out.println("mobile page heading is: "+actual);
		Assert.assertEquals(actual, expectedMobilePageTitle);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//5.Select 'SORT BY' dropdown as'name'
		mobilepage.selectSortByName();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//6.verify all products are sorted by name
		List<WebElement> allProducts=mobilepage.AllProductsSortByName();
		for (int i=0;i<allProducts.size()-1;i++) {
			String productName=allProducts.get(i).getText();
			String nextProductName=allProducts.get(i+1).getText();
			System.out.println("product name is : "+productName);
			Assert.assertTrue(productName.compareToIgnoreCase(nextProductName)<0);
		}
		
		//take a screenshot of mobilepage
		CommonMethods.getScreenShot();
	}
}
