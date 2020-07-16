package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002 extends BaseClass
{

@Test(dataProvider="LoginData")
public void loginDDT(String user, String pass)
{

LoginPage lp = new LoginPage(driver);
lp.setUserName(user);
logger.info("user name provided");
lp.setPassword(pass);
logger.info("password provided");
lp.clickSubmit();

if(isAlertPresent()==true)
{

driver.switchTo().alert().accept();//close the alert
driver.switchTo().defaultContent();
Assert.assertTrue(false);
logger.warn("Login Failed");

}//if

else
{
logger.info("Login passed");	
Assert.assertTrue(true);
lp.clickLogout();
driver.switchTo().alert().accept();//close logout alert
driver.switchTo().defaultContent();
	
}//else

}//loginDDT

public boolean isAlertPresent()//user defined method to check alert is present or not
{

try
{
	
driver.switchTo().alert();
return true;
}//try

catch(Exception e)
{
	
return false;
	
}//catch
	
}//isAlertPresent

@DataProvider(name="LoginData")
String [][] getData() throws IOException
{

String path = System.getProperty("user.dir")+"/src/main/java/com/inetbanking/testData/LoginData.xlsx";
int rownum = XLUtils.getRowCount(path, "Sheet1");
int colcount = XLUtils.getCellCount(path, "Sheet1", 1);

String logindata[][] = new String[rownum][colcount];

for(int i=1; i<=rownum;i++)
{

for(int j=0; j<colcount;j++)
{

logindata[i-1][j] = XLUtils.getCellData(path, "Sheet1", i, j);//1,0
	
}//for j
	
	
}//for  i

return logindata;
	
}//getData
	
}//TC_LoginDDT_002
