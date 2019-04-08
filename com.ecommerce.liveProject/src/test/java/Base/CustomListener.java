package Base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomListener implements ITestListener{

	public void onTestFailure(ITestResult tr) {
		log(tr.getName()+"--Test method failed\n");
	}
	
	 public void onTestSuccess(ITestResult tr) {
	    log(tr.getName()+"--Test method success\n");
	  }


	 
	  public void onTestSkipped(ITestResult tr) {
		  log(tr.getName()+"--Test method skipped\n");
	  }

	
	

	private void log(String string) {
		System.out.println(string);
		
	}

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
}
