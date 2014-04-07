package base_task;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	private WebDriver driver;

	public HomePage( WebDriver driver ){
		this.driver = driver;
	}

	public LoginPage clickLoginBtn() {
		driver.findElement(By.linkText("LOGIN")).click();
		return PageFactory.initElements( driver, LoginPage.class );
	}

}
