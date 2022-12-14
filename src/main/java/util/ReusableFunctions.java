package util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReusableFunctions {

	WebDriver driver;

	public JavascriptExecutor scrollToElement(WebElement element, WebDriver driver) {

		this.driver = driver;
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();", element);

		return js;

	}

	public void screenshot(WebDriver driver) {

		try {
			this.driver = driver;
			TakesScreenshot scrShot = ((TakesScreenshot)driver);

			File srcFile = scrShot.getScreenshotAs(OutputType.FILE);

			String fileWithPath = System.getProperty("user.dir") + "\\resources\\Screenshots\\test.png";

			File DestFile = new File(fileWithPath);
			FileUtils.copyFile(srcFile, DestFile);
			
		}catch(Exception screenshot) {

			screenshot.printStackTrace();

		}
	}

}
