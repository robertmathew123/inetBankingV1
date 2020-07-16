package com.inetbanking.testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass 
{
public static WebDriver driver;
public Logger logger = LogManager.getLogger(this.getClass()); 
public Properties configPropObj;

@BeforeClass
@Parameters("browser")
public void setUp(String br) throws IOException
{
// Load config.properties file
configPropObj = new Properties();
FileInputStream configfile = new FileInputStream(System.getProperty("user.dir") + "\\Configuration\\config.properties");
configPropObj.load(configfile);
// end of loading gconfig.properties file

if(br.equals("chrome"))
{
	
WebDriverManager.chromedriver().setup();
driver = new ChromeDriver();

}//chrome

else if(br.equals("firefox"))
{
	
WebDriverManager.firefoxdriver().setup();
driver = new FirefoxDriver();

}//firefox

else if(br.equals("ie"))
{
	
WebDriverManager.iedriver().setup();  
driver = new InternetExplorerDriver();

}//ie

driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
driver.get(configPropObj.getProperty("baseURL"));
driver.manage().window().maximize();
logger.info("URL is opened");
	
	

}//setUp

@AfterClass
public void tearDown()
{
	
driver.quit();	
	
}//tearDown

public String randomString()
{

String generatedstring = RandomStringUtils.randomAlphabetic(8);
return generatedstring;
	
}//randomString

public String randomNumber()
{
	
String generatednumber = RandomStringUtils.randomNumeric(4);
return generatednumber;

}//randomNumber


}//BaseClass
