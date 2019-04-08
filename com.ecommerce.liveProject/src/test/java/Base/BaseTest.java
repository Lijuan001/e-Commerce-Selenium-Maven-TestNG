package Base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import Util.Constants;

public class BaseTest {
	public static WebDriver driver;
	public Properties prop;
	
	public BaseTest() {
		
		prop=new Properties();//create an empty property list without defautl vlaue
		System.out.println("prop is :"+prop);
		try {
			FileInputStream fil=new FileInputStream(System.getProperty("user.dir")+
					"\\src\\test\\java\\config\\config.properties");
			prop.load(fil);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void initBrowser() {
		String browser=prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver",

					System.getProperty("user.dir") + "\\resource\\chromedriver.exe");

			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.firefox.driver",

					System.getProperty("user.dir") + "\\resource\\geckodriver.exe");

			driver = new ChromeDriver();

		}else if (browser.equalsIgnoreCase("ie")) {

			System.setProperty("webdriver.ie.driver",

					System.getProperty("user.dir") + "\\resource\\IEDriverServer.exe");

			driver = new ChromeDriver();

		}else{

			System.out.println("Chorme was not defined in Configuration");

		}



		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Constants.IMPLICITLY_WAIT, TimeUnit.SECONDS);

		driver.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
	}
	
	public String getCurrentDateAndTime() {
		LocalDateTime myObj=LocalDateTime.now();
		DateTimeFormatter myFormatObj=DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		
		String formattedDate=myObj.format(myFormatObj);
		return formattedDate;
		
	}
	
	//get screenshot
	
	public void getScreenShot() {
		File srcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentTime=getCurrentDateAndTime();
		System.out.println("current time is: "+currentTime);
		String png=System.getProperty("user.dir")+"\\Screenshot\\"+"screenshot"+currentTime+".png";
		System.out.println("png is: "+png);
		try {
			FileUtils.copyFile(srcFile, new File(png));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//generate a random emailAddress
	
	public String generateEmailAddress() {
		String string="abcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder chars=new StringBuilder();
		
		Random rnd=new Random();
		
		while(chars.length()<10) {
			int index=(int) (rnd.nextFloat() * string.length());
			System.out.println("next float is :"+rnd.nextFloat());
						
			chars.append(string.charAt(index));
			System.out.println("char is :"+string.charAt(index));

		}
		
		chars.append("@email.com");
		String email=chars.toString();
		System.out.println("email is:"+email);
		return email;
	}
	
}
	
	
	
	
	
	
	
	
	
	

