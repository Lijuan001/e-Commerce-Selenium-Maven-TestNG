
/***
 * Verify that you can create account in E-commerce site and can share wishlist to other people using email
 * 
 * Test Steps:
 * 1. Goto http://live.guru99.com/
 * 2.Click on my acccount link.
 * 3.Click Create Account link and fill New User Information.(emailAddress is random)
 * 4.Click Register
 * 5.Verify Registration is done.
 * 6.Go to TV menu.
 * 7.Add product in your wish list.
 * 8.Click 'SHARE WISHLIST'
 * 9.In next page enter Email and a message and click SHARE WISHLIST
 * 10.Check wishlist is shared
 * 
 * Test Data:
 * product=LG LCD
 * create account data eg:firstName,lastName,email,password,confirmPassword,emailAddressShareWishlist,messageShareWishlist
 * 
 * 
 * Expected outcomes:
 * 1.Account Registration done
 * 2.wishlist shared successfully
 */


package Test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.CreateNewAccountPage;
import Pages.CustomerHomePage;
import Pages.Index;
import Pages.LoginOrRegisterPage;
import Pages.MyWishlistPage;
import Pages.WishlistFormPage;
import Util.CommonMethods;
import Util.Constants;


public class TC5_VerifyCanCreateAccountAndShareWishlistToOtherPeopleUsingEmail extends BaseTest{
	
	
	@BeforeMethod
	//1.open browser and Goto http://live.guru99.com
	public void setUp() {
		initBrowser();
		driver.get(prop.getProperty("baseURL"));
		//System.out.println("set up prop is: "+prop);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	Index index;
	LoginOrRegisterPage loginOrRegisterPage;
	CreateNewAccountPage createNewAccountPage;
	CustomerHomePage customerHomePage;
	MyWishlistPage myWishlistPage;
	WishlistFormPage wishlistFormPage;
	
	@DataProvider(name="createNewAccountData")
	public Object[][] newAccountData(){
		return new Object[][] {
			//firstName,lastName,password,confirmPassword,emailAddressShareWishlist,messageShareWishlist
			{"jane","shen","Today@123","Today@123","shenlijuan001@gmail.com","this TV is very good."}
			
		};
	}
	
	
	@Test(dataProvider="createNewAccountData")
	public void VerifyCostOfProductInListPageAndDetailsPageEqual(String firstName,
			String lastName,String password,String confirmPassword,String emailAddressShareWishlist, String messageShareWishlist) throws InterruptedException {
		//get random sign up email address
		Constants.emailAddress=CommonMethods.generateEmailAddress();
		
		index=new Index();
		
		//verify heading of the page
		String expectedHeading="THIS IS DEMO SITE";
		System.out.println("index page heading is: "+index.getHeading());
		Assert.assertTrue(index.getHeading().contains(expectedHeading));
		
	
		//2.Click on 'My Account' link in the bottom of the page
		 index.clickMyAccount("my account footer");
		 
		 Thread.sleep(2000);
		 
		 loginOrRegisterPage=new LoginOrRegisterPage();
		 //Verify 'login or register' page title
		 String expectedLoginTitle="Customer Login";
		 System.out.println("loginOrRegister page title is: "+loginOrRegisterPage.getTitle());
		 Assert.assertEquals(loginOrRegisterPage.getTitle(), expectedLoginTitle);
		 
		 //3.click 'CREATE AN ACCOUNT' button 
		 loginOrRegisterPage.clickCreateAnAccount();
		 
		 createNewAccountPage=new CreateNewAccountPage();
		 //Verify 'create new customer' page title
		 String expectedCreateNewAccountPage="Create New Customer Account";
		 System.out.println("createNewAccountPage title is: "+createNewAccountPage.getTitle());
		 Assert.assertEquals(createNewAccountPage.getTitle(), expectedCreateNewAccountPage);
		 
		 //3.2 fill new user information and click register
		 createNewAccountPage.fillNewUserInformation(firstName, lastName, Constants.emailAddress, password, confirmPassword);
		 
		 Thread.sleep(2000);
		 
		 customerHomePage=new CustomerHomePage();
		 //5.Verify Registration is done
		 String expectedWelcome="Hello, "+firstName+" "+lastName+"!";
		 System.out.println("customerHomePage welcome text is: "+customerHomePage.getWelcomeText());
		 Assert.assertEquals(customerHomePage.getWelcomeText(), expectedWelcome);
		 
		 
		 //6.Go to TV menu
		 customerHomePage.clickTVLink();
		 
		 //7.Add product in your wish list,product=LG LCD
		 customerHomePage.addProductToWishList("LG LCD");
		 
		 Thread.sleep(2000);
		 
		 //8.Click SHARE WISHLIST
		 myWishlistPage=new MyWishlistPage();
		 myWishlistPage.clickButtonOrLink("share wishlist");
		 
		 //9.In next page enter email and a message and click SHARE WISHLIST
		 wishlistFormPage=new WishlistFormPage();
		 wishlistFormPage.clickShareWashlistButton(emailAddressShareWishlist, messageShareWishlist);
		 
		 Thread.sleep(2000);
		 
		 
		 //10.check wishlist is shared
		 String expectedShareWishlistSuccessMessag="Your Wishlist has been shared.";
		 System.out.println("ShareWishlistSuccessMessag: "+myWishlistPage.getShareWishlistSuccessMessage());
		 Assert.assertEquals(myWishlistPage.getShareWishlistSuccessMessage(), expectedShareWishlistSuccessMessag);
		 
	}
		
}
