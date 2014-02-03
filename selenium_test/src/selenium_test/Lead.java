package selenium_test;

import java.util.ArrayList;
import java.util.List;

public class Lead {
	private String firstName = "";
	private String lastName = "";
	private String companyName = "";
	private String title = "";
	private LeadStatuses leadStatus = LeadStatuses.NULL; 
	private String email = "";
	private String phoneWork = "";
	private String phoneMobile = "";
	
	private String addressStreet = "";
	private String city = "";
	private String state = "";
	private Countries country = Countries.NULL;
	
	private String zip = "";
	private List<String> tags = new ArrayList<String>();
	
	private String industry = "";
	private String website = "";
	private String skype = "";
	private String fax = "";
	private String linkedIn = "";
	private String twitter = "";
	private String facebook = "";
	private String description = "";
	
	public String getFullName() {
		return firstName + " " + lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LeadStatuses getLeadStatus() {
		return leadStatus;
	}
	public void setLeadStatus(LeadStatuses leadStatus) {
		this.leadStatus = leadStatus;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneWork() {
		return phoneWork;
	}
	public void setPhoneWork(String phoneWork) {
		this.phoneWork = phoneWork;
	}
	public String getPhoneMobile() {
		return phoneMobile;
	}
	public void setPhoneMobile(String phoneMobile) {
		this.phoneMobile = phoneMobile;
	}
	public String getAddressStreet() {
		return addressStreet;
	}
	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Countries getCountry() {
		return country;
	}
	public void setCountry(Countries country) {
		this.country = country;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public void addTag(String tagName){
		tags.add(tagName);
	}
	public void removeTag( String tagName){
		if(tags.contains(tagName))
			tags.remove(tagName);
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getSkype() {
		return skype;
	}
	public void setSkype(String skype) {
		this.skype = skype;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	public String getTwitter() {
		return twitter;
	}
	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}
	public String getFacebook() {
		return facebook;
	}
	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Lead [firstName=" + firstName + ", lastName=" + lastName
				+ ", companyName=" + companyName + ", title=" + title
				+ ", leadStatus=" + leadStatus + ", email=" + email
				+ ", phoneWork=" + phoneWork + ", phoneMobile=" + phoneMobile
				+ ", addressStreet=" + addressStreet + ", city=" + city
				+ ", state=" + state + ", country=" + country + ", zip=" + zip
				+ ", tags=" + tags + ", industry=" + industry + ", website="
				+ website + ", skype=" + skype + ", fax=" + fax + ", linkedIn="
				+ linkedIn + ", twitter=" + twitter + ", facebook=" + facebook
				+ ", description=" + description + "]";
	}

}
