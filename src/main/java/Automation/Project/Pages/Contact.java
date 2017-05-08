package Automation.Project.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Contact {
	
	WebDriver driver;
	
	By heading = By.name("id_contact");
	By from = By.name("from");
	By orderID = By.name("id_order");
	By message = By.id("message");
	
	public Contact(WebDriver driver) {
		this.driver = driver;
	}
	public void selectSubjectHeading() {
		Select oSelect = new Select(driver.findElement(heading));
		oSelect.selectByValue("1");
	}
	public String getSubjectHeading() {
		return driver.findElement(heading).getText();
	}
	public void getEmailInput(String text) {
		driver.findElement(from).sendKeys(text);
	}
	public String getEmailTextBoxText() {
		return driver.findElement(By.name("from")).getAttribute("value");
	}
	
	public void getOrderInput(String text) {
		driver.findElement(orderID).sendKeys(text);
	}
	public String getOrderTextBoxText() {
		return driver.findElement(orderID).getAttribute("value");
	}
	
	public void getMessageInput(String text) {
		driver.findElement(message).sendKeys(text);
	}
	public String getMessageTextBoxText() {
		return driver.findElement(message).getAttribute("value");
	}
	
	
}
