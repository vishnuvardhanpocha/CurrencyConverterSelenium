package com.currencyconverter.testCases;

import java.io.IOException;
import java.lang.Math;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.currencyconverter.pageObjects.HomePage;

public class TC_Currency_Converter_001 extends BaseClass {

	@Test
	public void loginTest() throws IOException, InterruptedException{
		
		HomePage homePage=new HomePage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		
		double amount[] = {1260, 5680, 265.56, 153.65, 569};
		double exchangeRate=0.8536;
		boolean overallResult = true;
		String resultString;
		float resultFloat ;
		int min_value;
		int max_value;
		int resultInt;

		for (int i=0; i<amount.length;i++)
		{	
			
			if(i==0){
				homePage.inputAmount(amount[i]);
				homePage.fromCurrencySelect("EUR");
				logger.info("Selected EUR");
				homePage.toCurrencySelect("GBP");
				logger.info("Selected GBP");
				homePage.clickConvertButton();
				logger.info("clicked Convert Button");
				Thread.sleep(2000);
				resultString = homePage.getResult();
			}
			else {
				driver.get("https://www.xe.com/currencyconverter/convert/?Amount="+amount[i]+"&From=EUR&To=GBP");
				Thread.sleep(5000);
				//resultString = homePage.getResult();
				WebElement element = driver.findElement(By.cssSelector(".result__BigRate-sc-1bsijpp-1.iGrAod"));
				resultString = js.executeScript("arguments[0].getText();", element).toString();
			}

			resultFloat = getFloatFromString(resultString);
			System.out.println("resultFloat "+resultFloat);
			double threshold=0.05;
			min_value = (int) Math.abs(amount[i]*exchangeRate*(1-threshold));
			max_value = (int) Math.abs(amount[i]*exchangeRate*(1+threshold));
			resultInt = (int) Math.abs(resultFloat);
			if (min_value <= resultInt &&  resultInt<= max_value)
				System.out.println("Its within the 5% tolerance value");
			else
				overallResult = false;
		}
		
		if (overallResult)
		{
			Assert.assertTrue(true);
			logger.info("Test is passed");
			System.out.println("Test is passed");
		}
		else {
			captureScreen(driver,"TC_Currency_Converter_001");
			Assert.assertTrue(false);
			logger.info("Test is failed");
			System.out.println("Test is failed");
		}
	}
}
