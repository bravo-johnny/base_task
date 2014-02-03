package selenium_test;

public enum Countries {
//poland, united states, germany, qatar
	POLAND("Poland"),
	USA("United States"),
	GERMANY("Germany"),
	TANZANIA("Tanzania, United Republic of"),
	NULL( null );
	
	String countryName = null;
	
    private Countries(String countryName) {
        this.countryName = countryName;
    }
    
    @Override
    public String toString(){
		return countryName;
    }
}
