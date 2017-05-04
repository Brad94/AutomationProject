package Automation.Project.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Home {
	WebDriver driver;

	By title = By.className("title");
	By solutionsLink = By.className("nav-menu__item__link");
	public Home(WebDriver driver) {
		this.driver = driver;
	}

	public void clickSolutionsLink() {
		driver.get(driver.findElement(solutionsLink).getAttribute("href"));
	}

	public String getTitle() {
		return driver.getTitle();
	}
}