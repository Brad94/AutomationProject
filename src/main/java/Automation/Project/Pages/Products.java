package Automation.Project.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Products {
	
	WebDriver driver;
	
	By heading = By.name("id_contact");
	By products = By.xpath("//*[@id='block_top_menu']/ul/li[1]/a");
	By productOne = By.xpath("//*[@id='center_column']/ul/li[1]/div/div[1]/div/a[1]");
	By productTwo = By.xpath("//*[@id='center_column']/ul/li[3]/div/div[1]/div/a[1]");
	By size = By.name("group_1");
	By addtobasket = By.name("Submit");
	By checkout = By.xpath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/a");
	
	public Products(WebDriver driver) {
		this.driver = driver;
	}
	public void clickProductOne() {
		driver.get(driver.findElement(productOne).getAttribute("href"));
	}
	public void clickProductTwo() {
		driver.get(driver.findElement(productTwo).getAttribute("href"));
	}
	public void selectProductSize() {
		Select oSelect = new Select(driver.findElement(size));
		oSelect.selectByValue("2");
	}
	public void submitAddToBasket() {
		driver.findElement(addtobasket).click();
	}
	public void proceedToCheckout() {
		driver.findElement(checkout).click();
	}
	
	
	
	public void clickProducts() {
		driver.get(driver.findElement(products).getAttribute("href"));
	}
	
	
}
