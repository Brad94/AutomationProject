package Automation.Project;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
				"C:\\Users\\BjPol\\Documents\\Eclipse\\AutomationProject\\automationreport.html",true);
	}
	
	public String getPass() throws IOException {
		String fileName = "C:\\Users\\BjPol\\Documents\\Eclipse\\AutomationProject\\pass.txt";
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
				"C:\\Users\\BjPol\\Documents\\Eclipse\\AutomationProject\\automationreport.html",
				true);
		//System.setProperty("webdriver.gecko.driver",
		//		"..\\SeleniumFiles\\Selenium\\geckodriver.exe");
		test = report.startTest("Home to Contact");
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\BjPol\\Documents\\Eclipse\\AutomationProject\\SeleniumFiles\\Selenium\\chromedriver.exe");
		//driver = new FirefoxDriver();
		driver = new ChromeDriver();
		test.log(LogStatus.INFO, "Browser started");
		driver.get("http://automationpractice.com/index.php");

		homePage = new Home(driver);
			
		homePage.clickContact();
		
		contactPage = new Contact(driver);
		
		contactPage.selectSubjectHeading();
		String subjectText = contactPage.getSubjectHeading();
		if (subjectText.contains("Webmaster")) {
			test.log(LogStatus.PASS, "verify contact email input");
		} else {
			test.log(LogStatus.FAIL, "verify contact email input");
		}
		
		contactPage.getEmailInput("bradley.pollard@qa.com");
		String emailText = driver.findElement(By.name("from")).getAttribute("value");
		if (emailText.contains("bradley.pollard@qa.com")) {
			test.log(LogStatus.PASS, "verify contact email input");
		} else {
			test.log(LogStatus.FAIL, "verify contact email input");
		}
		
		contactPage.getOrderInput("12345");
		String orderText = driver.findElement(By.name("id_order")).getAttribute("value");
		if (orderText.contains("12345")) {
			test.log(LogStatus.PASS, "verify contact order input");
		} else {
			test.log(LogStatus.FAIL, "verify contact order input");
		}
		
		contactPage.getMessageInput("Hi");
		String messageText = contactPage.getMessageTextBoxText();
		if (messageText.equals("Hi")) {
			test.log(LogStatus.PASS, "verify contact message input");
		} else {
			System.out.println("Er" + messageText);
			test.log(LogStatus.FAIL, "verify contact message input");

		}
		report.endTest(test);
		
	}
	
	@Test(priority = 2, enabled = true)
	public void testHomeToLoginandLogout() throws IOException{
		test = report.startTest("Home to Login and Logout");

		homePage = new Home(driver);
		loginPage = new Login(driver);
				
		String homePageTitle = homePage.getTitle();
		if (homePageTitle.contains("My Store")) {
			test.log(LogStatus.PASS, "verify page title");
		} else {
			test.log(LogStatus.FAIL, "verify page title");
		}
		homePage.home();
		homePage.clickLogin();

		loginPage.getEmailInput("Bradley.pollard@qa.com");
		String emailInputText = loginPage.getEmailTextBoxText();
		if (emailInputText.equals("Bradley.pollard@qa.com")) {
			test.log(LogStatus.PASS, "verify login email input");
		} else {
			test.log(LogStatus.FAIL, "verify login email input");
		}
		
		String pass = getPass();
		
		loginPage.getPasswordInput(pass);
		String passwordInputText = loginPage.getPasswordTextBoxText();
		if (passwordInputText.equals(pass)) {
			test.log(LogStatus.PASS, "verify login password input");
		}else {
			test.log(LogStatus.FAIL, "verify login password input");
		}

		loginPage.submitLogin();
		
		loginPage.submitLogout();
		report.endTest(test);
		//report.flush();
	}
	
	@Test(priority = 3, enabled = true)
	public void testHomeNewsletter() {
		test = report.startTest("Newletter Subscribe");
		
		homePage = new Home(driver);
		
		homePage.getNewletterTextBox("bradley.pollard@qa.com");
		String newsletterText = homePage.getNewletterTextBoxText();
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