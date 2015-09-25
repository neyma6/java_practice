package com.nng.homework.io;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.nng.homework.domain.IStreetPlate;
import com.nng.homework.io.StreetPlateParser;

public class StreetPlateParserTest {
	
	public static final String[] VALID_INPUT = {"513563,390896,390897,\"4\",3,\"\",\"\",329,\"F\",\"NNNNNNN\",50,0,45,0,0,0,\"Budapesti\",\"út\",\"1\",\"\",\"O\",109,131,\"E\",104,140,\"Budaörs\",\"Budaörs\",\"\",\"\",244,244,\"2040\",\"2040\"", 
												"513806,391093,391100,\"5.1\",3,\"\",\"\",514,\"F\",\"NNNNNNN\",50,0,40,0,0,0,\"Károly Király\",\"utca\",\"\",\"\",\"O\",25,83,\"E\",22,82,\"Budaörs\",\"Budaörs\",\"\",\"\",244,244,\"2040\",\"2040\""};
	public static final String[] EMPTY_INPUT = {};
	
	@Test
	public void whenTheInputIsValidThenTheInputIsParsedSuccessfully() {
		List<IStreetPlate> sps = StreetPlateParser.parse(Arrays.asList(VALID_INPUT));
		
		assertFalse(sps.isEmpty());
		assertTrue(sps.size() == 4);
	}
}
