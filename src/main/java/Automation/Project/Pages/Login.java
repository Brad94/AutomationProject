package Automation.Project.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {

	WebDriver driver;

	By email = By.name("email");
	By password = By.name("passwd");
	By loginSubmit = By.name("SubmitLogin");
	By logoutButton = By.xpath("//*[@id='header']/div[2]/div/div/nav/div[2]/a");
	
	public Login(WebDriver driver) {
		this.driver = driver;
	}
	public void getEmailInput(String text) {
		driver.findElement(email).sendKeys(text);
	}
	public String getEmailTextBoxText() {
		return driver.findElement(email).getAttribute("value");
	}
	
	public void getPasswordInput(String text) {
		driver.findElement(password).sendKeys(text);
	}
	public String getPasswordTextBoxText() {
		return driver.findElement(password).getAttribute("value");
	}
	
	public void submitLogin() {
		driver.findElement(loginSubmit).click();
	}
	
	public void submitLogout() {
		driver.findElement(logoutButton).click();
	}
	public String getTitle() {
		return driver.getTitle();
	}

}