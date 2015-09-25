package nng.homework.io;

import java.util.ArrayList;
import java.util.List;

import nng.homework.domain.IStreetPlate;
import nng.homework.domain.StreetPlateBuilder;

public class StreetPlateParser {

	private static final String DELIMITER = "\"";
	private static final String SEPARATOR = ",";
	private static final String EMPTY_STRING = "";
	private static final int[] LEFT_SIDE_INDECIES = {16, 17, 20, 21, 22};
	private static final int[] RIGHT_SIDE_INDECIES = {16, 17, 23, 24, 25};
	
	public static List<IStreetPlate> parse(List<String> data) {
		List<IStreetPlate> streetPlates = new ArrayList<>();
		
		for (String singleData : data) {
			String[] separatedData = singleData.replaceAll(DELIMITER, EMPTY_STRING).split(SEPARATOR);
			streetPlates.add(createStreetPlate(separatedData, LEFT_SIDE_INDECIES));
			streetPlates.add(createStreetPlate(separatedData, RIGHT_SIDE_INDECIES));
		}
		
		return streetPlates;
	}
	
	private static IStreetPlate createStreetPlate(String[] data, int[] indecies) {
		
		IStreetPlate streetPlate;
		try {
			streetPlate = StreetPlateBuilder.createBuilder()
					.withName(data[indecies[0]])
					.withType(data[indecies[1]])
					.withScheme(data[indecies[2]])
					.withFrom(Integer.parseInt(data[indecies[3]]))
					.withTo(Integer.parseInt(data[indecies[4]]))
					.build();
		} catch (Exception ex) {
			throw new IllegalArgumentException();
		}
		
		return streetPlate;
	}
}
