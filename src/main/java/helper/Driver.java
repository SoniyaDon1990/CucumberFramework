package helper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Driver {
	
	public static WebDriver driver;
	 
	 public WebDriver openWebPage(String link) { 
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//src//main//resources//Driver//chromedriver.exe");
			try {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.get(link);
			}catch(Exception e) {
				System.out.println(e.getClass());
			}
			return driver;
	 }
	 
}
