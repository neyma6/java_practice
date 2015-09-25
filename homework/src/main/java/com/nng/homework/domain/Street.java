package com.nng.homework.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Street {
	
	private final String name;
	private final String streetType;
	private final HouseNumberScheme numberScheme;
	
	public Street(String name, String streetType, HouseNumberScheme numberScheme) {
		super();
		this.name = name;
		this.streetType = streetType;
		this.numberScheme = numberScheme;
	}

	public String getName() {
		return name;
	}

	public String getStreetType() {
		return streetType;
	}

	public HouseNumberScheme getNumberScheme() {
		return numberScheme;
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31)
				.append(name)
				.append(streetType)
				.append(numberScheme)
				.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Street))
            return false;
        if (obj == this)
            return true;
        
        Street other = (Street)obj;
        return new EqualsBuilder()
        		.append(name, other.name)
        		.append(streetType, other.streetType)
        		.append(numberScheme, other.numberScheme)
        		.isEquals();
	}

	@Override
	public String toString() {
		return "Street [name=" + name + ", streetType=" + streetType + ", numberScheme=" + numberScheme + "]";
	}
	
}
