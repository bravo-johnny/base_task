package base_task;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// contains top-menu

public class NavigationPage {
	protected WebDriver driver;

	@FindBy( id = "nav-leads" )
	private WebElement leadsBtn;

	@FindBy( className = "icon-cogs" )
	private WebElement cogsButton;

	@FindBy( linkText = "Settings" )
	private WebElement settingsButton;

	public NavigationPage( WebDriver driver ){
		this.driver = driver;
		delay();
	}

	public SettingsLeadsPage clickNavSettings() {
		cogsButton.click();
		settingsButton.click();
		return PageFactory.initElements( this.driver, SettingsLeadsPage.class );
	}

	public LeadsPage clickNavLeads() {
		leadsBtn.click();	
		return PageFactory.initElements( this.driver, LeadsPage.class );
	}

	public void delay() {
		try {
			Thread.sleep(500L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
