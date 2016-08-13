package edu.ilstu.business.era.utilities;

import java.text.SimpleDateFormat;
import static edu.ilstu.business.era.constants.ApplicationConstants.DATE_FORMAT;
public class Utils {

	/**
	 * Converts date to milliseconds. Example date representation by LoudCloud: 2016-07-01T05:00:00.000+0000
	 */
	public static long getDate(String datetime) throws Exception {
		SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT);
		return sdf1.parse(datetime).getTime();
	}
	
	
}
