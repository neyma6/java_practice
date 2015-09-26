package com.nng.homework.io;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.nng.homework.domain.HouseNumberScheme;
import com.nng.homework.domain.IStreetPlate;

public class StreetPlateParserTest {
	
	public static final String[] VALID_INPUT = {"513563,390896,390897,\"4\",3,\"\",\"\",329,\"F\",\"NNNNNNN\",50,0,45,0,0,0,\"Budapesti\",\"út\",\"1\",\"\",\"O\",109,131,\"E\",104,140,\"Budaörs\",\"Budaörs\",\"\",\"\",244,244,\"2040\",\"2040\""};
	public static final String[] EMPTY_INPUT = {};
	
	public static final String[] INVALID_INPUT_WRONG_NUMBER = {"513563,390896,390897,\"4\",3,\"\",\"\",329,\"F\",\"NNNNNNN\",50,0,45,0,0,0,\"Budapesti\",\"út\",\"1\",\"\",\"O\",109,131,\"E\",test,140,\"Budaörs\",\"Budaörs\",\"\",\"\",244,244,\"2040\",\"2040\""};
	
	public static final String[] INVALID_INPUT_FEW_DATA = {"513563,390896,390897,\"4\",3,\"\",\"\",329,\"F\",\"NNNNNNN\""};
	
	private StreetPlateParser parser;
	
	@Before
	public void setup() {
		parser = new StreetPlateParser();
	}
	
	@Test
	public void whenTheInputIsValidThenTheInputIsParsedSuccessfully() {
		List<IStreetPlate> sps = parser.parse(Arrays.asList(VALID_INPUT));
		
		assertFalse(sps.isEmpty());
		assertTrue(sps.size() == 2);
		
		IStreetPlate left = sps.get(0);
		IStreetPlate right = sps.get(1);
		
		assertEquals("Budapesti", left.getStreet().getName());
		assertEquals("Budapesti", right.getStreet().getName());
		
		assertEquals("út", left.getStreet().getStreetType());
		assertEquals("út", right.getStreet().getStreetType());
		
		assertEquals(HouseNumberScheme.ODD, left.getStreet().getNumberScheme());
		assertEquals(HouseNumberScheme.EVEN, right.getStreet().getNumberScheme());
		
		assertEquals(109, left.getRange().getFrom());
		assertEquals(104, right.getRange().getFrom());
		
		assertEquals(131, left.getRange().getTo());
		assertEquals(140, right.getRange().getTo());
	}
	
	@Test 
	public void whenTheInputIsEmptyThenAnEmptyArrayShouldReturn() {
		List<IStreetPlate> sps = parser.parse(Arrays.asList(EMPTY_INPUT));
		assertTrue(sps.isEmpty());
	}
	
	@Test 
	public void whenTheInputIsNullThenAnEmptyArrayShouldReturn() {
		List<IStreetPlate> sps = parser.parse(null);
		assertTrue(sps.isEmpty());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void whenTheInputContainsStringInsteadOfIntThenAndExceptionShouldBeThrown() {
		parser.parse(Arrays.asList(INVALID_INPUT_WRONG_NUMBER));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void whenTheInputContainsTooFewDataThenAndExceptionShouldBeThrown() {
		parser.parse(Arrays.asList(INVALID_INPUT_FEW_DATA));
	}
	
}
