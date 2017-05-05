package Automation.Project.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home {
	WebDriver driver;
	
	By login = By.className("login");
	By tshirts = By.xpath("//=*[@id='block_top_menu']/ul/li[3]/a");
	By contact = By.xpath("//*[@id='contact-link']/a");
	By newsletter = By.id("newsletter-input");
	By newsletterButton = By.name("submitNewsletter");
	
	public Home(WebDriver driver) {
		this.driver = driver;
	}

	public void clickLogin() {
		driver.get(driver.findElement(login).getAttribute("href"));
	}
	public void clickTshirts() {
		driver.get(driver.findElement(tshirts).getAttribute("href"));
	}
	public void clickContact() {
		driver.get(driver.findElement(contact).getAttribute("href"));
	}
	public void getNewletterTextBox(String text) {
		driver.findElement(newsletter).sendKeys(text);
	}
	public String getNewletterTextBoxText() {
		return driver.findElement(newsletter).getText();
	}
	public void submitNewsletter() {
		driver.findElement(newsletterButton).click();
	}

	
	
	public String getTitle() {
		return driver.getTitle();
	}
}