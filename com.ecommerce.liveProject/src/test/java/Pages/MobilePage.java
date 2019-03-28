package Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.BaseTest;

public class MobilePage extends BaseTest{

	@FindBy(xpath="//select[@title='Sort By']")
	WebElement sortByDropDown;
	
	@FindBy(xpath="//a[@title='Sony Xperia']/../../div[@class='price-box']/span")
	WebElement sonyXperiaPrice;
	
	@FindBy(css=".product-name")
	List<WebElement> productsName;
	
	@FindBy(css="a[title='Sony Xperia']")
	WebElement sonyXperiaName;
	
	@FindBy(xpath="//a[@title='IPhone']/../div/h2/a")
	WebElement iphoneName;
	
	@FindBy(xpath="//a[@title='Sony Xperia']/../../div[@class='actions']/button")
	WebElement addToCartSonyXperiaButton;
	
	@FindBy(xpath="//a[@title='Sony Xperia']/../../div[@class='actions']/ul/li[2]/a")
	WebElement addToCompareSonyXperia;
	
	@FindBy(xpath="//a[@title='IPhone']/../../div[@class='actions']/ul/li[2]/a")
	WebElement addToCompareIphone;
	
	@FindBy(css="button[title='Compare']")
	WebElement compareButton;
	
	@FindBy(xpath="//table[@id='product_comparison']/tbody/td")
	List<WebElement> products;
	
	
	//"COMPARE PRODUCTS" popup WebElement
	@FindBy(css="div[class='page-title title-buttons']>h1")
	WebElement headingPopup;
	
	@FindBy(xpath="//table[@id='product_comparison']/tbody/tr/td[1]/h2")
	WebElement popupProduct1;
	
	@FindBy(xpath="//table[@id='product_comparison']/tbody/tr/td[2]/h2")
	WebElement popupProduct2;
	
	@FindBy(css="button[title='Close Window']")
	WebElement closeButtonPopup;
	
	public MobilePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public void  selectSortByName() {
		Select dropSortBy=new Select(sortByDropDown);
		dropSortBy.selectByIndex(1); //select 'SORT BY' dropdown as name
	}
	
	public List<WebElement> AllProductsSortByName() {
		return productsName;
	}
	
	//In the list of all mobile,read the cost of Sony Xperia mobile
	public String getCostOfSonyXperiaMobile() {
		return sonyXperiaPrice.getText();
	}
	
	//go to Sony Xperia Detail Page
	public void goToSonyXperiaDetailPage() {
		sonyXperiaName.click();
	}
	
	//get Sony Xperia name
	public String getSonyXperiaName() {
		return sonyXperiaName.getText();
	}
	
	//get Iphone name
	public String getIphoneName() {
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOf(iphoneName));
		return iphoneName.getText();
	}
	
	//click on 'ADD TO CART' for Sony Xperia mobile
	public void addToCartSonyXperia() {
		addToCartSonyXperiaButton.click();
	}
	
//	//In mobile products list,click on 'Add To Compare'
	public void addToCompare(WebElement webelement) {
		webelement.click();
	}
	
	//click on 'Add To Compare' for 2 mobiles(Sony Xperia and Iphone)
	//mobilepage.
	public void compareTwoPhoneXperiaAndIphone() {
		addToCompare(addToCompareSonyXperia);
		
		addToCompare(addToCompareIphone);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		compareButton.click();
		
	}
	
	//get "COMPARE PRODUCTS" POPUP Heading
	public String getHeadingPopup() {
		System.out.println("pop up heading is : "+headingPopup.getText());
		return headingPopup.getText();
	}
	
	
	//get "COMPARE PRODUCTS" popup product name it's sony xperia
	public String getProduct1NamePopup() {
		return popupProduct1.getText();
	}
	
	//get "COMPARE PRODUCTS" popup product name it's iphone
	public String getProduct2NamePop() {
		return popupProduct2.getText();
	}
	
	//close "COMPARE PRODUCTS" popup
	public void closeCompareProductsPopup() {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)", closeButtonPopup);
		closeButtonPopup.click();
	}

}
