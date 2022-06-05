package com.currencyconverter.testCases;

import java.io.IOException;
import java.lang.Math;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.currencyconverter.pageObjects.HomePage;

public class TC_Currency_Converter_002 extends BaseClass {

	@Test
	public void loginTest() throws IOException, InterruptedException{
		
		boolean overallResult = true;
		double exchangeRate=0.858;
		double inputAmount = 1280;
		HomePage homePage=new HomePage(driver);
		
		homePage.inputAmount(inputAmount);
		System.out.println("Entered "+inputAmount+" amount");
		
		homePage.fromCurrencySelect("EUR");
		logger.info("Selected EUR");
			
		homePage.toCurrencySelect("GBP");
		logger.info("Selected GBP");		
		
		homePage.clickConvertButton();
		System.out.println("clicked Convert Button");
		logger.info("Clicked on Convert option");
		
		Thread.sleep(5000);
		
		String resultString = homePage.getResult();
		Float resultFloat = getFloatFromString(resultString);
		System.out.println("resultFloat "+resultFloat);
		double threshold=0.05;
		int min_value = (int) Math.abs(inputAmount*exchangeRate*(1-threshold));
		int max_value = (int) Math.abs(inputAmount*exchangeRate*(1+threshold));
		int resultInt = (int) Math.abs(resultFloat);
		if (min_value <= resultInt &&  resultInt<= max_value)
			System.out.println("Its within the 5% tolerance value");
		else
			overallResult = false;
		
		if (overallResult)
		{
			Assert.assertTrue(true);
			logger.info("Test is passed");
			System.out.println("Test is passed");
		}
		else {
			captureScreen(driver,"TC_Currency_Converter_002");
			Assert.assertTrue(false);
			logger.info("Test is failed");
			System.out.println("Test is failed");
		}
	}
}
