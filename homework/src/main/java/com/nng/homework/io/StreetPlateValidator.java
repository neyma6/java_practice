package com.nng.homework.io;

import com.nng.homework.domain.HouseNumberRange;
import com.nng.homework.domain.IStreetPlate;
import com.nng.homework.domain.Street;

public class StreetPlateValidator {

	private static final String EMPTY_STRING = "";
	private static final int ZERO = 0;
	
	public static boolean validate(IStreetPlate streetPlate) {
		
		if (streetPlate == null || streetPlate.getStreet() == null || streetPlate.getRange() == null)
			return false;
		
		Street street = streetPlate.getStreet();
		if (street.getName().equals(EMPTY_STRING) || street.getStreetType().equals(EMPTY_STRING))
			return false;
		
		HouseNumberRange range = streetPlate.getRange();
		if (range.getFrom() <= ZERO || range.getTo() <= ZERO || range.getTo() < range.getFrom())
			return false;
		
		return true;
	}
}
