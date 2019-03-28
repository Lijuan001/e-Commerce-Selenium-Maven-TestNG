package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Base.BaseTest;

public class BillingInfoFormPage extends BaseTest{

	@FindBy(css="input[title='Street Address']")
	WebElement addressEle;
	
	@FindBy(css="input[title='City']")
	WebElement cityEle;
	
	@FindBy(css="select[title='State/Province']")
	WebElement stateEle;

	@FindBy(css="input[title='Zip']")
	WebElement zipEle;
	
	@FindBy(css="select[title='Country']")
	WebElement countryEle;
	
	@FindBy(css="input[title='Telephone']")
	WebElement telephoneEle;
	
	public BillingInfoFormPage() {
		PageFactory.initElements(driver, this);
	}
	
	//get page title
	public String getTitle() {
		return driver.getTitle();
	}
	
	
	//fill billing information form
	public void fillBillingInformation(String country,String state,String zip,
			String address,String city,String telephone) {
		addressEle.clear();
		addressEle.sendKeys(address);
		
		cityEle.clear();
		cityEle.sendKeys(city);
		
		//select state from state dropdown eg:Georgia
		Select dropdown=new Select(stateEle);
		dropdown.selectByVisibleText(state);
		
		zipEle.clear();
		zipEle.sendKeys(zip);
		
		Select countryDropdown=new Select(countryEle);//select country from country dropdown,eg:United States
		countryDropdown.selectByVisibleText(country);
		
		telephoneEle.clear();
		telephoneEle.sendKeys(telephone);
		
		
	}
}
