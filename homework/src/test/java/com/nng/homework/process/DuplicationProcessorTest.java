package com.nng.homework.process;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.nng.homework.domain.HouseNumberRange;
import com.nng.homework.domain.HouseNumberScheme;
import com.nng.homework.domain.IStreetPlate;
import com.nng.homework.domain.Street;

public class DuplicationProcessorTest {

	private static final String NAME_1 = "Kinizsi";
	private static final String NAME_2 = "Bartok";
	private static final String TYPE_1 = "utca";
	private static final String TYPE_2 = "ter";
	private static final HouseNumberScheme EVEN = HouseNumberScheme.EVEN;
	private static final HouseNumberScheme ODD = HouseNumberScheme.ODD;
	
	private List<IStreetPlate> input;
	private List<IStreetPlate> result;
	private DuplicationProcessor processor;
	
	@Before
	public void setup() {
		processor = new DuplicationProcessor();
		input = new ArrayList<>();
	}
	
	@Test
	public void whenThereAreNoCrossSectionThenAnEmptyListShouldReturn() {
		givenStreetPlateToInput(NAME_1, TYPE_1, EVEN, 2, 10);
		givenStreetPlateToInput(NAME_1, TYPE_1, EVEN, 12, 12);
		
		whenProcessInvokes();
		
		thenTheResultsSizeIs(0);
	}
	
	private void givenStreetPlateToInput(String name, String type, HouseNumberScheme scheme, int from, int to) {
		input.add(new TestStreetPlate(new Street(name, type, scheme), new HouseNumberRange(from, to)));
	}
	
	private void whenProcessInvokes() {
		result = processor.process(input);
	}
	
	private void thenTheResultsSizeIs(int size) {
		assertEquals(result.size(), size);
	}
	
	private class TestStreetPlate implements IStreetPlate {

		private final Street street;
		private final HouseNumberRange range;
		
		public TestStreetPlate(Street street, HouseNumberRange range) {
			super();
			this.street = street;
			this.range = range;
		}

		@Override
		public Street getStreet() {
			return street;
		}

		@Override
		public HouseNumberRange getRange() {
			return range;
		}
		
	}
}
