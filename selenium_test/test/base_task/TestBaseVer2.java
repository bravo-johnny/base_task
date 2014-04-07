package base_task;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import base_task.DashboardPage;
import base_task.HomePage;
import base_task.LeadsPage;
import base_task.LoginPage;
import base_task.NewLeadPage;
import base_task.SettingsLeadsPage;

public class TestBaseVer2 {

	private SettingsLeadsPage settingsPage;
	private DashboardPage dashboardPage;
	private final int NR_OF_CUSTOM_FIELDS = 9;
	
	private String baseUrl = "https://getbase.com";
	
	WebDriver driver;
	
	@Before
	public void openTheBrowser() {
		driver = new FirefoxDriver();
		
		driver.get( baseUrl + "/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		HomePage homePage = PageFactory.initElements( driver, HomePage.class );
		LoginPage loginPage = homePage.clickLoginBtn();
		dashboardPage = loginPage.loginAs( "qa1@os.pl", "secretpassword" );
	}

	@After
	public void  closeTheBrowser() {
		driver.quit();
	}

	@Test
	public void checkIfTheNewLeadPageHasProperCustomFields() throws InterruptedException {
		settingsPage = dashboardPage.clickNavSettings();
		settingsPage = settingsPage.customizeLeads();
		
		// requested "helper" method, that returns <K, V> pairs
		ArrayList<HashMap<String, String>> cstmFldsOnSettings = settingsPage.getCustomFields();
		
		System.out.println(cstmFldsOnSettings);
		assertTrue( cstmFldsOnSettings.size() == NR_OF_CUSTOM_FIELDS );

		LeadsPage leadsPage = settingsPage.clickNavLeads();
		NewLeadPage newLeadPage = leadsPage.addNewLead();
		
		assertTrue( newLeadPage.checkCustomFields( cstmFldsOnSettings ) );
	}
		
}
