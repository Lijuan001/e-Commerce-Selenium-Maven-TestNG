package Test;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.Index;
import Pages.MobilePage;

public class TC1_VerifyItemInMobileListPageCanBeSortedByName extends BaseTest{
	
	

	@BeforeMethod
	//open browser and Goto http://live.guru99.com
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
	
	@Test
	public void VerifyItemInMobileListPageCanBeSortedByName() {
		index=new Index();
		
		//verify heading of the page
		String expectedHeading="THIS IS DEMO SITE";
		System.out.println("index page heading is: "+index.getHeading());
		Assert.assertTrue(index.getHeading().contains(expectedHeading));
		
		//Click On 'MOBILE' menu
		index.goToMobilePage();
		
		mobilepage=new MobilePage();
		//verify Title of the page
		String actual=mobilepage.getTitle();
		String expectedMobilePageTitle="Mobile";
		System.out.println("mobile page heading is: "+actual);
		Assert.assertEquals(actual, expectedMobilePageTitle);
		
		//Select 'SORT BY' dropdown as'name'
		mobilepage.selectSortByName();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//verify all products are sorted by name
		List<WebElement> allProducts=mobilepage.AllProductsSortByName();
		for (int i=0;i<allProducts.size()-1;i++) {
			String productName=allProducts.get(i).getText();
			String nextProductName=allProducts.get(i+1).getText();
			System.out.println("product name is : "+productName);
			Assert.assertTrue(productName.compareToIgnoreCase(nextProductName)<0);
		}
		
		//take a screenshot of mobilepage
		getScreenShot();
	}
}
