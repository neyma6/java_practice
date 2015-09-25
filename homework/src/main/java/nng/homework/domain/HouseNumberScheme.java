package nng.homework.domain;

public enum HouseNumberScheme {

	ODD("O"),
	EVEN("E");
	
	private String schemeType;

	private HouseNumberScheme(String schemeType) {
		this.schemeType = schemeType;
	}

	public String getSchemeType() {
		return schemeType;
	}
	
	public static HouseNumberScheme getProperScheme(String schemeValue) {
		switch(schemeValue) {
			case "O": return ODD; 
			case "E": return EVEN;
			default: return EVEN;
		}
	}
}
