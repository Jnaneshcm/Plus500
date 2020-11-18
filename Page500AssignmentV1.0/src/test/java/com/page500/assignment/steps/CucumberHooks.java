package com.page500.assignment.steps;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.Status;
import com.cucumber.listener.ExtentCucumberFormatter;
import com.page500.core.base.Page500CoreBasePage;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class CucumberHooks {
	public static Logger logger = LoggerFactory.getLogger(Page500CoreBasePage.class.getName());

	@Before
	public void setUp(Scenario s) throws Exception {
		logger.info("**********************************************");
		logger.info("Scenario :: '" + s.getName() + "' has started");
	}

	@After
	public void tearDown(Scenario s) throws Exception {
		if (s.isFailed()) {
			String base64OfScreenShot = ((TakesScreenshot) Page500CoreBasePage.driver).getScreenshotAs(OutputType.BASE64);
			ExtentCucumberFormatter.stepTestThreadLocal.get().log(Status.INFO,
					"<img height=\"195\" width=\"500\" src='data:image/png;charset=utf-8;base64," + base64OfScreenShot
							+ "'  onmouseover=\"bigImg(this)\" onmouseout=\"normalImg(this)\"> Mouse Hover Here For Screenshot </img>"
							+ "<script> function bigImg(x) { x.style.height = \"500px\"; x.style.width = \"750px\";}  function normalImg(x)"
							+ " { x.style.height = \"195px\";  x.style.width = \"195px\";}</script>");
			System.out.println(s.getName() + "scenario failed");

			final byte[] screenshot = ((TakesScreenshot) Page500CoreBasePage.driver).getScreenshotAs(OutputType.BYTES);
			s.embed(screenshot, "image/png"); // stick it in the report
			s.write("url: " + Page500CoreBasePage.driver.getCurrentUrl());
			s.write("name :" + s.getName());
			/*
			 * take the screenshot of the entire home page and save it to a png file
			 * Screenshot screenshot = new AShot().shootingStrategy(new
			 * ViewportPastingStrategy(1000)).takeScreenshot(driver);
			 * ImageIO.write(screenshot.getImage(), "PNG", new File("D:\\home.png"));
			 */

		} else {
			try {
				/*
				 * This program demonstrates how to capture a screenshot (full screen) as an
				 * image which will be saved into a file.
				 */
				Robot robot = new Robot();
				String format = "jpg";
				String fileName = "FullScreenshot." + format;

				Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
				BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
				// ImageIO.write(screenFullImage, format, new File(fileName));

				System.out.println("A full screenshot saved!");

				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(screenFullImage, "png", baos);
				baos.flush();
				byte[] imageInByte = baos.toByteArray();
				baos.close();
				s.embed(imageInByte, "image/png"); // stick it in the report
				s.write("url: " + Page500CoreBasePage.driver.getCurrentUrl());
				s.write("name :" + s.getName());

			} catch (AWTException | IOException ex) {
				System.err.println(ex);
			}
		}
		Page500CoreBasePage.resetAndWriteCaptureData();
	}

}
