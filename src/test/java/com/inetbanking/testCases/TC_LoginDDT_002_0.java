package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage_0;
import com.inetbanking.utilities.XLUtils;

public class TC_LoginDDT_002_0 extends BaseClass_0
{
	@Test (dataProvider="LoginData")
	public void loginDDT(String user, String pwd) throws InterruptedException 
	{
		LoginPage_0 lp = new LoginPage_0(driver);	//(1) import page object class; (2) Call page constructor
		lp.setUserName(user);
		logger.info("user name provided");
		logger.info("password provided");
		lp.setPassword(pwd);
		lp.clickSubmit();
		
		Thread.sleep(3000);	//need to add InterruptedException
		
		if(isAlertPresent()==true)
		{
			driver.switchTo().alert().accept();		//close alert
			driver.switchTo().defaultContent(); 	//back to the login page
			Assert.assertTrue(false);
			logger.warn("Login failed");
		}
		else
		{
			Assert.assertTrue(true);
			logger.info("Login passed");
			
			lp.clickLogout();
			Thread.sleep(3000);
				
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();	//back to the login page
		}
	}
	
	public boolean isAlertPresent()	//user defined method to check on alert
	{
		try
		{
		driver.switchTo().alert();	//if false, will throw an exception -> exception handling using try
		return true;
		}
		catch(NoAlertPresentException e)	//import exception
		{
			return false;
		}
	}
		
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		
		int rownum=XLUtils.getRowCount(path, "Sheet1");			//import java.io.IOException
		int cocount=XLUtils.getCellCount(path, "Sheet1", 1);
		
		String logindata[][]=new String[rownum][cocount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<cocount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}
		
		return logindata;
	}
}
