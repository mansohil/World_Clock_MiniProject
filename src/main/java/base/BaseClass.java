package base;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import utilities.DriverSetup;
import utilities.ExtentReportManager;
import utilities.ReadPropertiesfile;

public class BaseClass extends DriverSetup {
	public static ReadPropertiesfile read = new ReadPropertiesfile();
	protected ExtentReports report = ExtentReportManager.getReportInstance();
	protected ExtentTest logger;

	public WebElement getElement(String xpathKey) {
		WebElement element = driver.findElement(By.xpath(read.getValue(xpathKey)));
		return element;
	}

	public void getElements(String xpathKey) {
		List<WebElement> element = driver.findElements(By.xpath(read.getValue(xpathKey)));
		System.out.println("The TimeZones Displayed in World Clock are: ");
		for (WebElement zone : element) {
			System.out.println("\n" + zone.getText());
		}
	}

	public void clickElement(String xpathKey) {
		try {
			getElement(xpathKey).click();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
