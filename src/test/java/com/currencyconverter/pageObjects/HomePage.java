package com.currencyconverter.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver ldriver;
	
	//Constructor
	public HomePage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(css="button[class='button__BaseButton-sc-1qpsalo-0 ctapkr']")
	@CacheLookup
	WebElement cookiesAccept;
	
	@FindBy(id="amount")
	@CacheLookup
	WebElement inputAmount;
	
	@FindBy(id="midmarketFromCurrency")
	@CacheLookup
	WebElement fromCurrency;
	
	@FindBy(id="midmarketToCurrency")
	@CacheLookup
	WebElement toCurrency;
	
	@FindBy(css="button[type='submit']")
	@CacheLookup
	WebElement convertButton;
	
	@FindBy(css=".result__BigRate-sc-1bsijpp-1.iGrAod")
	@CacheLookup
	WebElement result;


	public void clickCookiesPopup() {
		cookiesAccept.click();
	}
	
	public void inputAmount(double amount) {
		ldriver.navigate().refresh();
		inputAmount.sendKeys(String.valueOf(amount));
	}
	
	public void fromCurrencySelect(String fCurrency) {
		fromCurrency.sendKeys(fCurrency);
		fromCurrency.sendKeys(Keys.ENTER);
		//Select dropDown = new Select(fromCurrency);
		//dropDown.selectByIndex(1);
		System.out.println("Selected "+fCurrency);
	}
	
	public void toCurrencySelect(String tCurrency) {
		toCurrency.sendKeys(tCurrency);
		toCurrency.sendKeys(Keys.ENTER);
		ldriver.findElement(By.xpath("//span[normalize-space()='Convert']"));
		//Select dropDown = new Select(toCurrency);
		//dropDown.selectByIndex(1);
		System.out.println("Selected "+tCurrency);
	}
	
	public void clickConvertButton() {
		//To avoid StaleElementReferenceException for clicking on convert button, refreshing the page here
		//ldriver.navigate().refresh();
		//js.executeScript("arguments[0].click();", Convert);
		convertButton.click();
		System.out.println("clicked Convert Button");
	}
	
	public String getResult() throws InterruptedException {
		
		return result.getText();
	}
}
