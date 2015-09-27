package com.nng.homework.io;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.nng.homework.domain.IStreetPlate;
import com.nng.homework.domain.StreetPlateBuilder;

public class StreetPlateValidatorTest {

	private static final String NAME = "Kinizsi";
	private static final String TYPE = "utca";
	private static final String SCHEME = "E";
	private static final String EMPTY_STRING = "";
	private static final int FROM = 8;
	private static final int TO = 10;
	
	private StreetPlateValidator validator;
	
	@Before
	public void setup() {
		validator = new StreetPlateValidator();
	}
	
	@Test
	public void whenTheStreetPlateHasAllParametersThenTheStreetPlateIsValid() {
		IStreetPlate sp = StreetPlateBuilder.createBuilder()
				.withName(NAME)
				.withType(TYPE)
				.withLeftSideScheme(SCHEME)
				.withLeftSideFrom(FROM)
				.withLeftSideTo(TO)
				.build().get(0);
		
		assertTrue(validator.validate(sp));
	}
	
	@Test
	public void whenTheInputIsNullThenValidationFails() {
		assertFalse(validator.validate(null));
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
		
		assertFalse(validator.validate(sp));
	}
	
	
	@Test
	public void whenOnlyTheNameIsGivenThenValidationFails() {
		IStreetPlate sp = StreetPlateBuilder.createBuilder()
				.withName(NAME)
				.build().get(0);
		
		assertFalse(validator.validate(sp));
	}
	
	@Test
	public void whenTheRangeIsNotGivenThenValidationFails() {
		IStreetPlate sp = StreetPlateBuilder.createBuilder()
				.withName(NAME)
				.withType(TYPE)
				.withLeftSideScheme(SCHEME)
				.build().get(0);
		
		assertFalse(validator.validate(sp));
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
		
		assertFalse(validator.validate(sp));
	}
	
}
