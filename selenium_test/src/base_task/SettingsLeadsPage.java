package base_task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SettingsLeadsPage extends NavigationPage {

	@FindBy( className = "control-label" )
	private List<WebElement> ctrlLabelElementsList;

	@FindBy( linkText = "Leads" )
	private WebElement customizeLeadsBtn;

	public SettingsLeadsPage(WebDriver driver) {
		super( driver );
	}

	public ArrayList<HashMap<String, String>> getCustomFields() {
		
		ArrayList<HashMap<String, String>> array_map = new ArrayList<HashMap<String, String>>();

		for (WebElement ctrlLabelElement : ctrlLabelElementsList) {
			HashMap<String, String> map = new HashMap<String, String>();
			
			if( ctrlLabelElement!= null && ctrlLabelElement.isDisplayed() ){
				String ctrlLabelElementText = ctrlLabelElement.getText();
				String name = ctrlLabelElementText.substring( 0, ctrlLabelElementText.lastIndexOf('(') - 1 );
				String type = ctrlLabelElementText.substring( ctrlLabelElementText.lastIndexOf('(') + 1, ctrlLabelElementText.length() - 1 );
				
				map.put("name", name);
				map.put("type", type);
				array_map.add(map);
			}
		}
		return array_map;
	}

	public SettingsLeadsPage customizeLeads() {
		customizeLeadsBtn.click();
		return PageFactory.initElements( driver, SettingsLeadsPage.class );
	}

}
