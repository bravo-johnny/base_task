package base_task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class NewLeadPage extends NavigationPage{

	@FindBy( css = "div.custom-fields-items label.control-label" )
	private List<WebElement> customItems;
	
	public NewLeadPage(WebDriver driver) {
		super(driver);
	}
	
	
	public List<String> getCustomFields() {
		List<String> list = new ArrayList<String>();
		System.out.println("==start==");
		for (WebElement customItem : customItems) {
			System.out.println( " > " +  customItem.getText());
			list.add( customItem.getText() );
		}
		System.out.println("==end==");
		return list;	
	}
	
	public boolean checkCustomFields( ArrayList<HashMap<String, String>> array_map ){
		
		boolean output = true;
		
		if(array_map.size() != customItems.size()){
			System.err.println("dupa");
			return false;
		}
		
		Iterator<HashMap<String, String>> it_remote = array_map.iterator();
		Iterator<WebElement> it_local = customItems.iterator();
		
		while (it_remote.hasNext() && it_local.hasNext())
		{
		  HashMap<String, String> customFieldInSettings = it_remote.next();
		  String customFieldOnThePage = it_local.next().getText();
		  
		  output = output && ( customFieldOnThePage.equals( customFieldInSettings.get( "name" ) ) );
		}
		return output;
	}

}
