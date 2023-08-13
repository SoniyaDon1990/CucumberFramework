package helper;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserInteractions {
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	public UserInteractions(WebDriver driver) {
		this.driver = driver;
		 this.wait = new WebDriverWait(driver, 10);
	}
	
	public void inputKeys(String input, By by, String elementName, int time) throws Exception {
		try {
			wait = new WebDriverWait(driver, time);
		WebElement element =wait.until(ExpectedConditions.presenceOfElementLocated(by));
		
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
		jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);   
		
		element.sendKeys(input);
		Reporter.report("Pass","<b>"+input +"</b>"+" should be entered in "+ elementName, input +" entered in "+ elementName, takeSnapShot());	
	
		jsExecutor.executeScript("arguments[0].style.border='0px solid red'", element); 
		}catch (TimeoutException e){
			e.printStackTrace();
			Reporter.report("Fail","<b>"+input +"</b>"+" should be entered in "+ elementName, input +" could not be entered in "+ elementName+" due to "+e.getClass(), takeSnapShot());
		}catch (Exception e){
			e.printStackTrace();
			Reporter.report("Fail","<b>"+input +"</b>"+" should be entered in "+ elementName, input +" could not be entered in "+ elementName+" as "+e.getClass(), takeSnapShot());
		}	
		}
	
	public void activeInputKeys(String input) throws Exception {
		try {
		driver.switchTo().activeElement().sendKeys(input);
		Reporter.report("Pass","<b>"+input +"</b>"+" should be entered in active element", input +" entered in active element", takeSnapShot());	
		
		}catch (TimeoutException e){
			e.printStackTrace();
			Reporter.report("Fail","<b>"+input +"</b>"+" should be entered in active element", input +" entered in active element", takeSnapShot());	
			}catch (Exception e){
			e.printStackTrace();
			Reporter.report("Fail","<b>"+input +"</b>"+" should be entered in active element", input +" entered in active element", takeSnapShot());	
				}
		
	}
	
	public void inputKeys(Keys input, By by) {
		try {
			wait = new WebDriverWait(driver, 10);
		WebElement element =wait.until(ExpectedConditions.presenceOfElementLocated(by));
		
		element.sendKeys(input);
		}catch (Exception e){
			e.printStackTrace();
		}	
		}
	
	public void dragElement(int range, By by, String elementName) throws Exception {
		try {
			wait = new WebDriverWait(driver, 10);
			WebElement element =wait.until(ExpectedConditions.presenceOfElementLocated(by));

			int value = Integer.parseInt(element.getAttribute("value"));
			if(value!=range) {
				if(value>range) {
					for(int i=0;i<(value-range);i++)
						inputKeys(Keys.ARROW_LEFT,by);
				}else {
					for(int i=0;i<(range-value);i++)
						inputKeys(Keys.ARROW_RIGHT,by);
				}
			}
			Reporter.report("Pass","Range value should be selected as "+ "<b>"+range +"</b>", "Range value selected as "+ range, takeSnapShot());	
		}catch (TimeoutException e){
			e.printStackTrace();
			Reporter.report("Fail","Range value should be selected as "+ "<b>"+range +"</b>", "Range value could not be selected as "+ range, takeSnapShot());
		}catch (Exception e){
			e.printStackTrace();
			Reporter.report("Fail","Range value should be selected as "+ "<b>"+range +"</b>", "Range value could not be selected as "+ range, takeSnapShot());
		}
	}
		// TODO Auto-generated method stub
		
	
	
	public void click(By by, String elementName, int time) throws Exception {
		wait = new WebDriverWait(driver, time);
		try {
			WebElement element =wait.until(ExpectedConditions.presenceOfElementLocated(by));

			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
			jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);   
			String snap = takeSnapShot();
			jsExecutor.executeScript("arguments[0].style.border='0px solid red'", element); 
			element.click();	
			Reporter.report("Pass",elementName +" should be clicked", elementName +" clicked",snap);	

		}catch (TimeoutException e){
			e.printStackTrace();
			Reporter.report("Fail",elementName +" should be clicked", elementName +" could not be clicked", takeSnapShot());	
		}catch (StaleElementReferenceException e){
			e.printStackTrace();
			Reporter.report("Fail",elementName +" should be clicked", elementName +" could not be clicked", takeSnapShot());	
		}
		
	}
	
	public void click(By by, String elementName) throws Exception {
		wait = new WebDriverWait(driver, 10);
		try {
		WebElement element =wait.until(ExpectedConditions.presenceOfElementLocated(by));
			element.click();	
	}catch (TimeoutException e){
		e.printStackTrace();
		Reporter.report("Fail",elementName +" should be clicked", elementName +" could not be clicked", takeSnapShot());	
		}catch (Exception e){
		e.printStackTrace();
		Reporter.report("Fail",elementName +" should be clicked", elementName +" could not be clicked", takeSnapShot());	
		}
		
	}
	
	public void checkElement(By by, String elementName) throws Exception {
		wait = new WebDriverWait(driver, 10);
		try {
			WebElement element =wait.until(ExpectedConditions.presenceOfElementLocated(by));
			
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
			jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);   		
			Reporter.report("Pass",elementName +" should be present", elementName +" was present", takeSnapShot());		
			jsExecutor.executeScript("arguments[0].style.border='0px solid red'", element);  
			
	}catch (TimeoutException e){
		e.printStackTrace();
		Reporter.report("Fail",elementName +" should be present", elementName +" was not present", takeSnapShot());	
		}catch (Exception e){
		e.printStackTrace();
		Reporter.report("Fail",elementName +" should be present", elementName +" was not present", takeSnapShot());	
		
		}
		
	}
	
	public WebElement getElement(By by, String elementName) throws Exception {
		wait = new WebDriverWait(driver, 10);
		try {
			WebElement element =wait.until(ExpectedConditions.presenceOfElementLocated(by));
			
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
			jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);   		
			Reporter.report("Pass",elementName +" should be present", elementName +" was present", takeSnapShot());		
			jsExecutor.executeScript("arguments[0].style.border='0px solid red'", element);  
			return element;
			
	}catch (TimeoutException e){
		e.printStackTrace();
		Reporter.report("Fail",elementName +" should be present", elementName +" was not present", takeSnapShot());	
		}catch (Exception e){
		e.printStackTrace();
		Reporter.report("Fail",elementName +" should be present", elementName +" was not present", takeSnapShot());	
		
		}
		return null;
		
	}
	

	public void checkBoxSelection(boolean state, By by, String elementName, int time) throws Exception {
		wait = new WebDriverWait(driver, time);
		try {
		WebElement element =wait.until(ExpectedConditions.presenceOfElementLocated(by));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
		jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);   
		
		boolean status = element.isSelected();
		if(state!=status) {
			element.click();	
		}
		Reporter.report("Pass","<b>"+state +"</b>"+" should be selected for "+ elementName, state +" selected for "+ elementName, takeSnapShot());	

		jsExecutor.executeScript("arguments[0].style.border='0px solid red'", element);   
	}catch (TimeoutException e){
		e.printStackTrace();
		Reporter.report("Fail","<b>"+state +"</b>"+" should be selected for "+ elementName, state +" could not be selected for "+ elementName+" as "+e.getClass(), takeSnapShot());	
		}catch (Exception e){
		e.printStackTrace();
		Reporter.report("Fail","<b>"+state +"</b>"+" should be selected for "+ elementName, state +" could not be selected for "+ elementName+" as "+e.getClass(), takeSnapShot());	
		
		}
		
	}
	
	
	public void dropDownSelection(String option,String type, By by, String elementName, int time) throws Exception {
		wait = new WebDriverWait(driver, time);
		try {
		WebElement element =wait.until(ExpectedConditions.presenceOfElementLocated(by));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
		jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);   
		Select objSelect = new Select(element);
		switch (type){
		case "value":
			objSelect.selectByValue(option);
		case "text":	
			objSelect.deselectByVisibleText(option);
		}
		
		Reporter.report("Pass","<b>"+option +"</b>"+" should be selected for "+ elementName, option +" selected for "+ elementName, takeSnapShot());	
		
		jsExecutor.executeScript("arguments[0].style.border='0px solid red'", element);   
		}catch (TimeoutException e){
		e.printStackTrace();
		Reporter.report("Fail","<b>"+option +"</b>"+" should be selected for "+ elementName, option +" could not be selected for "+ elementName+" as "+e.getClass(), takeSnapShot());	
		}catch (Exception e){
		e.printStackTrace();
		Reporter.report("Fail","<b>"+option +"</b>"+" should be selected for "+ elementName, option +" could not be selected for "+ elementName+" as "+e.getClass(), takeSnapShot());	
		
		}
		
	}
	
	public void clickRadio(By by, String elementName, int time) throws Exception {
		wait = new WebDriverWait(driver, time);
		try {
		WebElement element =wait.until(ExpectedConditions.presenceOfElementLocated(by));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;  
		jsExecutor.executeScript("arguments[0].style.border='2px solid red'", element);   
		
		boolean bol = element.isSelected();		
			if(bol!=true) {
				element.click();
			}
		Reporter.report("Pass","Radio Button "+elementName+" should be clicked", "Radio Button "+elementName+" clicked", takeSnapShot());	 
		jsExecutor.executeScript("arguments[0].style.border='0px solid red'", element);   
		}catch (TimeoutException e){
		e.printStackTrace();
		Reporter.report("Fail","Radio Button "+elementName+" should be clicked", "Radio Button "+elementName+" Ccould not be clicked as "+e.getClass(), takeSnapShot());	
		}catch (Exception e){
		e.printStackTrace();
		Reporter.report("Fail","Radio Button "+elementName+" should be clicked", "Radio Button "+elementName+" Ccould not be clicked as "+e.getClass(),takeSnapShot());	
		
		}
		
	}
	
	public void pressKey(int key) {
		Robot robo;
		try {
			robo = new Robot();
			robo.keyPress(key);
			robo.keyRelease(key);
			Thread.sleep(10);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void pressNumber(int i) throws Exception {
		String numString = Integer.toString(i);
		String[] numArray = numString.split("");
		
		Robot robo;
		try {
			robo = new Robot();
			for(int j=0;j<numArray.length;j++) {
				int k = toKeyEvent(numArray[j]);
			robo.keyPress(k);
			robo.keyRelease(k);
			Thread.sleep(10);
			}
			Reporter.report("Pass","<b>"+i +"</b>"+" should be entered in active Element", i+" entered in Active Element", takeSnapShot());	

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Reporter.report("Fail","<b>"+i +"</b>"+" should be entered in active Element", i+" could not be entered in Active Element", takeSnapShot());	
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Reporter.report("Fail","<b>"+i +"</b>"+" should be entered in active Element", i+" could not be entered in Active Element", takeSnapShot());	
		}	
	}
	
	public void ReportComparision(String status, String expected, String actual) throws Exception {
		
			Reporter.report(status,expected, actual, takeSnapShot());		
}
	
	
	private int toKeyEvent(String string) {
		int k = Integer.parseInt(string);
		switch (k){
		case 0:
			return KeyEvent.VK_0;
		case 1:
			return KeyEvent.VK_1;
		case 2:
			return KeyEvent.VK_2;
		case 3:
			return KeyEvent.VK_3;
		case 4:
			return KeyEvent.VK_4;
		case 5:
			return KeyEvent.VK_5;
		case 6:
			return KeyEvent.VK_6;
		case 7:
			return KeyEvent.VK_7;
		case 8:
			return KeyEvent.VK_8;
		case 9:
			return KeyEvent.VK_9;
		}
	return 0;
	}

	public String takeSnapShot(){
		String fileWithPath=Reporter.screenPath();
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(fileWithPath);
		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileWithPath;
	}
	
	public List<WebElement> getElements(By by) {
		wait = new WebDriverWait(driver, 10);
		try {
		List<WebElement> elements =wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		return elements;
		}catch (TimeoutException e){
			Reporter.report("Fail","<b> elements with by "+by +"</b>"+" should be present", "<b> elements with by "+by +"</b>"+" was not present", takeSnapShot());			
		return null;
		}
		
		
	}
		
	}


