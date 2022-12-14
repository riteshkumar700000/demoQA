package util;

import java.io.File;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import util.LogInPageObject;

public class LogInPageObject  {

	ReusableFunctions rf = new ReusableFunctions();

	WebDriver driver;

	public static final String bookStoreApplication = "//*[text()='Book Store Application']";
	public static final String login = "//span[text()='Login']";
	public static final String bookStoreAPI = "//*[text()='Book Store API']";
	public static final String emailXpath = "//*[@id='userName']";
	public static final String passwordXpath = "//*[@id='password']";
	public static final String newUser = "//*[@id='newUser']";
	public static final String profile = "//span[text()='Profile']";
	public static final String firstNameXpath = "//*[@id='firstname']";
	public static final String lastNameXpath = "//*[@id='lastname']";
	public static final String imnotarobot = "//div[@class='recaptcha-checkbox-borderAnimation']";
	public static final String register = "//*[@id='register']";

	//switchToTabAndGetTextObjectsPage
	public static final String alertsFramesAndWindows = "(//div[@class='avatar mx-auto white'])[3]";
	public static final String browserWindows = "//span[text()='Browser Windows']";
	public static final String buttonNewWindow = "//button[text()='New Window']";
	public static final String newWindow = "//*[text()='This is a sample page']";
	public static final String iframe = "//span[text()='Frames']";

	public static final String frame1Text = "//h1[text()='This is a sample page']";
	public static final String address1Xpath = "//*[@id='address1']";
	public static final String address2Xpath = "//*[@id='address2']";
	public static final String cityXpath = "//*[@id='city']";
	public static final String state = "id_state";
	public static final String postalCodeXpath = "//*[@id='postcode']";
	public static final String country = "id_country";
	public static final String homePhone = "//*[@id='phone']";
	public static final String mobile = "//*[@id='phone_mobile']";
	public static final String assign = "//*[@id='alias']";
	public static final String btnregister = "//*[@id='submitAccount']/span";

	//Already Registered

	public static final String btnSignInXpath = "//*[@id='SubmitLogin']/span";
	public static final String tshirtsLinkXpath = "//*[@id='block_top_menu']/ul/li[3]/a";
	//Dresses

	public static final String lnkDresses = "//*[@id='block_top_menu']/ul/li[2]/a";
	public static final String eveDresses = "//*[@id='subcategories']/ul/li[2]/div[1]/a/img";
	public static final String printedDressesHover = "//*[@id='center_column']/ul/li/div/div[1]/div/a[1]/img";

	public LogInPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void register(String username, String firstName, String lastname, String password, String company, String address, String addressLine2, String city, 
			String postalCode) {

		try {

			WebElement element2 = driver.findElement(By.xpath(bookStoreApplication));
			/*
			 * JavascriptExecutor js = (JavascriptExecutor)driver;
			 * js.executeScript("arguments[0].scrollIntoView();",element);
			 */
			rf.scrollToElement(element2, driver);
			driver.findElement(By.xpath(bookStoreApplication)).click();
			WebDriverWait wait = new WebDriverWait(driver,10);

			boolean flag = false;
			if(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(bookStoreAPI)))!=null) {

				flag = true;

				System.out.println("Element is present");

			}


			WebElement element3 = driver.findElement(By.xpath(bookStoreAPI));
			rf.scrollToElement(element3, driver);
			
			Thread.sleep(3000);
			driver.findElement(By.xpath(login)).click();

			WebElement element4 = driver.findElement(By.xpath(newUser));
			rf.scrollToElement(element4, driver);
			driver.findElement(By.xpath(newUser)).click();
			driver.findElement(By.xpath(LogInPageObject.firstNameXpath)).sendKeys(firstName);
			driver.findElement(By.xpath(LogInPageObject.lastNameXpath)).sendKeys(lastname);
			driver.findElement(By.xpath(emailXpath)).sendKeys(username );
			
			driver.findElement(By.xpath(LogInPageObject.passwordXpath)).sendKeys(password);
			
