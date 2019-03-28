package Base;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class CustomListener extends TestListenerAdapter{

	@Override
	public void onTestFailure(ITestResult tr) {
		log(tr.getName()+"--Test method failed\n");
	}
	
	 @Override
	  public void onTestSuccess(ITestResult tr) {
	    log(tr.getName()+"--Test method success\n");
	  }


	  @Override
	  public void onTestSkipped(ITestResult tr) {
		  log(tr.getName()+"--Test method skipped\n");
	  }

	
	

	private void log(String string) {
		System.out.println(string);
		
	}
}
