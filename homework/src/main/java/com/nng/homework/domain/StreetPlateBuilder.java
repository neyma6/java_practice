package com.nng.homework.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StreetPlateBuilder {

	private static final String EMPTY_STRING = "";
	
	private String name = EMPTY_STRING;
	private String type = EMPTY_STRING;
	
	private Side left;
	private Side right;
	
	private StreetPlateBuilder() {
	}
	
	public static StreetPlateBuilder createBuilder() {
		return new StreetPlateBuilder();
	}
	
	public StreetPlateBuilder withName(String name) {
		this.name = name;
		return this;
	}
	
	public StreetPlateBuilder withType(String type) {
		this.type = type;
		return this;
	}
	
	public StreetPlateBuilder withLeftSideScheme(String scheme) {
		createLeftSide();
		this.left.setScheme(scheme);
		return this;
	}
	
	public StreetPlateBuilder withRightSideScheme(String scheme) {
		createRightSide();
		this.right.setScheme(scheme);
		return this;
	}
	
	public StreetPlateBuilder withLeftSideFrom(int from) {
		createLeftSide();
		this.left.setFrom(from);
		return this;
	}
	
	public StreetPlateBuilder withRightSideFrom(int from) {
		createRightSide();
		this.right.setFrom(from);
		return this;
	}
	
	public StreetPlateBuilder withLeftSideTo(int to) {
		createLeftSide();
		this.left.setTo(to);
		return this;
	}
	
	public StreetPlateBuilder withRightSideTo(int to) {
		createRightSide();
		this.right.setTo(to);
		return this;
	}
	
	public List<IStreetPlate> build() {
		List<IStreetPlate> streetPlates = new ArrayList<>();
		
		streetPlates.addAll(processStreetSide(left));
		streetPlates.addAll(processStreetSide(right));
		
		return streetPlates;
	}
	
	private List<IStreetPlate> processStreetSide(Side side) {
		if (side == null) {
			return Collections.emptyList();
		}
		
		List<IStreetPlate> streetPlates = new ArrayList<>();
		String scheme = side.getScheme();
		
		if (scheme.equals("M")) {
			int from = side.getFrom();
			int to = side.getTo();
			
			streetPlates.add(createStreetPlate(from, to - 1));
			streetPlates.add(createStreetPlate(from + 1, to));
			
		} else {
			streetPlates.add(createStreetPlate(side.getFrom(), side.getTo()));
		}
		
		return streetPlates;
	}
	
	private HouseNumberScheme determineScheme(int from) {
		return from % 2 == 0 ? HouseNumberScheme.EVEN : HouseNumberScheme.ODD;
	}
	
	private IStreetPlate createStreetPlate(int from, int to) {
		Street street = new Street(name, type, determineScheme(from));
		HouseNumberRange range = new HouseNumberRange(from, to);
		return new StreetPlate(street, range);
	}
	
	private void createLeftSide() {
		if (left == null)
			left = new Side();
	}
	
	private void createRightSide() {
		if (right == null)
			right = new Side();
	}
	
	private class StreetPlate implements IStreetPlate {

		private final Street street;
		private final HouseNumberRange range;
		
		public StreetPlate(Street street, HouseNumberRange range) {
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

		@Override
		public String toString() {
			return "StreetPlate [street=" + street + ", range=" + range + "]";
		}
	}
	
	private class Side {
		
		private String scheme = EMPTY_STRING;
		private int from;
		private int to;
		
		public String getScheme() {
			return scheme;
		}
		public void setScheme(String scheme) {
			this.scheme = scheme;
		}
		public int getFrom() {
			return from;
		}
		public void setFrom(int from) {
			this.from = from;
		}
		public int getTo() {
			return to;
		}
		public void setTo(int to) {
			this.to = to;
		}		
		
	}
	
}
