package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.BaseTest;

public class CheckoutConfirmPage extends  BaseTest{

	@FindBy(xpath="//div[@class='main']/div/p[1]/a")
	WebElement orderNumber;
	
	public CheckoutConfirmPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void getTitle() {
		driver.getTitle();
	}
	
	public String getOrderNumber() {
		return orderNumber.getText();
	}
}
