package selenium_test;

import static org.junit.Assert.*;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Test12 {

	/* *
	 *  TASK:
	 * 
		V 1. Log into the Web version of Base.
		V 2. Create a new Lead.
		V 3. Check that its Lead status is "New"
		V 3. Go into Settings / Leads / Lead statuses and change the name of the "New" status to a different name.
		V 4. Go back to the Lead to check if the name change is reflected.
	 * 
	 */

	private Lead lead;
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	private final static String USER = "extol@poczta.fm";
	private final static String PASS = "alaala";
	

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		baseUrl = "https://getbase.com";
		driver.get(baseUrl + "/");	    
		
		login();
		
		lead = new Lead();
		lead.setFirstName( "Johnny" );
		lead.setLastName( "Bravo" );
		lead.setCompanyName( "Test INC." );
		lead.setTitle( "CEO" );
		lead.setEmail( "jb@test.com" );
		lead.setCountry( Countries.TANZANIA );
		lead.setLeadStatus( LeadStatuses.NEW );
	}
	

	@Test
	public void changeLeadStatus() throws Exception {
		
		// add a new Lead
		assertTrue( addLead( lead ) ); 

		// TASK_1 - check if Lead's status is "NEW" (for newly created Lead)
		assertEquals( LeadStatuses.NEW.toString(), getLeadStatus( lead.getFullName() ) ); 	
		
		// rename LeadStatus NEW -> BRAND_NEW
		assertTrue( renameLeadStatus( LeadStatuses.NEW.toString(), LeadStatuses.BRAND_NEW.toString() ) );
		
		// reopen browser
		tearDown(); 
		setUp();
		
		// TASK_2 - check if Lead's status has cheanged accordingly (to BRAND_NEW)
		assertEquals( LeadStatuses.BRAND_NEW.toString(), getLeadStatus( lead.getFullName() ) ); //check if Lead's status is "BRAND_NEW"
	
	}

	
	@Ignore("cleanup")
	@Test
	public void revertChanges(){
		assertTrue( removeLead( lead.getFullName() ) );
		assertTrue( renameLeadStatus( LeadStatuses.BRAND_NEW.toString(), LeadStatuses.NEW.toString() ) );
	}

	
	@After
	public void tearDown() throws Exception {		
		driver.quit();
		
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
	
	private boolean addLead( Lead localLead ) {
		try{
			menuLeads(); // click on "Leads"
			
			driver.findElement(By.id("selection")).click(); // click on "+Lead"

			driver.findElement(By.id("lead-first-name")).clear();
			driver.findElement(By.id("lead-first-name")).sendKeys( localLead.getFirstName() );

			driver.findElement(By.id("lead-last-name")).clear();
			driver.findElement(By.id("lead-last-name")).sendKeys( localLead.getLastName() );

			driver.findElement(By.id("lead-company-name")).clear();
			driver.findElement(By.id("lead-company-name")).sendKeys( localLead.getCompanyName() );

			driver.findElement(By.id("lead-title")).clear();
			driver.findElement(By.id("lead-title")).sendKeys( localLead.getTitle() );		

			Select select  = new Select( driver.findElement(By.name("status_id")) );
			select.selectByVisibleText( localLead.getLeadStatus().toString() );

			driver.findElement(By.id("lead-email")).clear();
			driver.findElement(By.id("lead-email")).sendKeys( localLead.getEmail() );	
			
			// scroll down
			int y = driver.findElement(By.id("lead-title")).getLocation().y;
			
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript( "window.scrollBy( 0, " + y + " )" ); 
			
			
			// Country ver.1
			driver.findElement(By.xpath( "//label[contains(.,'Country')]/following-sibling::div//a[contains(.,'Select an Option')]/div" )).click();
			driver.findElement(By.xpath( "//li[contains(.,'" + localLead.getCountry().toString() + "')]" )).click();
			/*
			driver.findElement(By.xpath("//div[@id='" + id + "']//input")).clear();
			driver.findElement(By.xpath("//div[@id='" + id + "']//input")).sendKeys( "Poland" );
			driver.findElement(By.xpath("//div[@id='" + id + "']//input")).sendKeys( Keys.RETURN );
			*/
			
			
			clickSave();
			
			return true;
		}
		catch(Exception e){
			System.err.println("couldn't add Lead: " + localLead.getFullName() + "\n" + e );
			return false;
		}
	}


	private void clickSave() {
		driver.findElement(By.linkText("Save")).click();
	}
	
	private String getLeadStatus(String fullName) {
		try{
			menuLeads();
			driver.findElement( By.linkText( fullName ) ).click();  //click on Lead's name
			String status = driver.findElement( By.className( "lead-status") ).getText(); //get its status
			
			return status;
		}
		catch(NoSuchElementException e){
			System.err.println("Couldn't check status for Lead: <" + fullName +"> !" );
			
			return null;
		}
	}
	
	private void login() {
		driver.findElement(By.linkText("LOGIN")).click();
		driver.findElement(By.id("user_email")).clear();
		driver.findElement(By.id("user_email")).sendKeys(USER);
		driver.findElement(By.id("user_password")).clear();
		driver.findElement(By.id("user_password")).sendKeys(PASS);

		driver.findElement(By.xpath( "//button[contains(.,'Login')]")).click();

	}
	

	private void menuLeads() {
		driver.findElement(By.id("nav-leads")).click();
	}


	private void openSettingsPage() {
		driver.findElement(By.id("user-dd")).click();  
		driver.findElement(By.linkText("Settings")).click();
	}


	private boolean removeLead( String fullName ) {
		try{
			menuLeads();
			
			driver.findElement( By.linkText( fullName ) ).click();  //click on Lead's name
			driver.findElement( By.linkText( "Delete" ) ).click(); 
			driver.findElement( By.linkText( "Remove" ) ).click(); 
			
			Thread.sleep(1000L);
			
			List<WebElement> list = driver.findElements( By.linkText( fullName ) );
			System.out.println("list.size():" + list.size() );
			
			if( list.size() != 0 )
				return false;
			else
				return true;
		}
		catch(Exception e){
			System.err.println("Couldn't remove Lead: <" + fullName +"> !\n\n" + e );
			return false;
		} 
	}

	private boolean renameLeadStatus(String oldStatus, String newStatus ) throws NoSuchElementException {
		try{
			// pick Settings
			openSettingsPage();
			driver.findElement(By.linkText("Leads")).click();
			driver.findElement(By.linkText("Lead Statuses")).click();

			//rename given Status
			driver.findElement(By.xpath("//label[contains(.,'" + oldStatus + "')]/following-sibling::div/div/button[contains(.,'Edit')]")).click();
			driver.findElement(By.xpath("//label[contains(.,'Name')]/following-sibling::div/input[@id='name' and @value='" + oldStatus + "']")).clear();
			driver.findElement(By.xpath("//label[contains(.,'Name')]/following-sibling::div/input[@id='name' and @value='" + oldStatus + "']")).sendKeys( newStatus );
			driver.findElement(By.xpath("//div//input[@value='" + oldStatus + "']/ancestor::div/following-sibling::div//button[contains(.,'Save')]")).click();

			List<WebElement> list = driver.findElements(By.xpath("//label[contains(.,'" + newStatus + "')]"));

			if ( list.size() != 1 )
				return false;
			else
				return true;

		}
		catch(NoSuchElementException e){
			System.err.println("Couldn't rename status: <" + oldStatus +"> !\n\n" + e );
			return false;
		}
	}


}
