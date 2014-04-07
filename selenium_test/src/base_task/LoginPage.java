package base_task;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	private WebDriver driver;

	@FindBy( id  = "user_email" )
	private WebElement userEmail;

	@FindBy( id  = "user_password" )
	private WebElement userPassword;

	@FindBy( xpath  = "//button[contains(.,'Login')]" )
	private WebElement loginBtn;

	public LoginPage( WebDriver driver ) {
		this.driver = driver;
		if (!driver.getTitle().equals("Login to Base")) {
			throw new IllegalStateException("This is not Home Page of logged in user, current page" +
					"is: " + driver.getCurrentUrl() +"\n>" + driver.getTitle() + "<" );
		}
	}

	public DashboardPage loginAs( String user, String pass){

		userEmail.clear();
		userEmail.sendKeys( user );
		userPassword.clear();
		userPassword.sendKeys( pass );

		loginBtn.click();
		
		return PageFactory.initElements( driver, DashboardPage.class );
	}

}
