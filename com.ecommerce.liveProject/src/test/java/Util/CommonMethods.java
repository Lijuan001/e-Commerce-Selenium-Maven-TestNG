package Util;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import Base.BaseTest;

public class CommonMethods extends BaseTest{
	//get current data and time
	public static String getCurrentDateAndTime() {
		LocalDateTime myObj=LocalDateTime.now();
		DateTimeFormatter myFormatObj=DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		
		String formattedDate=myObj.format(myFormatObj);
		return formattedDate;
		
	}
	
	//get screenshot
		public static void getScreenShot() {
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
		public static String generateEmailAddress() {
		String string="abcdefghijklmnopqrstuvwxyz1234567890";
		StringBuilder chars=new StringBuilder();
		
		Random rnd=new Random();
		
		while(chars.length()<10) {
			int index=(int) (rnd.nextFloat() * string.length());
			//System.out.println("next float is :"+rnd.nextFloat());
						
			chars.append(string.charAt(index));
			//System.out.println("char is :"+string.charAt(index));

		}
		
		chars.append("@gmail.com");
		String email=chars.toString();
		System.out.println("email is:"+email);
		return email;
	}
	
}
