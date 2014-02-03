package selenium_test;

public enum LeadStatuses {
	//new, working, unqualified
	NEW("New"),
	BRAND_NEW("Brand new"),
	WORKING("Working"),
	UNQUALIFIED("Unqualified"),
	NULL(null);
	
	String status = null;
	
    private LeadStatuses(String status) {
        this.status = status;
    }
    
    @Override
    public String toString(){
		return status;
    }
    
}