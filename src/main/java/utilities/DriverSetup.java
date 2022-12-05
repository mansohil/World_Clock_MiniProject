package utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverSetup {
public static WebDriver driver;
public static String browser;
	
	public DriverSetup()
	{
		try {
			ReadPropertiesfile.readPropertiesFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	   //Setting up Browser
       public void setupBrowser() {
		
		browser = ReadPropertiesfile.getbrowserName();
		
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}else if(browser.equals("FF")){
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		
		driver.get(ReadPropertiesfile.getUrl());
		
	}

}
