package com.nng.homework.io;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.nng.homework.domain.IStreetPlate;
import com.nng.homework.domain.StreetPlateBuilder;

public class StreetPlateParser {

	private static final Logger LOGGER = Logger.getLogger(StreetPlateParser.class.getName());
	
	private static final String DELIMITER = "\"";
	private static final String SEPARATOR = ",";
	private static final String EMPTY_STRING = "";
	
	private IValidator<IStreetPlate> validator;
	
	public StreetPlateParser(IValidator<IStreetPlate> validator) {
		this.validator = validator;
	}

	public List<IStreetPlate> parse(List<String> data) {
		List<IStreetPlate> streetPlates = new ArrayList<>();
		
		if (data != null) {
			for (String singleData : data) {
				String[] separatedData = singleData.replaceAll(DELIMITER, EMPTY_STRING).split(SEPARATOR);
	
				List<IStreetPlate> rawStreetPlates = createStreetPlates(separatedData);
				
				for (IStreetPlate sp : rawStreetPlates) {
					if (validator.validate(sp))
						streetPlates.add(sp);
				}
			}
		}
		
		return streetPlates;
	}
	
	private List<IStreetPlate> createStreetPlates(String[] data) {
		
		List<IStreetPlate> streetPlate;
		try {
			streetPlate = StreetPlateBuilder.createBuilder()
					.withName(data[16])
					.withType(data[17])
					.withLeftSideScheme(data[20])
					.withLeftSideFrom(Integer.parseInt(data[21]))
					.withLeftSideTo(Integer.parseInt(data[22]))
					.withRightSideScheme(data[23])
					.withRightSideFrom(Integer.parseInt(data[24]))
					.withRightSideTo(Integer.parseInt(data[25]))
					.build();
		} catch (Exception ex) {
			LOGGER.log(Level.SEVERE, "Error occured during data parsing!");
			throw new IllegalArgumentException();
		}
		
		return streetPlate;
	}
}
