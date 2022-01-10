package listeners;



import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import frameWorkClasses.Utilities;;

public class Listeners implements ITestListener {

	Utilities uts = new Utilities();
	
	@Override
	public void onTestStart(ITestResult result) {

		// ITestListener.super.onTestStart(result);
		System.out.println("Listener starts" + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
        
		// ITestListener.super.onTestSuccess(result);
		System.out.println("On success: We are in the listener" + result.getName());
		try 
        {
        	uts.takeSnapShot("On test success" + uts.timereturn() + ".png");
        }
        
        catch (Exception e) 
        {
        e.printStackTrace();	
        }
	}

	@Override
	public void onTestFailure(ITestResult result) {

		// ITestListener.super.onTestFailure(result);
		System.out.println("On failure: listener fail");

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		// ITestListener.super.onTestSkipped(result);
		System.out.println("On skipped: ");

	}

	@Override
	public void onStart(ITestContext result) {

		// ITestListener.super.onStart(result);
		System.out.println("On start:");

	}

	@Override
	public void onFinish(ITestContext result) {

		// ITestListener.super.onFinish(result);
		System.out.println("On finish:");

	}

}
