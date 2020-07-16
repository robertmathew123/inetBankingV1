package com.inetbanking.testCases;


import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

public class TC_Login_001 extends BaseClass
{

	
@Test
public void loginTest() throws InterruptedException
{

LoginPage lp = new LoginPage(driver);
lp.setUserName(configPropObj.getProperty("username"));
logger.info("Entered the UserName");

lp.setPassword(configPropObj.getProperty("password"));
logger.info("Entered the Password");

lp.clickSubmit();


WebDriverWait wait = new WebDriverWait(driver, 20);
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='menusubnav']/li[1]")));
if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
{

Assert.assertTrue(true);
logger.info("Login Test Passed");

}//if

else
{
	
Assert.assertTrue(false);
logger.info("Login Test Failed");
	
}//else
	
}//loginTest
	
	
	
}//TC_Login_001
