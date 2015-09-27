package com.nng.homework.process;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import com.nng.homework.domain.HouseNumberRange;
import com.nng.homework.domain.IStreetPlate;
import com.nng.homework.domain.Street;

public class DuplicationProcessor {

	public List<IStreetPlate> process(List<IStreetPlate> streetPlates) {
		
		Map<Street, SortedSet<HouseNumberRange>> sortedStreetPlates = new HashMap<>();
		Map<Street, SortedSet<HouseNumberRange>> duplicatedStreetPlates = new HashMap<>();
		
		sortStreetPlates(streetPlates, sortedStreetPlates, duplicatedStreetPlates);
		determineDuplicates(sortedStreetPlates, duplicatedStreetPlates);
		
		System.out.println(duplicatedStreetPlates);
		
		return null;
	}
	
	private void sortStreetPlates(List<IStreetPlate> streetPlates, 
			Map<Street, SortedSet<HouseNumberRange>> sortedStreetPlates, Map<Street,
			SortedSet<HouseNumberRange>> duplicatedStreetPlates) {
		
		for (IStreetPlate sp : streetPlates) {
			Street street = sp.getStreet();
			HouseNumberRange range = sp.getRange();
			
			SortedSet<HouseNumberRange> storedPlates = getOrCreateSortedPlates(sortedStreetPlates, street);
			if (storedPlates.contains(range)) {
				SortedSet<HouseNumberRange> duplicatedPlates = getOrCreateSortedPlates(duplicatedStreetPlates, street);
				duplicatedPlates.add(range);
			} else {
				storedPlates.add(range);
			}
			
		}
	}
	
	private void determineDuplicates(Map<Street, SortedSet<HouseNumberRange>> sortedStreetPlates,
			Map<Street, SortedSet<HouseNumberRange>> duplicatedStreetPlates) {
		
		for (Street street : sortedStreetPlates.keySet()) {
			SortedSet<HouseNumberRange> sortedRanges = sortedStreetPlates.get(street);
			HouseNumberRange previousRange = sortedRanges.first();
			
			for (HouseNumberRange range : sortedRanges) {
				if (range == previousRange) {
					continue;
				}
				
				if (isCrossSection(previousRange, range)) {
					HouseNumberRange crossSection = getCrossSection(previousRange, range);
				}
				
				previousRange = range;
			}
		}
	}
	
	private boolean isCrossSection(HouseNumberRange first, HouseNumberRange second) {
		
		if (first.getFrom() <= second.getFrom() && second.getFrom() <= first.getTo()) 
			return true;
		
		return false;
	}
	
	private HouseNumberRange getCrossSection(HouseNumberRange first, HouseNumberRange second) {
		int from = second.getFrom();
		int to = second.getTo() < first.getTo() ? second.getTo() : first.getTo();
		return new HouseNumberRange(from, to);
	}
	
	private SortedSet<HouseNumberRange> getOrCreateSortedPlates(Map<Street, SortedSet<HouseNumberRange>> map, Street street) {
		
		if (map.containsKey(street)) {
			return map.get(street);
		} else {
			SortedSet<HouseNumberRange> storedPlates = new TreeSet<>();
			map.put(street, storedPlates);
			return storedPlates;
		}
	}
}
