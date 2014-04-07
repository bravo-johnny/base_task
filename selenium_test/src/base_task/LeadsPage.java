package base_task;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeadsPage extends NavigationPage{

	@FindBy( partialLinkText = "Lead" )
	private WebElement addNewLeadBtn;
	
	public LeadsPage(WebDriver driver) {
		super(driver);
	}
	
	// click on "+Lead" method
	public NewLeadPage addNewLead() {
		addNewLeadBtn.click();
		return PageFactory.initElements( driver, NewLeadPage.class );
	}

}
