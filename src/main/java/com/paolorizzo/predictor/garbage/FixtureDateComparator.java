package com.paolorizzo.predictor.hibernate.model.comparators;

import java.util.Comparator;

import com.paolorizzo.predictor.hibernate.model.Fixture;

public class FixtureDateComparator implements Comparator<Fixture> {

	@Override
	public int compare(Fixture fixture1, Fixture fixture2) {
		return (int) (fixture1.getDate() - fixture2.getDate());
	}

}
