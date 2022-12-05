package testclass;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.BaseClass;
import pages.BeCognizant;
import pages.Signin_Page;

public class RunTest extends BaseClass {
	Signin_Page login= new Signin_Page(driver);
	BeCognizant cts = new BeCognizant(driver);
	
	@BeforeClass
	public void openBrowserAndLogin() {
		setupBrowser();
		login.inputEmail();
		
		try {
			login.inputPassword();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		login.textMessage("messageBtn");
		try {
			login.verifyOTP("verifymessage");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		login.yesOrNo("noOption");
	}
	
	@Test
	public void testWorldClock() {
	
		System.out.println(driver.getTitle());
		cts.userCapture("userName","userDesignation");
		cts.ScrollDown();
		cts.displayWorldClock("worldclock");
		cts.getElements("timezones");
		//test scenario 1 before slider movement
		cts.timeCheck("NJdate", "NJtime", "NJ", "America/New_York");
		cts.timeCheck("londondate", "londontime", "london", "GMT+1");
		cts.timeCheck("IndiaDate", "IndiaTime", "India", "Asia/Kolkata");
		
		try {
			cts.moveSlider("Slider");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("");
		System.out.println("");
		System.out.println("The movement of slider is done and results are:-");
		System.out.println("");
		System.out.println("");
		
		//tet scenario 2 after slider movement
		cts.timeCheck("NJdate", "NJtime", "NJ", "America/New_York", "Slider");
		cts.timeCheck("londondate", "londontime", "london", "GMT+1", "Slider");
		cts.timeCheck("IndiaDate", "IndiaTime", "India", "Asia/Kolkata", "Slider");
	}
	
	@AfterClass
	public void teardown() {
		driver.quit();
		report.flush();
	}
	
	
	
	
	
	
	
	
	
	

}
