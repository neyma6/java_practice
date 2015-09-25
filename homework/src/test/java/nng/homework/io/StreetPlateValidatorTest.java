package nng.homework.io;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import nng.homework.domain.IStreetPlate;
import nng.homework.domain.StreetPlateBuilder;

public class StreetPlateValidatorTest {

	private static final String NAME = "Kinizsi";
	private static final String TYPE = "utca";
	private static final String SCHEME = "E";
	private static final String EMPTY_STRING = "";
	private static final int FROM = 8;
	private static final int TO = 10;
	
	@Test
	public void whenTheStreetPlateHasAllParametersThenTheStreetPlateIsValid() {
		IStreetPlate sp = StreetPlateBuilder.createBuilder()
				.withName(NAME)
				.withType(TYPE)
				.withLeftSideScheme(SCHEME)
				.withLeftSideFrom(FROM)
				.withLeftSideTo(TO)
				.build().get(0);
		
		assertTrue(StreetPlateValidator.validate(sp));
	}
	
	@Test
	public void whenTheInputIsNullThenValidationFails() {
		assertFalse(StreetPlateValidator.validate(null));
	}
	
	@Test
	public void whenTheNameIsAnEmptyStringThenValidationFails() {
		IStreetPlate sp = StreetPlateBuilder.createBuilder()
				.withName(EMPTY_STRING)
				.withType(TYPE)
				.withLeftSideScheme(SCHEME)
				.withLeftSideFrom(FROM)
				.withLeftSideTo(TO)
				.build().get(0);
		
		assertFalse(StreetPlateValidator.validate(sp));
	}
	
	
	@Test
	public void whenOnlyTheNameIsGivenThenValidationFails() {
		IStreetPlate sp = StreetPlateBuilder.createBuilder()
				.withName(NAME)
				.build().get(0);
		
		assertFalse(StreetPlateValidator.validate(sp));
	}
	
	@Test
	public void whenTheRangeIsNotGivenThenValidationFails() {
		IStreetPlate sp = StreetPlateBuilder.createBuilder()
				.withName(NAME)
				.withType(TYPE)
				.withLeftSideScheme(SCHEME)
				.build().get(0);
		
		assertFalse(StreetPlateValidator.validate(sp));
	}
	
	@Test
	public void whenTheFromIsGreaterThenTheToThenValidationFails() {
		IStreetPlate sp = StreetPlateBuilder.createBuilder()
				.withName(NAME)
				.withType(TYPE)
				.withLeftSideScheme(SCHEME)
				.withLeftSideFrom(TO)
				.withLeftSideTo(FROM)
				.build().get(0);
		
		assertFalse(StreetPlateValidator.validate(sp));
	}
	
}
