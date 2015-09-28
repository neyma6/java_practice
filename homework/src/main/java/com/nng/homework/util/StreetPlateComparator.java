package com.nng.homework.util;

import java.util.Comparator;

import com.nng.homework.domain.HouseNumberScheme;
import com.nng.homework.domain.IStreetPlate;

public class StreetPlateComparator implements Comparator<IStreetPlate> {

	@Override
	public int compare(IStreetPlate o1, IStreetPlate o2) {
		if (o2 == null) return -1;
		if (o1 == null) return 1;
		int result = o1.getStreet().getName().compareTo(o2.getStreet().getName());
		
		if (result == 0 && o1.getStreet().getNumberScheme().equals(HouseNumberScheme.EVEN))
			return -1;
		if (result == 0 && o1.getStreet().getNumberScheme().equals(HouseNumberScheme.EVEN))
			return 1;
		
		return result;
	}

}
