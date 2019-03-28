package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.BaseTest;

public class CreateNewAccountPage extends BaseTest{
	 
			@FindBy(css="input[name='firstname']")
			WebElement firstNameEle;
			
			@FindBy(css="input[name='lastname']")
			WebElement lastNameEle;
			
			@FindBy(css="input[name='email']")
			WebElement emailAddressEle;
			
			@FindBy(css="input[name='password']")
			WebElement passwordEle;
			
			@FindBy(css="input[name='confirmation']")
			WebElement confirmPasswordEle;

			@FindBy(css="button[title='Register']")
			WebElement registerEle;

			
			public CreateNewAccountPage() {
				PageFactory.initElements(driver, this);
			}
			
			//get page title
			public String getTitle() {
				return driver.getTitle();
			}
			
			
			//fill New User information
			public void fillNewUserInformation(String firstName,
					String lastName,String emailAddress,String password,String confirmPassword) {
				firstNameEle.clear();
				firstNameEle.sendKeys(firstName);//first name
				
				lastNameEle.clear();
				lastNameEle.sendKeys(lastName);//last name
				
				emailAddressEle.clear();
				emailAddressEle.sendKeys(emailAddress);//email address
				
				passwordEle.clear();
				passwordEle.sendKeys(password);//password
				
				confirmPasswordEle.clear();
				confirmPasswordEle.sendKeys(confirmPassword);//confirm password
				
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				registerEle.click();//register button
				
			}
}