			//WebElement element5 = driver.findElement(By.xpath(profile));
			Wait<WebDriver> waitFluent =  new FluentWait<WebDriver> (driver)
							.withTimeout(30, TimeUnit.SECONDS)
							.pollingEvery(5, TimeUnit.SECONDS)
							.ignoring(NoSuchElementException.class);
			
			WebElement fluentElement = waitFluent.until(new Function<WebDriver, WebElement>(){
				
				public WebElement apply(WebDriver driver) {
					
					return driver.findElement(By.xpath(profile));
				}
				
			});

			WebElement element5 = driver.findElement(By.xpath(profile));
			rf.scrollToElement(element5, driver);
			
			SoftAssert softAssert = new SoftAssert();
			System.out.println(driver.getTitle().trim());
			softAssert.assertEquals(driver.getTitle().trim(), "Tools", "Title is not matching");
			//softAssert.assertNull((driver.getCurrentUrl()),"Null object found\n");
			softAssert.assertAll();

			/*
			 * WebDriverWait wait2 = new WebDriverWait(driver,30); WebElement element6 =
			 * wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
			 * imnotarobot))); driver.findElement(By.xpath(imnotarobot)).click();
			 * driver.findElement(By.xpath(register)).click();
			 */

		}catch(ElementNotVisibleException notVisible) {

			notVisible.printStackTrace();

		}catch(Exception logIn) {

			logIn.printStackTrace();

		}

	}


	public void switchToTabAndGetTextObjectsPage() {

		try {
			WebElement element = driver.findElement(By.xpath(bookStoreApplication));
			rf.scrollToElement(element, driver);
			//rf.screenshot(driver);
			
			driver.findElement(By.xpath(alertsFramesAndWindows)).click();
			driver.findElement(By.xpath(browserWindows)).click();

			String parent  = driver.getWindowHandle();
			driver.findElement(By.xpath(buttonNewWindow)).click();

			Set<String> handles = driver.getWindowHandles();

			Iterator<String> I1 = handles.iterator();

			while(I1.hasNext()){

				String child_window = I1.next();

				if(!parent.equals(child_window)) {

					driver.switchTo().window(child_window);

					System.out.println(driver.switchTo().window(child_window).getTitle());
					System.out.println(driver.findElement(By.xpath(newWindow)).getText());

				}
			}

			driver.switchTo().window(parent);

		}catch(Exception switchToTabAndGetTextObjectsPage) {

			switchToTabAndGetTextObjectsPage.printStackTrace();

		}

	}

	public void switchToIframeObjectsPage() {

		try{
			
			WebElement element = driver.findElement(By.xpath(bookStoreApplication));			
			rf.scrollToElement(element, driver);
			rf.screenshot(driver);
			
			driver.findElement(By.xpath(alertsFramesAndWindows)).click();
			driver.findElement(By.xpath(iframe)).click();
			driver.switchTo().frame("frame1");
			String text = driver.findElement(By.xpath(frame1Text)).getText();
			System.out.println(text);
			driver.switchTo().parentFrame();

		}catch(Exception switchToIframeObjectsPage) {

			switchToIframeObjectsPage.printStackTrace();

		}
	}

	public void alreadyRegisteredUserLogIn(String username, String password) {

		try {


			driver.findElement(By.xpath(passwordXpath)).sendKeys(password);
			driver.findElement(By.xpath(btnSignInXpath)).click();
			driver.findElement(By.xpath(tshirtsLinkXpath)).click();

		}catch(Exception alreadyRegisteredUserLogIn) {

			alreadyRegisteredUserLogIn.printStackTrace();

		}

	}

	public void Dresses() {

		try {

			driver.findElement(By.xpath(lnkDresses)).click();
			driver.findElement(By.xpath(eveDresses)).click();
			WebElement element = driver.findElement(By.xpath(printedDressesHover));
			JavascriptExecutor js = (JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView();", element);

			Actions actions = new Actions(driver);

			actions.moveToElement(element).perform();
			Thread.sleep(4000);

			js.executeScript("arguments[0].click();",element);

		}catch(Exception Dresses) {

			Dresses.printStackTrace();

		}

	}

}
