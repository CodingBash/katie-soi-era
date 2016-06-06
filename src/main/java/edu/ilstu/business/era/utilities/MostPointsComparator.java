package edu.ilstu.business.era.utilities;

import java.util.Comparator;

import edu.ilstu.business.era.models.Event;

public class MostPointsComparator implements Comparator<Event> {

	@Override
	public int compare(Event eventOne, Event eventTwo) {
		return eventTwo.getPoints() - eventOne.getPoints();
	}

}
