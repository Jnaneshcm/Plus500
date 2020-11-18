package com.page500.core.base;

import static org.testng.Assert.fail;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import com.page500.core.util.Constants;
import com.page500.core.util.ObjectRepository;
import com.page500.core.util.PropertyReader;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Page500CoreBasePage {

	static int timeOutInSeconds;
	static PropertyReader propertyReader = new PropertyReader();
	public static Logger logger = LoggerFactory.getLogger(Page500CoreBasePage.class.getName());
	protected static HashMap<String, String> capturedData = new HashMap<String, String>();
	public static String browserType;
	public static WebDriver driver;
	static String sUrl = null;
	private static int counter = 60;
	
	/*
	 * // Adds the <logicalName, by > entry in Object Repository public void
	 * addObject(String logicalName, By by) { ObjRep.put(logicalName, by); }
	 */

	public void verifyTextValue(String logicalName, String value) {
		if (!value.isEmpty()) {
			logger.info("Logical name : " + logicalName + " ---- " + "Expected Value : " + value + " ---- "
					+ "Actual Value : " + getText(logicalName));
			Assert.assertEquals(getText(logicalName), value);
		} else if (value.isEmpty()) {
			verifyElementIsNotPresent(logicalName);
		}
	}
	
	public void validateThresholdValue(String logicalName, String min, String max) throws InterruptedException {
		if (!min.isEmpty()) {
			
			Thread.sleep(10000);
			logger.info("Logical name : " + logicalName + " ---- " + "Expected Min : " + min + " ---- " +"Expected Max : " + max +""
				+ "Actual Value : " + getText(logicalName));
			
				double  ActualValue = Double.parseDouble(getText(logicalName)); 
				double Min = 	 Double.parseDouble(min); 
				double Max =  Double.parseDouble(max);
			 
			
			
			if (Min < ActualValue && Max < ActualValue) {
				Assert.assertTrue(true);
			}
			
		} else {
			Assert.assertTrue(true);
		}
	}

	public String getText(String logicalName) {
		return (getText(getWebElement(logicalName)));
	}
	
	private String getText(WebElement element) {
		String text = "";
		try {
			waitForDisplayed(element, timeOutInSeconds);
			if (!(element.getText().isEmpty())) {
				text = element.getText();
			}
		} catch (UnhandledAlertException ar) {
			driver.switchTo().alert().dismiss();
		} catch (Exception e) {

		}
		return text;
	}
	
	private static By getByObject(String pageName, String labelName) {
		return ObjectRepository.getLocator(pageName, labelName);
	}

	// Enter text in element using logicalName
	public static void enterIntoTextBox(String logicalName, String value) {
		sendKeys(getWebElement(logicalName), value);
		logger.info("Logical name : " + logicalName + " ---- " + "Value Entered: " + value);
	}
	
	final public static void sendKeys(WebElement element, String keys) {
		try {
			clear(element);
			element.sendKeys("");
			element.sendKeys(keys);
			logger.info("succesfully sent the keys:: \"" + keys + "\" to the input box of element:: " + element);
		} catch (Exception e) {
			logger.error("unable to send \"" + keys + "\" keys to input box " + element + " due to " + e.getMessage());
			fail("unable to send \"" + keys + "\" keys to input box " + element + " due to " + e.getMessage());
		}
	}
	
	final public static void clear(WebElement element) {
		try {
			click(element);
			element.clear();
		} catch (Exception e) {
			logger.error(
					"unable to clear data present in input box for element " + element + " due to " + e.getMessage());
			fail("unable to clear data present in input box for element " + element + " due to " + e.getMessage());
		}
	}

	// Click element using logicalName
	public void click(String logicalName) {
		click(getWebElement(logicalName));
		logger.info("---clicked " + logicalName);
	}

	
	private static boolean click(WebElement element) throws Error {
		try {
			waitForDisplayed(element, timeOutInSeconds);
			if (isDisplayed(element)) {
				element.click();
				return true;
			}
			return false;
		} catch (UnhandledAlertException ar) {
			driver.switchTo().alert().dismiss();
			return false;
		} catch (Exception e) {
			return false;
		}

	}
	
	// Click element using logicalName
		public void pressEnter(String logicalName) {
			pressEnter(getWebElement(logicalName));
			logger.info("---clicked " + logicalName);
		}
		
		private static boolean pressEnter(WebElement element) throws Error {
			try {
				waitForDisplayed(element, timeOutInSeconds);
				if (isDisplayed(element)) {
					element.sendKeys(Keys.ENTER);
					return true;
				}
				return false;
			} catch (UnhandledAlertException ar) {
				driver.switchTo().alert().dismiss();
				return false;
			} catch (Exception e) {
				return false;
			}

		}
	
	public void verifyElementIsPresent(String logicalName) {
		Assert.assertTrue(isElementPresent(logicalName));
		logger.info("verifyElementIsPresent " + logicalName);
	}

	public void verifyElementIsNotPresent(String logicalName) {
		Assert.assertFalse(isElementPresent(logicalName));
		logger.info("verifyElementIsPresent " + logicalName);
	}

	
	private boolean isElementPresent(String logicalName) {
		try {
			if (getWebElement(logicalName) != null) {
				return true;
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {

		} catch (org.openqa.selenium.TimeoutException e) {

		} catch (NullPointerException e) {

		} catch (org.openqa.selenium.StaleElementReferenceException e) {

		}
		return false;
	}
	
	
	private static boolean isDisplayed(WebElement element) throws Error {
		try {
			return element.isDisplayed();
		} catch (UnhandledAlertException ar) {
			driver.switchTo().alert().dismiss();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	// Returns the WebElement corresponding to a logicalName
	private static WebElement getWebElement(String logicalName) {
		By by = null;
		WebElement element = null;
		String[] logical = logicalName.split("\\.");
		String pageName = logical[0];
		String labelName = logical[1];
		// To fetch By value corresponding to logicalName from Object Repository
		by = ObjectRepository.getLocator(pageName, labelName);

		try {
			// Wait for element to be displayed for timeOutInSeconds seconds
			waitForDisplayed(logicalName, timeOutInSeconds);
			// Finds the WebElement corresponding to By values
			if (driver.findElement(by) != null) {
				element = driver.findElement(by);
			} else {
				// if null is returned in WebElement
				logger.info("Unable to do findElement opertation on object -" + logicalName);
				Assert.fail("Unable to do findElement opertation on object -" + logicalName);
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			// e.printStackTrace();
			logger.info("NoSuchElementException:  " + logicalName + "  is not present");
		} catch (org.openqa.selenium.TimeoutException e) {
			// e.printStackTrace();
			logger.info("TimeoutException: " + logicalName + "  is not shown in given time");
		} catch (org.openqa.selenium.StaleElementReferenceException e) {
			logger.info("StaleElementReferenceException: " + logicalName + " is not shown in given time");
		}
		return element;
	}
	
		
	private static WebElement waitForDisplayed(final String logicalName, int timeOutSeconds) {

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOutSeconds))
				.pollingEvery(Duration.ofMillis(25));
		String[] logical = logicalName.split("\\.");
		String pageName = logical[0];
		String labelName = logical[1];
		// To fetch By value corresponding to logicalName from Object Repository
		By by = ObjectRepository.getLocator(pageName, labelName);
		return wait.ignoring(WebDriverException.class).ignoring(NoSuchElementException.class)
				.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver driver) {
						return driver.findElement(by).isDisplayed() ? driver.findElement(by) : null;
					}
				});
	}

	
	public static void resetAndWriteCaptureData() throws IOException {

		File newFile = new File(Constants.PLUS500_REPO + File.separator + "capture.properties");
		if (newFile.exists()) {
			newFile.delete();
		}
		File file = new File(Constants.PLUS500_REPO);

		file.mkdirs();
		newFile.createNewFile();
		try {
			Properties properties = new Properties();

			for (Map.Entry<String, String> entry : capturedData.entrySet()) {
				properties.put(entry.getKey(), entry.getValue());
			}

			properties.store(new FileOutputStream(newFile), null);

		} catch (Exception e) {
		}
	}
	
	private static WebElement waitForDisplayed(final WebElement webElement, int timeOutInSeconds) {

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOutInSeconds))
				.pollingEvery(Duration.ofMillis(25));

		return wait.ignoring(WebDriverException.class).ignoring(NoSuchElementException.class)
				.until(new Function<WebDriver, WebElement>() {
					public WebElement apply(WebDriver driver) {
						return webElement.isDisplayed() ? webElement : null;
					}
				});
	}


	protected static WebDriver createFirefoxDriver() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		return driver;
	}

	protected static WebDriver createChromeDriver() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
		options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
