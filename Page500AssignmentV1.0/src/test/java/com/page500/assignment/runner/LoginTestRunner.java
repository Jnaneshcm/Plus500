package com.page500.assignment.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.page500.core.base.Page500CoreBasePage;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(glue = { "com.page500.assignment.steps" }, features = {
		"src/test/resources/Features/Page500/" }, plugin = { "pretty", "json:target/cucumber.json",
				"junit:target/cucumber.xml", "html:target/cucumber-html-reports", "rerun:target/rerun.txt",
				"com.cucumber.listener.ExtentCucumberFormatter:target/LoginReport.html" }, monochrome = true, tags = {})
public class LoginTestRunner extends AbstractTestNGCucumberTests {
	Logger log = LoggerFactory.getLogger(this.getClass().getSimpleName());

	@Parameters(value = { "browser", "environment" })
	@BeforeSuite
	public void beforeSuite(@Optional("chrome") String browser, @Optional("YCOM_L1") String environment) {
		log.info("Browser :: " + browser);
		log.info("Environment :: " + environment);
		Page500CoreBasePage.driver = Page500CoreBasePage.getCurrentDriver(browser);
	}

	@AfterSuite
	public void afterSuite() {
		
		try {
			if (!(Page500CoreBasePage.driver == null)) {
				Page500CoreBasePage.driver.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
