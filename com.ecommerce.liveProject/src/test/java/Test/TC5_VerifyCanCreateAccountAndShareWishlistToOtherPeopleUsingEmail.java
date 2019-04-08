package Test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.CreateNewAccountPage;
import Pages.CustomerHomePage;
import Pages.Index;
import Pages.LoginOrRegisterPage;
import Pages.MyWishlistPage;
import Pages.WishlistFormPage;

public class TC5_VerifyCanCreateAccountAndShareWishlistToOtherPeopleUsingEmail extends BaseTest{
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
	LoginOrRegisterPage loginOrRegisterPage;
	CreateNewAccountPage createNewAccountPage;
	CustomerHomePage customerHomePage;
	MyWishlistPage myWishlistPage;
	WishlistFormPage wishlistFormPage;
	
	@DataProvider(name="createNewAccountData")
	public Object[][] newAccountData(){
		return new Object[][] {
			//{"jane","shen","shenlijuan004@gmail.com","Today@123","Today@123","shenlijuan001@gmail.com","this TV is very good."}
			{"jane","shen","Today@123","shenlijuan001@gmail.com","this TV is very good."}
		};
	}
	
	
	@Test(dataProvider="createNewAccountData")
	public void VerifyCostOfProductInListPageAndDetailsPageEqual(String firstName,
			String lastName,String password,String confirmPassword,String emailAddressShareWishlist, String messageShareWishlist) throws InterruptedException {
		//get random sign up email address
		String emailAddress=generateEmailAddress();
		
		index=new Index();
		
		//verify heading of the page
		String expectedHeading="THIS IS DEMO SITE";
		System.out.println("index page heading is: "+index.getHeading());
		Assert.assertTrue(index.getHeading().contains(expectedHeading));
		
//		//2.Click On 'ACCOUNT' menu
//		index.clickAccountLink();
//		Thread.sleep(2000);
		
		//3.Click Create Account link and fill New User information except Email ID
		 index.clickMyAccount("my account footer");
		 
		 Thread.sleep(2000);
		 loginOrRegisterPage=new LoginOrRegisterPage();
		 //Verify 'login or register' page title
		 String expectedLoginTitle="Customer Login";
		 System.out.println("loginOrRegister page title is: "+loginOrRegisterPage.getTitle());
		 Assert.assertEquals(loginOrRegisterPage.getTitle(), expectedLoginTitle);
		 
		 //click 'CREATE AN ACCOUNT' button
		 loginOrRegisterPage.clickCreateAnAccount();
		 
		 createNewAccountPage=new CreateNewAccountPage();
		 //Verify 'create new customer' page title
		 String expectedCreateNewAccountPage="Create New Customer Account";
		 System.out.println("createNewAccountPage title is: "+createNewAccountPage.getTitle());
		 Assert.assertEquals(createNewAccountPage.getTitle(), expectedCreateNewAccountPage);
		 
		 createNewAccountPage.fillNewUserInformation(firstName, lastName, emailAddress, password, confirmPassword);
		 
		 customerHomePage=new CustomerHomePage();
		 //5.Verify Registration is done
//		 String expectedcustomerHomePageTitle="My Account";
//		 System.out.println("customerHomePage title is: "+customerHomePage.getTitle());
//		 Assert.assertEquals(customerHomePage.getTitle(), expectedcustomerHomePageTitle);
		 String expectedWelcome="Hello, "+firstName+" "+lastName+"!";
		 System.out.println("customerHomePage welcome text is: "+customerHomePage.getWelcomeText());
		 Assert.assertEquals(customerHomePage.getWelcomeText(), expectedWelcome);
		 
		 
		 //6.Go to TV menu
		 customerHomePage.clickTVLink();
		 
		 //7.Add product in your wish list,product=LG LCD
		 customerHomePage.addProductToWishList("LG LCD");
		 
		 //8.Click SHARE WISHLIST
		 myWishlistPage=new MyWishlistPage();
		 myWishlistPage.clickButtonOrLink("share wishlist");
		 
		 //9.In next page enter email and a message and click SHARE WISHLIST
		 wishlistFormPage=new WishlistFormPage();
		 wishlistFormPage.clickShareWashlistButton(emailAddressShareWishlist, messageShareWishlist);
		 //10.check wishlist is shared
		 String expectedShareWishlistSuccessMessag="Your Wishlist has been shared.";
		 System.out.println("ShareWishlistSuccessMessag: "+myWishlistPage.getShareWishlistSuccessMessage());
		 Assert.assertEquals(myWishlistPage.getShareWishlistSuccessMessage(), expectedShareWishlistSuccessMessag);
		 
	}
		
}
