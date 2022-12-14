package sampleTest;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
//import sun.util.calendar.LocalGregorianCalendar.Date;

//import SampleTest.SampleTest.MyLogoTest;

import util.ExcelUtil;
import util.LogInPageObject;

public class MyLogoTest {

	Method[] methods = MyLogoTest.class.getMethods();

	public WebDriver driver;

	@DataProvider(name = "readDataFromExcel")
	public Object[][] dataFromExcel(Method method) {
		Object[][] fetchDataFromExcel = null;

		try {

			String filePath = System.getProperty("user.dir") + "\\resources\\TestData.xlsx";
			System.out.println(filePath);
			fetchDataFromExcel = ExcelUtil.fetchDataFromExcel(filePath, "Login", method.getName());

		} catch (Exception dataFromExcel) {

			dataFromExcel.printStackTrace();

		}

		return fetchDataFromExcel;

	}

	@DataProvider(name = "singleTestCaseMultipleTimes")
	public Object[][] singleTestCaseMultipleTimes(Method method) {
		Object[][] fetchDataFromExcel = null;

		try {

			String filePath = System.getProperty("user.dir") + "\\resources\\TestData.xlsx";
			System.out.println(filePath);
			fetchDataFromExcel = ExcelUtil.singleTestCaseMultipleTimes(filePath, "Login", method.getName());

		} catch (Exception dataFromExcel) {
			dataFromExcel.printStackTrace();
		}

		return fetchDataFromExcel;

	}

	@BeforeMethod(groups= {"single", "Multiple"})
	@Parameters("browser")
	public void invokeBrowser(String browser) throws Exception{

		if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		}else if(browser.equalsIgnoreCase("firefox")){

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}else{

			throw new Exception("Browser is not correct");

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demoqa.com/");
	}


	@AfterMethod(groups= {"single", "Multiple"}) 
	public void afterTest() {

		driver.close();

	}

	@Test(dataProvider="singleTestCaseMultipleTimes", groups= {"single","Multiple"})
	public void newMemberRegistration(Map<String, String> data) {

		try {

			LogInPageObject obj = new LogInPageObject(driver);
			String username = data.get("Username");			
			String firstName = data.get("FirstName");
			String lastName = data.get("LastName");
			Thread.sleep(3000);
			String password = data.get("Password");

			String company = data.get("Company");
			String address = data.get("Address");
			String addressLine2 = data.get("AddressLine2");
			String city = data.get("City");

			String postalCode = data.get("ZipPostalCode");

			obj.register(username, firstName, lastName, password, company, address, addressLine2, city, postalCode);
			Thread.sleep(3000);

			System.out.println("Thread ID:"+Thread.currentThread().getId());

		}catch(Exception logIn) {

			logIn.printStackTrace();

		}

	}

	//@Test(groups= "single")
	public void switchToTabAndGetText() {

		try {

			LogInPageObject obj = new LogInPageObject(driver);
			obj.switchToTabAndGetTextObjectsPage();
			System.out.println("Thread ID:"+Thread.currentThread().getId());

		}catch(Exception switchToTabAndGetText) {

			switchToTabAndGetText.printStackTrace();

		}

	}

	//@Test(groups= "single")
	public void switchToIframe() {

		try{
			LogInPageObject obj = new LogInPageObject(driver);
			obj.switchToIframeObjectsPage();
			System.out.println("Thread ID:"+Thread.currentThread().getId());

		}catch(Exception switchToIframe) {

			switchToIframe.printStackTrace();

		}
	}

	//@Test(dataProvider="readDataFromExcel", groups= {"single"}) 
	public void signIn(Map<String, String> data) {

		try {

			String username = data.get("Username");
			String password = data.get("Password");

			LogInPageObject obj = new LogInPageObject(driver);
			obj.alreadyRegisteredUserLogIn(username, password);

		}catch(Exception signIn) {

			signIn.printStackTrace();

		}

	}
	//@Test(groups= {"single"})
	public void printedDress() {

		try {

			LogInPageObject obj = new LogInPageObject(driver);
			obj.Dresses();			



		}catch(Exception printedDress) {

			printedDress.printStackTrace();

		}

	}


}