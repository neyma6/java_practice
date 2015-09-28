package com.nng.homework.domain;

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
	
	//remove this
	public HouseNumberScheme oppositeSideScheme() {
		switch(this) {
			case ODD: return EVEN;
			case EVEN: return ODD;
			default: return EVEN; //never happens this
		}
	}
	
	//remove this
	public static HouseNumberScheme getProperScheme(String schemeValue) {
		switch(schemeValue) {
			case "O": return ODD; 
			case "E": return EVEN;
			default: return EVEN;
		}
	}
}
