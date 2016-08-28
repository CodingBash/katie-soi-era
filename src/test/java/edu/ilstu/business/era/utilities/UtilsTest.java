package edu.ilstu.business.era.utilities;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
/**
 * {@link Utils} test
 * 
 * @see Utils
 * @author Basheer Becerra (ULID: bbecer2)
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UtilsTest
{
	public static final String TEST_DATE = "2016-07-01T05:00:00.000+0000";
	public static long EXPECTED_DATE = 0;

	@Rule
	private ExpectedException exception = ExpectedException.none();
	
	/**
	 * @method {@link Utils#parseDate(String)}
	 * @objective Determine if parsing works
	 * @expectedResults Method returns correct date
	 * 
	 * @throws Exception
	 */
	@Test
	public void parseDateTest() throws Exception
	{
		assertEquals(EXPECTED_DATE, Utils.parseDate(TEST_DATE));
	}

	/**
	 * @method {@link Utils#parseDate(String)}
	 * @objective Determine if exception is thrown when parse is expected to not
	 *            work
	 * @expectedResults Method throws exception
	 * 
	 * @throws Exception
	 */
	@Test
	public void parseDateThrowsExceptionTest() throws Exception
	{
		exception.expect(Exception.class);
		Utils.parseDate("8/27/2016");
	}

}
