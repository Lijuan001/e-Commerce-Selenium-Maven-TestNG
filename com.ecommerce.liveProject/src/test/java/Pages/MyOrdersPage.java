package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.BaseTest;

public class MyOrdersPage extends BaseTest{
	 @FindBy(xpath="//table[@id='my-orders-table']/tbody/tr[1]/td[6]/span/a")
	 WebElement viewOrder;
	 
	 public MyOrdersPage() {
		 PageFactory.initElements(driver, this);
	 }
	 
	 public void clickViewOrderLink() {
		 viewOrder.click();
	 }
	 
}
