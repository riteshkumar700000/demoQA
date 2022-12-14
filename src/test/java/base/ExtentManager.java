package base;

import java.io.File;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	
	private static ExtentReports extent;
	public static ExtentReports createInstance() {
		String fileName = getReportName();
		String directory = System.getProperty("user.dir")+"/reports/";
		new File(directory).mkdirs();
		String path = directory+fileName;
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Sample Test Report");
		reporter.config().setDocumentTitle("Test Report Title");
		reporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Operating System", "Windows 10");
		extent.setSystemInfo("Tested By", "Mitesh Kumar");
		return extent;
	}

	public static String getReportName() {

		Date d = new Date();
		String fileName = "AutomationReport" +"_"+ d.toString().replace(":", "_").replace("", "_")+ ".html";
		return fileName;
	}

}
