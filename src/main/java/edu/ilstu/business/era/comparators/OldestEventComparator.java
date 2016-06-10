package edu.ilstu.business.era.comparators;

import java.util.Comparator;

import org.joda.time.DateTimeComparator;

import edu.ilstu.business.era.models.Event;

public class OldestEventComparator implements Comparator<Event> {

	@Override
	public int compare(Event eventOne, Event eventTwo) {
		// Create the DateTime Comparator
		DateTimeComparator dtComp = DateTimeComparator.getDateOnlyInstance();

		// Compare the event dates and return value
		return dtComp.compare(eventOne.getStartDate(), eventTwo.getStartDate());
	}

}
