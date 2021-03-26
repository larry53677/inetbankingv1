package com.inetbanking.testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.inetbanking.pageObjects.LoginPage_0;

public class TC_LoginTest_002 extends BaseClass_0
{
	@Test
	public void loginTest()
	{
		driver.get(baseURL);
		
		LoginPage_0 lp = new LoginPage_0(driver);
		lp.setUserName(username);
		lp.setPassword(password);
		lp.clickSubmit();
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
		}
		else
		{
			Assert.assertTrue(false);
		}	
	}
}
