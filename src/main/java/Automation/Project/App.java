package Automation.Project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Automation.Project.Pages.*;

public class App {
	WebDriver driver;

	Solutions solutionsPage;

	Home homePage;

	@Before
	public void setup() {
		System.setProperty("webdriver.gecko.driver",
				"..\\SeleniumFiles\\Selenium\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://consulting.qa.com");
	}
	@Test
	public void testHomeToSolutions() {

		homePage = new Home(driver);
		String homePageTitle = homePage.getTitle();
		assertEquals(homePageTitle, "QA Consulting");
		homePage.clickSolutionsLink();

		solutionsPage = new Solutions(driver);
		String solutionsHeader = solutionsPage.getHeader();
		String solutionsBody = solutionsPage.getBody();
		String solutionsTitle = solutionsPage.getTitle();

		assertTrue(solutionsHeader.contains("Solutions"));
		assertTrue(solutionsBody.contains("Accelerate"));
		assertTrue(solutionsTitle.contains("Solutions - QA Consulting"));
	}

	@After
	public void tearDown() {
		try {
			driver.close();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}
}