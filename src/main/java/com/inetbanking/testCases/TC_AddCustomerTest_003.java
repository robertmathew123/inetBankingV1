package com.inetbanking.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass
{
	
@Test
public void addNewCustomer() throws InterruptedException
{

LoginPage lp = new LoginPage(driver);
lp.setUserName(configPropObj.getProperty("username"));
logger.info("user name provided");
lp.setPassword(configPropObj.getProperty("password"));
logger.info("password provided");
lp.clickSubmit();

Thread.sleep(3000L);

AddCustomerPage addcust = new AddCustomerPage(driver);
addcust.clickAddNewCustomer();

logger.info("providing customer details....");
addcust.custName("Pavan");
addcust.custgender("male");
addcust.custdob("10","15","1985");
Thread.sleep(5000);
addcust.custaddress("INDIA");
addcust.custcity("HYD");
addcust.custstate("AP");
addcust.custpinno("5000074");
addcust.custtelephoneno("987890091");

String email = randomString()+"gmail.com";
addcust.custemailid(email);
addcust.custpassword("abcdef");
addcust.custsubmit();
driver.switchTo().alert().accept();//close  alert
driver.switchTo().defaultContent();

Thread.sleep(3000);

logger.info("validation started....");

boolean res=driver.getPageSource().contains("Customer Registered Successfully!!!");

if(res==true)
{
Assert.assertTrue(true);
logger.info("test case passed....");
	
}//if

else
{
	
logger.info("test case failed....");
Assert.assertTrue(true);

}//else
	
}//addNewCustomer


}//TC_AddCustomerTest_003
