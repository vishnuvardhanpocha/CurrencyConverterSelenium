package com.currencyconverter.testCases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.currencyconverter.utilities.ReadConfig;

public class BaseClass {
	
	ReadConfig readConfig = new ReadConfig();
	
	public String baseURL=readConfig.getApplicationURL();
	public static WebDriver driver;
	
	public static Logger logger;
	
	@SuppressWarnings("deprecation")
	@Parameters("browser")
	@BeforeClass
	public void setup(String br) throws InterruptedException
	{
		logger = Logger.getLogger("currency converter");
		PropertyConfigurator.configure("Log4j.properties");
		
		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",readConfig.getChromePath());
			driver=new ChromeDriver();			
		}
		else if(br.equals("firefox"))
		{	
			System.setProperty("webdriver.gecko.driver",readConfig.getFirefoxPath());
			driver=new FirefoxDriver();		
		}
		else if(br.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver",readConfig.getIEPath());
			driver=new InternetExplorerDriver();			
		}
		else
		{
			System.out.println("Please provide valid value for browser parameter in testNG file");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		
		driver.get(baseURL);
		logger.info("Opened URL");
		
		//Mazimize current window
		driver.manage().window().maximize();
		
		driver.findElement(By.cssSelector("button[class='button__BaseButton-sc-1qpsalo-0 ctapkr']")).click();
		logger.info("Clicked on accept option in cookies popup");
		
		//Wait for advertisement pop-up to display
		Thread.sleep(10000);
		if(driver.findElements(By.xpath("/html[1]/body[1]/div[6]/div[2]/div[1]/button[1]")).size() > 0) {
			driver.findElement(By.xpath("/html[1]/body[1]/div[6]/div[2]/div[1]/button[1]")).click();
			logger.info("Closed the advertisement popup");
		}
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.quit();
	}

	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		File target = new File(System.getProperty("user.dir") + "/screenshots/" + tname + timeStamp + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}
	
	public String randomestring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
	
	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}
	
	public static float getFloatFromString(String str) {
		return Float.parseFloat(str.replaceAll("[^\\d.]", ""));
	}
}