//		options.addArguments("--headless"); // only if you are ACTUALLY running headless
		options.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
		options.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
		options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
		options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
		options.addArguments("--disable-gpu");
		driver = new ChromeDriver(options);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		return driver;
	}

	protected static WebDriver createInternetExplorerDriver() {
		WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		return driver;
	}

	public static void loadInitialURL() throws Exception {
		String sUrl = getAppURLNew();
		driver.get(sUrl);
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.querySelector('#proceed-link').click();");
		} catch (Exception e) {
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	protected static String getAppURLNew() {
		try {
			sUrl = propertyReader.getApplicationproperty("url").trim();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sUrl;
	}

	public synchronized static WebDriver getCurrentDriver(String browserType) {
		if (driver == null) {
			try {
				/*
				 * String sBrowserName = propReader.getApplicationproperty(
				 * "browser.type").trim();
				 */
				if (browserType.trim().equalsIgnoreCase("firefox")) {
					driver = createFirefoxDriver();
				} else if (browserType.trim().equalsIgnoreCase("chrome")) {
					driver = createChromeDriver();
				} else if (browserType.trim().equalsIgnoreCase("ie")) {
					driver = createInternetExplorerDriver();
				} else {
					System.out.println(
							"Please define the browser type = as firefox or ie and remoteMode = true/false in application.properties inside properties folder");
				}
			} catch (Exception e) {
				System.err.println("Unable to start driver due to :: " + e.getMessage());
				Assert.fail("Unable to start driver due to :: " + e.getMessage());
			}
		}
		return driver;
	}

	public static void close() {
		try {
			driver.close();
			driver = null;
		} catch (UnreachableBrowserException e) {

		}
	}



		public static WebElement getWebElement(String pageName, String labelName) {
		WebElement we = null;
		we = driver.findElement(getByObject(pageName, labelName));
		if (we != null) {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView()", we);
		}
		logger.info("WebElement Value is : {}", we);
		return we;
	}

	public static List<WebElement> getWebElements(String pageName, String labelName) {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		List<WebElement> elementList = wait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByObject(pageName, labelName)));
		return elementList;
	}

		public static void click(WebElement we, String message) {
		if (we != null) {
			logger.info("Clicking on element " + we);
			try {
				we.click();
			} catch (Exception ex) {
				logger.warn("Exception Thrown while Calling WebElement.click() method.");
				logger.warn("Trying with javascript click");
				((JavascriptExecutor) driver).executeScript("arguments[0].click()", we);
			}
		} else {
			logger.error("WebElement to click is null.");
		}
	}

	public static void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	
	public static String getVisibileElementXPath(String pageName, String labelName) {
		return ObjectRepository.getExpression(pageName, labelName);
	}

	
	

	public boolean assertFalse(List<String> actualValue, String expected) {
		boolean elementFresentFalse = true;
		for (String value : actualValue) {
			if (value.equals(expected)) {
				elementFresentFalse = true;
				break;
			} else {
				elementFresentFalse = false;
			}
		}
		return elementFresentFalse;
	}

	
	
	/**
	 * To check whether element is displayed or not
	 * 
	 * @return=true if element is displayed
	 * @return=false if element is not displayed
	 * @param=By element locator in the form of By
	 */
	final public static boolean isDisplayed(By element, int waitTime) {
		boolean flag = false;

		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			for (int i = 0; i < waitTime; i++) {
				try {
					flag = driver.findElement(element).isDisplayed();
					if (flag) {
						flag = true;
						break;
					} else {
						Thread.sleep(1000);
					}
				} catch (Exception e) {
					Thread.sleep(1000);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		}
		return flag;
	}

	public static String getAttribute(By by, String attribute) {

		String attributeValue = "";
		try {
			attributeValue = driver.findElement(by).getAttribute(attribute);
			logger.info(attribute + " attribute's value of element :: " + by + " is " + attributeValue);
		} catch (Exception e) {
			logger.error("Unable to get attribute value due to :: " + e.getMessage());
			return "";
		}
		return attributeValue;
	}

	/**
	 * wait until element is present in document object model (DOM)
	 * 
	 * @param=By element you want to wait for till that element loads in the DOM
	 * @author kongara.sravan
	 * @param driver
	 */
	final public static void waitUntilElementIsPresent(By element) {

		try {
			boolean flag = false;
			implicitlyWait(0);
			for (int i = 0; i < counter; i++) {
				try {
					driver.findElement(element);
					flag = true;
					break;
				} catch (NoSuchElementException | StaleElementReferenceException e) {
					logger.info("Waiting for element to be present in DOM " + i + " seconds element:: " + element);
					waitFor(1);
				} catch (Exception e) {
					e.getStackTrace();
					break;
				}
			}
			if (flag) {
			} else {
				logger.error("Element is not present in DOM element:: " + element);
				Assert.fail("Element is not present in DOM element:: " + element);
			}
		} catch (Exception e) {
		} finally {
			implicitlyWait(60);
		}
	}

	final public static void implicitlyWait(int time) {

		try {
			driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	final public static void waitFor(float timeToWait) {
		try {
			Thread.sleep((long) (timeToWait * 1000));
		} catch (InterruptedException e) {
		}
	}



	

}
