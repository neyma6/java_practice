package com.nng.homework.domain;

public class HouseNumberRange implements Comparable<HouseNumberRange> {

	private int from;
	private int to;
	
	public HouseNumberRange(int from, int to) {
		this.from = from;
		this.to = to;
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

	@Override
	public int compareTo(HouseNumberRange o) {
		if (o == null || from < o.from || to < o.to) 
			return -1;
		if (from > o.from || to > o.to)
			return 1;
		
		return 0;
	}

	@Override
	public String toString() {
		return "HouseNumberRange [from=" + from + ", to=" + to + "]";
	}
	
}
