package edu.ilstu.business.era.comparators;

import java.util.Comparator;

import org.joda.time.DateTimeComparator;

import edu.ilstu.business.era.models.Event;

/**
 * Comparator class for sorting events by newest
 * 
 * @author Basheer
 *
 */
public class NewestEventComparator implements Comparator<Event> {

	@Override
	public int compare(Event eventOne, Event eventTwo) {
		// Create the DateTime Comparator
		DateTimeComparator dtComp = DateTimeComparator.getDateOnlyInstance();

		// Compare the event dates and return value
		return dtComp.compare(eventTwo.getStartDate(), eventOne.getStartDate());
	}

}
