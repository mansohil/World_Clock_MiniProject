package pages;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.BaseClass;
import utilities.ExtentReportManager;
import utilities.ReadExcel;
import utilities.ScreenShots;

public class BeCognizant extends BaseClass {

	public static long minutesToadd;
	ReadExcel excel = new ReadExcel(driver);

	ScreenShots s=new ScreenShots(driver);
	public BeCognizant(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Printing User name and designation
	public void userCapture(String userName, String userDesignation)

	{
		String userNam = getElement(userName).getText();
		String userDesig = getElement(userDesignation).getText();
		System.out.println("Employee Name is: " + userNam);
		System.out.println("Employee Designation is: " + userDesig);
	}

	public void ScrollDown() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	// Check whether worldclock is displayed
	public void displayWorldClock(String worldclock) {
		logger=report.createTest("world clock is display");
		WebElement WorldClock = getElement(worldclock);
		if (WorldClock.isDisplayed()) {
			System.out.println("\nWorld Clock Section is Displayed");
			System.out.println("");
			logger.pass("World clock section is displayed");
		}
		s.captureScreenShot();
		
		
	}

	// Check how many time zones are displayed
	public void timeZones(String timezones) {
		logger=report.createTest("Time zones are displayed");
		getElements(timezones);
		logger.pass("Found three time zones in world clock section with local time");
	}

	// Drag and drop Slider
	public void moveSlider(String sliderXpathKey) throws InterruptedException {
		logger=report.createTest("Drag and drop Slider");
		int i = excel.getdata(0, 1, 0);
		WebElement slider = getElement(sliderXpathKey);
		Actions action = new Actions(driver);
		action.clickAndHold(slider).moveByOffset(i, 0).release().build().perform();
		
		logger.pass("Slider is moved successfully");
		Thread.sleep(3000);
		s.captureScreenShot();
	}

	// time formatter
	public String getText(String dateXpathKey, String timeXpathKey, String place) {
		String date = driver.findElement(By.xpath(read.getValue(dateXpathKey))).getText();
		String time = driver.findElement(By.xpath(read.getValue(timeXpathKey))).getText();
		String country = driver.findElement(By.xpath(read.getValue(place))).getText();

		String timeformat = date + " " + time.replace("\n", " ");

		System.out
				.println("*****************************Date time for " + place + "**********************************");
		System.out.println("Date and Time in World Clock Section of " + country + " was " + timeformat);

		return timeformat;
	}

	public String zonetime(String id) {
		// LocalDate Time
		
		LocalDateTime ldt = LocalDateTime.now();
		ZoneId india = ZoneId.of("Asia/Kolkata");
		ZonedDateTime zone1 = ZonedDateTime.of(ldt, india);
		ZoneId zone = ZoneId.of(id);
		ZonedDateTime zone3 = zone1.withZoneSameInstant(zone);

		String date = DateTimeFormatter.ofPattern("dd MMMM h:mm a").format(zone3);

		return date;
	}

	// Checking with base time
	public void timeCheck(String dateXpathKey, String timeXpathKey, String place, String id) {
		logger=report.createTest("Compare local time of "+place+" with world clock section time");
		if (getText(dateXpathKey, timeXpathKey, place).equalsIgnoreCase(zonetime(id))) {
			System.out.println("Calculated Date Time of " + id + " is : " + zonetime(id));
			System.out.println("Date Time displayed in the WorldClock Section for  " + place + " is true");
			logger.pass("Zone time in world clock section and local time of "+place+" are equal");
		} else {
			System.out.println("Calculated Date Time of " + id + " is : " + zonetime(id));
			System.out.println("difference found in Date Time displayed in the WorldClock Section for " + place);
			logger.fail("Zone time in world clock section and local time of "+place+" are not equal");
		}
	}

	// local date and time of zones after adding minutes/hours
	public String zonetimeForSliderMove(String id, String Slider) {

		// LocalDate Time
		LocalDateTime ldt = LocalDateTime.now();
		ZoneId india = ZoneId.of("Asia/Kolkata");
		ZonedDateTime zone1 = ZonedDateTime.of(ldt, india);

		ZoneId zone = ZoneId.of(id);
		ZonedDateTime zone3 = zone1.withZoneSameInstant(zone);

		String sliderMinute = driver.findElement(By.xpath(read.getValue(Slider))).getAttribute("aria-valuenow");
		minutesToadd = Long.parseLong(sliderMinute);
		ZonedDateTime plustime = zone3.plusMinutes(minutesToadd);

		String date = DateTimeFormatter.ofPattern("dd MMMM h:mm a").format(plustime);

		return date;
	}

	public void timeCheck(String dateXpathKey, String timeXpathKey, String place, String id, String Slider) {
		logger=report.createTest("Compare local time of "+place+" with world clock section time after slider movement");
		if (getText(dateXpathKey, timeXpathKey, place).equalsIgnoreCase(zonetimeForSliderMove(id, Slider))) {
			System.out.println("Calculated Date Time after adding " + minutesToadd + " minutes to local time of " + id
					+ " is " + zonetimeForSliderMove(id, Slider));
			System.out.println("Date Time displayed in the WorldClock Section for  " + place + " is true");
			logger.pass("After slider movement zone time in world clock section and local time of "+place+" are equal");
		} else {
			System.out.println("Calculated Date Time after adding " + minutesToadd + " minutes to local time of " + id
					+ " is " + zonetimeForSliderMove(id, Slider));
			System.out.println("difference found in Date Time displayed in the WorldClock Section for " + place);
			logger.fail("After slider movement zone time in world clock section and local time of "+place+" are not equal");
		}
	}

}
