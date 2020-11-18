package com.page500.assignment.steps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;


import com.page500.core.base.Page500CoreBasePage;
import com.page500.core.util.ObjectRepository;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StandardSteps extends Page500CoreBasePage {
	public static String logicalPageName;

	@Given("^User is on \"([^\"]*)\"$")
	public void user_is_on(String pageName) throws Throwable {
		switch (pageName) {
		case "LoginPage":
			loadInitialURL();
			logicalPageName = "LoginPage";
			break;
		case "HomePage":
			logicalPageName = "HomePage";
			break;
		case "DashboardPage":
			logicalPageName = "DashboardPage";
			break;
		case "LinkAccountPopup":
			logicalPageName = "LinkAccountPopup";
			break;
		default:
			break;
		}
	}

	
	@When("^User enter the value in the text box \"([^\"]*)\" \"([^\"]*)\"$")
	public void user_enter_the_value_in_the_text_box(String arg1, String arg2) throws Throwable {
		enterIntoTextBox(logicalPageName + "." + arg1, arg2);
	}
	
	
	@When("^User clicks on the button \"([^\"]*)\"$")
	public void user_clicks_on_the_Button(String logicalName) throws Throwable {
		click(logicalPageName + "." + logicalName);
	}

	

	@Then("^Verify the text value of \"([^\"]*)\" \"([^\"]*)\"$")
	public void verify_value_of(String logicalName, String value) throws Throwable {
		verifyTextValue(logicalPageName + "." + logicalName, value);

	}
	
	@Then("^User Verifies the page title \"([^\"]*)\"$")
	public void user_Verifies_the_page_title(String ExpectedTitle) throws Throwable {
		String ActualTitle = driver.getTitle();
	logger.info("Expected Value : " + ExpectedTitle + " ---- "
				+ "Actual Value : " + ActualTitle);
		Assert.assertEquals(ExpectedTitle, ActualTitle);
	}
	
	@Then("^User Press Enter \"([^\"]*)\"$")
	public void user_Press_Enter(String logicalName) throws Throwable {
		pressEnter(logicalPageName + "." + logicalName);
	}

	
	/*
	 * @Then("^User Press Enter \"([^\"]*)\"$") public void user_Press_Enter(String
	 * logicalName) throws Throwable { pressEnter(logicalName); }
	 */

	@Then("^User Validate \"([^\"]*)\" Price with the Threshold \"([^\"]*)\" \"([^\"]*)\"$")
	public void user_Validate_Price_with_the_Threshold(String logicalName, String min, String max) throws Throwable {
		validateThresholdValue(logicalPageName + "." +logicalName,min,max);
	}

	
}
