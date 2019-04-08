package Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import Base.BaseTest;

public class PrintOptionPage extends BaseTest{
 @FindBy(xpath="//*[@id='destinationSelect']//select")
 WebElement changeDestination;
 
 @FindBy(xpath="//*[@id=\"button-strip\"]/paper-button[1]")
 WebElement printButton;
 
 public PrintOptionPage() {
	 PageFactory.initElements(driver, driver);
 }
 
// //change save destination by choosing from dropdown
// public void changeDestination(String changeOption) throws InterruptedException {
////	 System.out.println("to verify null pointer exception");
////	 Select destination=new Select(changeDestination);
////	 if(changeOption.equalsIgnoreCase("save as pdf")) {
////		 System.out.println("should choose save as pdf");
////		 //destination.selectByIndex(3);
////		 Thread.sleep(2000);
//	// }
// }
// 
// //click 'Print' button
// public void clickPrintButton() {
//	 printButton.click();
// }
 
 public void processPrint() throws AWTException, InterruptedException {
	 Robot robot=new Robot();
	 Thread.sleep(1000);
	 
	//To choose'Save as PDF'
	 robot.keyPress(KeyEvent.VK_TAB);
	 robot.keyPress(KeyEvent.VK_TAB);
	 robot.keyPress(KeyEvent.VK_ENTER);
	 robot.keyPress(KeyEvent.VK_DOWN);
	 robot.keyPress(KeyEvent.VK_ENTER);
	 Thread.sleep(5000);
	 
	 //To choose 'Save' 
	 robot.keyPress(KeyEvent.VK_CONTROL);
	 robot.keyPress(KeyEvent.VK_S);
	 robot.keyRelease(KeyEvent.VK_CONTROL);
	 robot.keyRelease(KeyEvent.VK_S);
	 Thread.sleep(5000);
	 
	 //To save file
	 robot.keyPress(KeyEvent.VK_TAB);
	 robot.keyPress(KeyEvent.VK_TAB);
	 robot.keyPress(KeyEvent.VK_ENTER);
	 
	 
 }
}
