package com.nng.homework.domain;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StreetPlateBuilderTest {

	private static final String NAME = "Kinizsi";
	private static final String TYPE = "utca";
	private static final String EVEN = "E";
	private static final String ODD = "O";
	private static final String MIXED = "M";
	private static final int EVEN_FROM = 2;
	private static final int EVEN_TO = 8;
	private static final int ODD_FROM = 3;
	private static final int ODD_TO = 9;
	
	private StreetPlateBuilder builder;
	private List<IStreetPlate> result;
	
	@Before
	public void setup() {
		builder = StreetPlateBuilder.createBuilder();
	}
	
	@Test
	public void whenEveryParameterIsGivenThen2StreetPlateShouldReturn() {
		givenBuilderWithName(NAME);
		givenBuilderWithType(TYPE);
		givenBuilderWithLeftSideParams(EVEN, EVEN_FROM, EVEN_TO);
		givenBuilderWithRightSideParams(ODD, ODD_FROM, ODD_TO);
		
		whenBuilderInvokes();
		
		thenTheResultsSizeIs(2);
		Object[][] verificationData = {{NAME, TYPE, HouseNumberScheme.EVEN, EVEN_FROM, EVEN_TO},
									  {NAME, TYPE, HouseNumberScheme.ODD, ODD_FROM, ODD_TO}};
		verifyResult(verificationData);
	}
	
	@Test
	public void whenNoParamterIsGivenThenAnEmptyListShouldReturn() {
		whenBuilderInvokes();	
		thenTheResultsSizeIs(0);
	}
	
	@Test
	public void whenJustLeftParamsGivenThen1StreetPlateShouldReturns() {
		givenBuilderWithName(NAME);
		givenBuilderWithType(TYPE);
		givenBuilderWithLeftSideParams(EVEN, EVEN_FROM, EVEN_TO);
		
		whenBuilderInvokes();
		
		thenTheResultsSizeIs(1);
		Object[][] verificationData = {{NAME, TYPE, HouseNumberScheme.EVEN, EVEN_FROM, EVEN_TO}};
		verifyResult(verificationData);
	}
	
	@Test
	public void whenJustRightParamsGivenThen1StreetPlateShouldReturns() {
		givenBuilderWithName(NAME);
		givenBuilderWithType(TYPE);
		givenBuilderWithRightSideParams(ODD, ODD_FROM, ODD_TO);
		
		whenBuilderInvokes();
		
		thenTheResultsSizeIs(1);
		Object[][] verificationData = {{NAME, TYPE, HouseNumberScheme.ODD, ODD_FROM, ODD_TO}};
		verifyResult(verificationData);
	}
	
	@Test
	public void whenNoSideSpecificParamIsGivenThenAnEmptyListShouldReturn() {
		givenBuilderWithName(NAME);
		givenBuilderWithType(TYPE);
		
		whenBuilderInvokes();
		
		thenTheResultsSizeIs(0);
	}
	
	@Test
	public void whenLeftSchemeIsMixedAndTheRightIsGivenThen3StreetPlateShouldReturn() {
		givenBuilderWithName(NAME);
		givenBuilderWithType(TYPE);
		givenBuilderWithLeftSideParams(MIXED, EVEN_FROM, ODD_TO);
		givenBuilderWithRightSideParams(ODD, ODD_FROM, ODD_TO);
		
		whenBuilderInvokes();
		
		thenTheResultsSizeIs(3);
		Object[][] verificationData = {{NAME, TYPE, HouseNumberScheme.EVEN, EVEN_FROM, ODD_TO - 1},
										{NAME, TYPE, HouseNumberScheme.ODD, EVEN_FROM + 1, ODD_TO},
										{NAME, TYPE, HouseNumberScheme.ODD, ODD_FROM, ODD_TO}};
		verifyResult(verificationData);
	}
	
	@Test
	public void whenRightSchemeIsMixedAndTheLeftIsGivenThen3StreetPlateShouldReturn() {
		givenBuilderWithName(NAME);
		givenBuilderWithType(TYPE);
		givenBuilderWithLeftSideParams(EVEN, EVEN_FROM, EVEN_TO);
		givenBuilderWithRightSideParams(MIXED, ODD_FROM, EVEN_TO);
		
		whenBuilderInvokes();
		
		thenTheResultsSizeIs(3);
		Object[][] verificationData = {{NAME, TYPE, HouseNumberScheme.EVEN, EVEN_FROM, EVEN_TO},
				{NAME, TYPE, HouseNumberScheme.ODD, ODD_FROM, EVEN_TO - 1},
				{NAME, TYPE, HouseNumberScheme.EVEN, ODD_FROM + 1, EVEN_TO}};
		verifyResult(verificationData);
	}
	
	@Test
	public void whenLeftSchemeIsMixedAndTheRightIsSideNotGivenThen2StreetPlateShouldReturn() {
		givenBuilderWithName(NAME);
		givenBuilderWithType(TYPE);
		givenBuilderWithLeftSideParams(MIXED, EVEN_FROM, ODD_TO);
		
		whenBuilderInvokes();
		
		thenTheResultsSizeIs(2);
		Object[][] verificationData = {{NAME, TYPE, HouseNumberScheme.EVEN, EVEN_FROM, ODD_TO - 1},
										{NAME, TYPE, HouseNumberScheme.ODD, EVEN_FROM + 1, ODD_TO}};
		verifyResult(verificationData);
	}
	
	@Test
	public void whenRightSchemeIsMixedAndTheLeftIsSideNotGivenThen2StreetPlateShouldReturn() {
		givenBuilderWithName(NAME);
		givenBuilderWithType(TYPE);
		givenBuilderWithRightSideParams(MIXED, ODD_FROM, EVEN_TO);
		
		whenBuilderInvokes();
		
		thenTheResultsSizeIs(2);
		Object[][] verificationData = {{NAME, TYPE, HouseNumberScheme.ODD, ODD_FROM, EVEN_TO - 1},
										{NAME, TYPE, HouseNumberScheme.EVEN, ODD_FROM + 1, EVEN_TO}};
		verifyResult(verificationData);
	}
	
	@Test
	public void whenTheRangeValuesAreChangedThenTheBuilderShouldChangeThem() {
		givenBuilderWithName(NAME);
		givenBuilderWithType(TYPE);
		givenBuilderWithLeftSideParams(EVEN, EVEN_TO, EVEN_FROM);
		
		whenBuilderInvokes();
		
		thenTheResultsSizeIs(1);
		Object[][] verificationData = {{NAME, TYPE, HouseNumberScheme.EVEN, EVEN_FROM, EVEN_TO}};
		verifyResult(verificationData);
	}
	
	private void whenBuilderInvokes() {
		result = builder.build();
	}
	
	private void givenBuilderWithName(String name) {
		builder = builder.withName(name);
	}
	
	private void givenBuilderWithType(String type) {
		builder = builder.withType(type);
	}
	
	private void givenBuilderWithLeftSideParams(String scheme, int from, int to) {
		builder = builder.withLeftSideScheme(scheme).withLeftSideFrom(from).withLeftSideTo(to);
	}
	
	private void givenBuilderWithRightSideParams(String scheme, int from, int to) {
		builder = builder.withRightSideScheme(scheme).withRightSideFrom(from).withRightSideTo(to);
	}
	
	private void thenTheResultsSizeIs(int size) {
		assertEquals(size, result.size());
	}
	
	
	private void verifyResult(Object[][] verificationData) {
		assertEquals(verificationData.length, result.size());
		for (int i = 0; i < result.size(); i++) {
			assertEquals(result.get(i).getStreet().getName(), ((String)verificationData[i][0]));
			assertEquals(result.get(i).getStreet().getStreetType(), ((String)verificationData[i][1]));
			assertEquals(result.get(i).getStreet().getNumberScheme(), ((HouseNumberScheme)verificationData[i][2]));
			assertEquals(result.get(i).getRange().getFrom(), ((int)verificationData[i][3]));
			assertEquals(result.get(i).getRange().getTo(), ((int)verificationData[i][4]));
		}
	}
}
