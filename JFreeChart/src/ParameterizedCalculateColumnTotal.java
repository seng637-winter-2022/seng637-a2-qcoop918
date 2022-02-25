import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class ParameterizedCalculateColumnTotal {

	@Parameter(value = 0)
	public double val1;
	@Parameter(value = 1)
	public double val2;
	@Parameter(value = 2)
	public double val3;
	@Parameter(value = 3)
	public int column;
	@Parameter(value = 4)
	public double expected;

	// instance of Values2D
	private Values2D value;
	private Mockery mockery;
	
	@BeforeClass 
	public static void setUpBeforeClass() throws Exception {		
	}
	
	@Before
	public void setUp() throws Exception {
		mockery = new Mockery();
		value = mockery.mock(Values2D.class); // all test cases will use this mocked value variable with changed values.
		mockery.checking(new Expectations() {{
			allowing(value).getColumnCount(); will(returnValue(4));
			allowing(value).getRowCount(); will(returnValue(3));
			
			one(value).getValue(0, column); will(returnValue(val1));
			one(value).getValue(1, column); will(returnValue(val2));
			one(value).getValue(2, column); will(returnValue(val3));
		}});
	}

	// POSITIVE COLUMN VALUES
	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] {
			// Positive Values
			{ 2, 3, 4, 3, 9 }, 
			{ 10, 15, 20, 2, 45 }, 
			{ 123, 523, 200, 1, 846 },
			
			// All Negative Values
			{ -5,-3,-7,3,-15},
			{-12,-16,-10,2,-38},
			{-100,-222,-320,1,-642},
			
			// Mixed Values
			{-5,5,-2,3,-2},
			{70,-30,50,2,90}
		});
	}

	// Testing calculateColumnTotal method with column values having positive
	// values.
	// Result should be returning a total of the column's values that should all
	// return a positive total.
	@Test
	public void test_calculateColumnTotalMethod_AllPositive() {
		double actual = DataUtilities.calculateColumnTotal(value, column);

		assertEquals(expected, actual, 0);

		// assertEquals(13, DataUtilities.calculateColumnTotal(value, 2), .01d);
		mockery.assertIsSatisfied();
//		verify(value, times(3)).getValue(anyInt(), anyInt()); // verify that the getValue method is called exactly 3
//																// times
		// use verify only if that count makes sense.
	}
}
