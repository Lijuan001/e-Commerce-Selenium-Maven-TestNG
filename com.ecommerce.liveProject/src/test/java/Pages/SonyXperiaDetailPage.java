package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Base.BaseTest;

public class SonyXperiaDetailPage extends BaseTest{
	@FindBy(css=".price")
	WebElement sonyXperiaPrice;
	
	public SonyXperiaDetailPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public String getSonyXperiaPrice() {
		return sonyXperiaPrice.getText();
	}
}
