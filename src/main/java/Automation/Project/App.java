package Automation.Project;

import java.io.IOException;

import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Automation.Project.Pages.*;

public class App {
	ExtentReports report;
	ExtentTest test;
	WebDriver driver;

	Login loginPage;

	Home homePage;
	
	Contact contactPage;

	@BeforeClass()
	public void setup(){
		report = new ExtentReports(
				"C:\\Users\\Administrator\\Documents\\Eclipse\\Project\\automationreport.html",true);
	}
	
	public String getPass() throws IOException {
		String fileName = "C:\\Users\\Administrator\\Documents\\Eclipse\\Project\\pass.txt";
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
	
	@Test(priority = 1, enabled = true)
	public void testHomeToContact() {
		report = new ExtentReports(
				"C:\\Users\\Administrator\\Documents\\Eclipse\\Project\\automationreport.html",
				true);
		//System.setProperty("webdriver.gecko.driver",
		//		"..\\SeleniumFiles\\Selenium\\geckodriver.exe");
		test = report.startTest("Home to Contact");
		System.setProperty("webdriver.chrome.driver",
				"..\\SeleniumFiles\\Selenium\\chromedriver.exe");
		//driver = new FirefoxDriver();
		driver = new ChromeDriver();
		test.log(LogStatus.INFO, "Browser started");
		driver.get("http://automationpractice.com/index.php");

		homePage = new Home(driver);
			
		homePage.clickContact();
		
		contactPage = new Contact(driver);
		
		String subjectText = contactPage.getSubjectHeading();
		String emailText = contactPage.getEmailTextBoxText();
		String orderText = contactPage.getOrderTextBoxText();
		String messageText = contactPage.getMessageTextBoxText();
		
		contactPage.selectSubjectHeading();
		if (subjectText.equals("Webmaster")) {
			test.log(LogStatus.PASS, "verify contact email input");
		} else {
			test.log(LogStatus.FAIL, "verify contact email input");
		}
		
		contactPage.getEmailInput("bradley.pollard@qa.com");
		if (emailText.equals("bradley.pollard@qa.com")) {
			test.log(LogStatus.PASS, "verify contact email input");
		} else {
			test.log(LogStatus.FAIL, "verify contact email input");
		}
		
		contactPage.getOrderInput("12345");
		if (orderText.equals("12345")) {
			test.log(LogStatus.PASS, "verify contact order input");
		} else {
			test.log(LogStatus.FAIL, "verify contact order input");
		}
		
		contactPage.getMessageInput("Hi");
		if (messageText.equals("Hi")) {
			test.log(LogStatus.PASS, "verify contact message input");
		} else {
			test.log(LogStatus.FAIL, "verify contact message input");
		}
		report.endTest(test);
	//	report.flush();
		
	}
	
	@Test(priority = 2, enabled = true)
	public void testHomeToLoginandLogout() throws IOException{
		test = report.startTest("Home to Login and Logout");

		homePage = new Home(driver);
		loginPage = new Login(driver);
		
		//String emailInputText = loginPage.getEmailTextBoxText();
		//String passwordInputText = loginPage.getPasswordTextBoxText();
		
		String homePageTitle = homePage.getTitle();
		if (homePageTitle.equals("My Store")) {
			test.log(LogStatus.PASS, "verify page title");
		} else {
			test.log(LogStatus.FAIL, "verify page title");
		}
		homePage.home();
		homePage.clickLogin();

		loginPage.getEmailInput("Bradley.pollard@qa.com");
//		if (emailInputText.equals("Bradley.pollard@qa.com")) {
//			test.log(LogStatus.PASS, "verify login email input");
//		} else {
//			test.log(LogStatus.FAIL, "verify login email input");
//		}
		
		String pass = getPass();
		
		loginPage.getPasswordInput(pass);
//		if (passwordInputText.equals(pass)) {
//			test.log(LogStatus.PASS, "verify login password input");
//		}else {
//			test.log(LogStatus.FAIL, "verify login password input");
//		}

		loginPage.submitLogin();
		
		loginPage.submitLogout();
		report.endTest(test);
		//report.flush();
	}
	
	@Test(priority = 3, enabled = true)
	public void testHomeNewsletter() {
		test = report.startTest("Newletter Subscribe");
		
		homePage = new Home(driver);
		
		String newsletterText = homePage.getNewletterTextBoxText();

		homePage.getNewletterTextBox("bradley.pollard@qa.com");
		if (newsletterText.equals("bradley.pollard@qa.com")) {
			test.log(LogStatus.PASS, "verify newsletter input");
		} else {
			test.log(LogStatus.FAIL, "verify newsletter input");
		}
		homePage.submitNewsletter();
		report.endTest(test);
		report.flush();
		tearDown();
	}
	
	public void tearDown() {
		try {
			driver.close();
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
	}
}