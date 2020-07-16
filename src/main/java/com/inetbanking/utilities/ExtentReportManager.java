package com.inetbanking.utilities;

//Listener class used to generate Extent Reports

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.inetbanking.testCases.BaseClass;

public class ExtentReportManager implements ITestListener
{
	
public ExtentSparkReporter reporter;
public ExtentReports extent;
public ExtentTest test;
	
public void onStart(ITestContext testContext)
{		
String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
String repName="InetBanking-Report-"+timeStamp+".html";
		
String path = (System.getProperty("user.dir")+"\\reports\\" +repName);//Specify the location
ExtentSparkReporter reporter = new ExtentSparkReporter(path);
reporter.config().setDocumentTitle("InetBanking Project");//Title of the report
reporter.config().setReportName("Functional Test Report");//Name of the report
reporter.config().setTheme(Theme.DARK);
		
extent=new ExtentReports();
extent.attachReporter(reporter);
extent.setSystemInfo("Host name","localhost");
extent.setSystemInfo("Environemnt","QA");
extent.setSystemInfo("user","Robert");
			
}//onStart
	
	
public void onTestSuccess(ITestResult result)
{
	
test=extent.createTest(result.getName()); // create new entry in the report
test.log(Status.PASS, "Test Case PASSED IS " + result.getName()); //Send the passed information
}//onTestSuccess
	
public void onTestFailure(ITestResult result)
{
test=extent.createTest(result.getName()); // create new entry in the report
		
test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent report


try 
{
	
test.addScreenCaptureFromPath(captureScreen(BaseClass.driver,result.getName())); //Take the screen shot

}//try

catch (IOException e1) 
{
	
e1.printStackTrace();

}


}//onTestFailure
	
public void onTestSkipped(ITestResult result)
{
	
test=extent.createTest(result.getName()); // create new entry in the report
test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());

}//onTestSkipped
	
public void onFinish(ITestContext testContext)
{
	
extent.flush();

}//onFinish

public void onTestStart(ITestResult result) 
{
		
		
}//onTestStart

public void onTestFailedButWithinSuccessPercentage(ITestResult result) 
{
		
		
}//onTestFailedButWithinSuccessPercentage

public String captureScreen(WebDriver driver, String tname) throws IOException 
{
	
TakesScreenshot ts = (TakesScreenshot) driver;
File source = ts.getScreenshotAs(OutputType.FILE);
String destpath = System.getProperty("user.dir")+"\\Screenshots\\"+tname+".png";
File file  = new File(destpath);
FileUtils.copyFile(source, file);
System.out.println("Screenshot taken");
return destpath;

}//captureScreen

	
}//ExtentReportManager
