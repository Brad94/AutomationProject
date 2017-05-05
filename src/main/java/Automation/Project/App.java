package Automation.Project;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Automation.Project.Pages.*;

public class App {
	WebDriver driver;

	Login loginPage;

	Home homePage;
	
	Contact contactPage;

	@Before
	public void setup() {
		//System.setProperty("webdriver.gecko.driver",
		//		"..\\SeleniumFiles\\Selenium\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver",
				"..\\SeleniumFiles\\Selenium\\chromedriver.exe");
		//driver = new FirefoxDriver();
		driver = new ChromeDriver();
		driver.get("http://automationpractice.com/index.php");
	}
	
	@Before
	public static String getPass() throws IOException {

		String fileName = "..Eclipse\\Project\\pass.txt";
		String passFile = "";
		try{
			ReadFile file = new ReadFile(fileName);
			String[] arylines = file.OpenFile();				
			passFile = arylines[0];
			
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}	
	return passFile;
}
	@Test
	public void testHomeToProducts() {
		homePage = new Home(driver);
		homePage.clickTshirts();
	}
	
	@Test
	public void testHomeToLoginandLogout() {
		
		String emailInputText = loginPage.getEmailTextBoxText();
		String passwordInputText = loginPage.getPasswordTextBoxText();
		
		homePage = new Home(driver);
		homePage = new Home(driver);
			
		String homePageTitle = homePage.getTitle();
		assertEquals(homePageTitle, "My Store");
		homePage.clickLogin();
		
		loginPage = new Login(driver);
		
		loginPage.getEmailInput("Hi");
		assertTrue(emailInputText.contains("hi"));
		
		try {
			String pass = getPass();
			
			loginPage.getPasswordInput(pass);
			assertTrue(passwordInputText.contains(pass));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		loginPage.submitLogin();
		
		loginPage.submitLogout();
		
//		solutionsPage = new Solutions(driver);
//		String solutionsHeader = solutionsPage.getHeader();
//		String solutionsBody = solutionsPage.getBody();
		//String solutionsTitle = solutionsPage.getTitle();
//
//		assertTrue(solutionsHeader.contains("Solutions"));
//		assertTrue(solutionsBody.contains("Accelerate"));
		//(solutionsTitle.contains("Solutions - QA Consulting"));
	}
	
	@Test
	public void testHomeToContact() {
		homePage = new Home(driver);
		
		String subjectText = contactPage.getEmailTextBoxText();
		String orderText = contactPage.getOrderTextBoxText();
		String messageText = contactPage.getMessageTextBoxText();
		
		homePage.clickContact();

		contactPage = new Contact(driver);
		
		contactPage.selectSubjectHeading();
		
		contactPage.getEmailInput("Hi");
		assertTrue(subjectText.contains("Hi"));
		
		contactPage.getOrderInput("Hi");
		assertTrue(orderText.contains("Hi"));
		
		contactPage.getMessageInput("Hi");
		assertTrue(messageText.contains("Hi"));
		
	}
	
	@Test
	public void testHomeNewsletter() {
		homePage = new Home(driver);
		
		String newsletterText = homePage.getNewletterTextBoxText();

		homePage.getNewletterTextBox("bradley.pollard@qa.com");
		assertTrue(newsletterText.contains("bradley.pollard@qa.com"));
		homePage.submitNewsletter();
		
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