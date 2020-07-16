package com.inetbanking.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{

WebDriver driver;	

public LoginPage(WebDriver driver)
{

this.driver = driver;
PageFactory.initElements(driver, this);

}//LoginPage
	

@FindBy(xpath="//input[@name='uid']")
@CacheLookup
WebElement txtUserName;

@FindBy(xpath="//input[@name='password']")
@CacheLookup
WebElement txtPassWord;

@FindBy(xpath="//input[@name='btnLogin']")
@CacheLookup
WebElement btnLogin;

@FindBy(xpath="//a[@href='Logout.php']")
@CacheLookup
WebElement lnkLogout;


public void setUserName(String uname)
{
	
txtUserName.sendKeys(uname);	

}//setUserName

public void setPassword(String pwd)
{
	
txtPassWord.sendKeys(pwd);	

}//setPassword

public void clickSubmit()
{
	
btnLogin.click();	

}//setPassword

public void clickLogout()
{
	
lnkLogout.click();	

}//clickLogout


}//LoginPage
