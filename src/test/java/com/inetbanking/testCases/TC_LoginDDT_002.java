package com.inetbanking.testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import org.testng.annotations.DataProvider;
import org.openqa.selenium.NoAlertPresentException;
import com.inetbanking.utilities.XLUtils;


import com.inetbanking.pageObjects.LoginPage;


public class TC_LoginDDT_002 extends BaseClass
{

	@Test(dataProvider="LoginData")
	public void loginDDT(String user,String pwd) throws InterruptedException, IOException
	{
		LoginPage lp=new LoginPage(driver);
		lp.setUserName(user);
		logger.info("username = "+user);
		lp.setPassword(pwd);
		
		Thread.sleep(5000);
		
		logger.info("password = "+pwd);
		lp.clickSubmit();
		
		Thread.sleep(5000);
		
		if(isAlertPresent()==true)
		{
			logger.info("Invalid alert presented. Login failed!");
			driver.switchTo().alert().accept();//close alert
			
			Thread.sleep(2000);
			captureScreen(driver,"loginTest");
			logger.info("\n");
			
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("Login failed");
		}
		else
		{
			Assert.assertTrue(true);
			logger.info("Invalid alert not presented. Login successful!");
			
			captureScreen(driver,"loginTest");
			logger.info("\n");
			
			lp.clickLogout();
			Thread.sleep(1000);
			driver.switchTo().alert().accept();//close logout alert
			driver.switchTo().defaultContent();
			Thread.sleep(2000);
			
		}
		
		
	}
	
	
	public boolean isAlertPresent() //user defined method created to check alert is presetn or not
	{
		try
		{
		driver.switchTo().alert();
		return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
		
	}
	
	
	@DataProvider(name="LoginData")
	String [][] getData() throws IOException
	{
		String path=System.getProperty("user.dir")+"/src/test/java/com/inetbanking/testData/LoginData.xlsx";
		
		int rownum=XLUtils.getRowCount(path, "Sheet1");
		int colcount=XLUtils.getCellCount(path,"Sheet1",1);
		
		String logindata[][]=new String[rownum][colcount];
		
		for(int i=1;i<=rownum;i++)
		{
			for(int j=0;j<colcount;j++)
			{
				logindata[i-1][j]=XLUtils.getCellData(path,"Sheet1", i,j);//1 0
			}
				
		}
	return logindata;
	}
	
}