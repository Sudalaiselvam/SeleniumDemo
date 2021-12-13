package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	public WebDriver driver;
	
	By email = By.xpath("(//*[@type='text'])[2]");
	By password = By.xpath("//*[@type='password']");
	By login = By.xpath("(//*[@type='submit'])[2]");
	By forgotPassword = By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[2]/a/span");

	
	public LoginPage(WebDriver driver) {
		
		this.driver = driver;
	}
	
	public WebElement getEmail()
	{
		return driver.findElement(email);
	}
	

	public WebElement getPassword()
	{
		return driver.findElement(password);
	}
	
	public WebElement getLogin()
	{
		return driver.findElement(login);
	}

}
