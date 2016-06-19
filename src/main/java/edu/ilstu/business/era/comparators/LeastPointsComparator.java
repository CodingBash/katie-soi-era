package edu.ilstu.business.era.comparators;

import java.util.Comparator;

import edu.ilstu.business.era.models.Event;

/**
 * Comparator class for sorting events by the least points
 * 
 * @author Basheer
 *
 */
public class LeastPointsComparator implements Comparator<Event> {

	@Override
	public int compare(Event eventOne, Event eventTwo) {
		return eventOne.getPoints() - eventTwo.getPoints();
	}

}
