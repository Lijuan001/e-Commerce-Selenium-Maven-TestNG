package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.BaseTest;

public class ViewOrderDetail extends BaseTest{
	@FindBy(xpath="//*[@class='my-account']/div/h1")
	WebElement orderNO;
	
	@FindBy(linkText="Print Order")
	WebElement printOrder;
	
	public ViewOrderDetail() {
		PageFactory.initElements(driver, this);
	}
	
	//get order information
	public String getOrderDetail() {
		return orderNO.getText();
	}
	
	//click 'PrintOrder' link
	public void clickPrintOrderlINK() {
		printOrder.click();
	}
}
