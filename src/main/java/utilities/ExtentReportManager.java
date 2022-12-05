package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {

	public static ExtentReports report;

	public static ExtentReports getReportInstance() {
		if (report == null) {
			String reportName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			ExtentSparkReporter htmlReporter = new ExtentSparkReporter(
					System.getProperty("user.dir") + "//test-output//" + reportName + ".html");
			report = new ExtentReports(); 
			report.attachReporter(htmlReporter);

			report.setSystemInfo("OS", "Windows 10");
			report.setSystemInfo("Environment", "QA");

			htmlReporter.config().setDocumentTitle("Be.Cognizant");
			htmlReporter.config().setReportName("TimeZone Analysis");
			htmlReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		}

		return report;
	}
}
