package com.page500.core.util;

import java.util.HashMap;

import org.openqa.selenium.By;

public class ObjectRepository {
	static Config config = Config.getInstance();

	private static ElementLocatorBean getElemenLocatorBean(String pageName, String label) throws Exception {
		PagesBean pagesBean = config.getPagesBean();

		if (pagesBean == null) {
			throw new Exception("PagesBean is null !");
		}
		PageBean page = pagesBean.getPages().get(pageName);
		if (page == null) {
			throw new Exception("No page found with page name::" + pageName + " in Pages.json");
		}

		HashMap<String, ElementLocatorBean> elements = page.getElements();

		if (elements == null || elements.size() == 0) {
			throw new Exception("Page :::" + pageName + " has no elements !!");
		}

		ElementLocatorBean elementLocatorBean = elements.get(label);
		if (elementLocatorBean == null) {
			throw new Exception("No element with name as::" + label + " found in page::" + pageName);
		}
		return elementLocatorBean;
	}

	public static By getLocator(String pageName, String labelName) {
		try {
			ElementLocatorBean e = getElemenLocatorBean(pageName, labelName);
			String locatorType = e.getLocatorType();
			String value = e.getValue();
			switch (locatorType.toLowerCase()) {
			case "id":
				return By.id(value);
			case "class":
			case "classname":
				return By.className(value);
			case "tagname":
				return By.tagName(value);
			case "name":
				return By.name(value);
			case "linktext":
				return By.linkText(value);
			case "partiallinktext":
				return By.partialLinkText(value);
			case "css":
			case "cssselector":
				return By.cssSelector(value);
			case "xpath":
				return By.xpath(value);
			default:
				return By.id("Unable to find locator with Pagename :: " + pageName + " and Label :: " + labelName);
			}
		} catch (Exception e) {
			return By.id("Unable to find locator with Pagename :: " + pageName + " and Label :: " + labelName);
		}
	}
	
	public static String getExpression(String pageName, String labelName) {
		try {
			ElementLocatorBean e = getElemenLocatorBean(pageName, labelName);
			return e.getValue();
		} catch (Exception e) {
			return null;
		}
	}

}
