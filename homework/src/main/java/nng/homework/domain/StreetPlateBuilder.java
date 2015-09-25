package nng.homework.domain;

public class StreetPlateBuilder {

	private String name;
	private String type;
	private int from;
	private int to;
	private String scheme;
	
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
	
	public StreetPlateBuilder withScheme(String scheme) {
		this.scheme = scheme;
		return this;
	}
	
	public StreetPlateBuilder withFrom(int from) {
		this.from = from;
		return this;
	}
	
	public StreetPlateBuilder withTo(int to) {
		this.to = to;
		return this;
	}
	
	public IStreetPlate build() {
		HouseNumberScheme houseNumberScheme = HouseNumberScheme.getProperScheme(scheme);
		Street street = new Street(name, type, houseNumberScheme);
		HouseNumberRange range = new HouseNumberRange(from, to);
		return new StreetPlate(street, range);
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
	}
	
}
