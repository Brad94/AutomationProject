package Automation.Project.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Solutions {

	WebDriver driver;

	By body = By.className("model-card__heading");
	By header = By.xpath("/html/body/header/div[2]/div[1]/div[1]/h1/span");
	By logo = By.className("nav__logo icon icon--ui__logo");
	By title = By.className("title");

	public Solutions(WebDriver driver) {
		this.driver = driver;
	}
	public String getHeader() {
		return driver.findElement(header).getText();
	}
	public String getBody() {
		return driver.findElement(body).getText();
	}
	public String getTitle() {
		return driver.getTitle();
	}

	public void clickOnLogo() {
		driver.findElement(logo).click();
	}

}