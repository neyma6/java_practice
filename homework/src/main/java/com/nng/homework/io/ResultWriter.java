package com.nng.homework.io;

import java.util.List;

import com.nng.homework.domain.IStreetPlate;

public class ResultWriter {

	private static final String PATTERN = "%s %s: %s %d %d";
 	
	public void write(List<IStreetPlate> streetPlates) {
		
		for (IStreetPlate sp : streetPlates) {
			String output = String.format(PATTERN, sp.getStreet().getName(),
					sp.getStreet().getStreetType(),
					sp.getStreet().getNumberScheme().getSchemeType(),
					sp.getRange().getFrom(), sp.getRange().getTo());
			
			System.out.println(output);
		}
	}
}
