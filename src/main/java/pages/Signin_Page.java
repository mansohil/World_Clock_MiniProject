package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import base.BaseClass;

public class Signin_Page extends BaseClass {
	static WebDriver driver;

	public Signin_Page(WebDriver driver) {

		PageFactory.initElements(driver, this);
	}

	// To enter email id in signin page
	public void inputEmail() {
		try {
			logger = report.createTest("Enter email id in sign page");
			Thread.sleep(15000);

			logger.pass("User entered Email id");
			clickElement("nextBtn");
		} catch (Exception e) {
			e.printStackTrace();
			logger.fail("User entered Email id");
		}
	}

	// To enter password in signin page
	public void inputPassword() throws InterruptedException {
		try {
			logger = report.createTest("Enter password in signin page");
			Thread.sleep(15000);

			logger.pass("User entered Password");
			clickElement("nextBtn");
		} catch (Exception e) {
			e.printStackTrace();
			logger.fail("User entered Password");
		}
	}

	// To Click on TextMessage to receive text Message
	public void textMessage(String textmsg) {
		logger = report.createTest("Click on TextMessage to receive text Message");
		getElement(textmsg).click();
		logger.pass("Text message clicked");
	}

	// Click next button after entered OTP
	public void verifyOTP(String verifybtn) throws InterruptedException {

		Thread.sleep(25000);
		getElement(verifybtn).click();
	}

	public void yesOrNo(String noOption) {
		try {
			logger = report.createTest("Click No option for stay signin");
			Thread.sleep(1000);
			getElement(noOption).click();
			logger.pass("User entered Password");
		} catch (Exception e) {
			e.printStackTrace();
			logger.fail("User entered Password");
		}
	}

}
