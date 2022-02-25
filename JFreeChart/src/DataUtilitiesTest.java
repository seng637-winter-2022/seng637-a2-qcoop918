import static org.junit.Assert.*;

import org.jfree.data.*;
import org.jmock.*;
import org.junit.*;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;

public class DataUtilitiesTest {
	private double[] oldArray;
	private double[][] old2DArray;
	private java.lang.Number[] newArray;
	private java.lang.Number[][] new2DArray;
	
	// Variables for mocking
	private Mockery mockery;
	private KeyedValues kvalues;
	private Values2D value; // instance of Values2D
	

	@BeforeClass
	public static void setupBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		mockery = new Mockery();
		value = mockery.mock(Values2D.class); // all test cases will use this mocked value variable with changed values.
		kvalues = mockery.mock(KeyedValues.class);
		
		ArrayList<Integer> keyList = new ArrayList<Integer>(Arrays.asList(0,1,2));
		
		mockery.checking(new Expectations() {{
			allowing(value).getColumnCount(); will(returnValue(4)); // 3x4 whatever you want
			allowing(value).getRowCount(); will(returnValue(3));
			
			allowing(kvalues).getItemCount(); will(returnValue(3));
			
			allowing(kvalues).getValue(0); will(returnValue(-4));
			allowing(kvalues).getValue(1); will(returnValue(6));
			allowing(kvalues).getValue(2); will(returnValue(12));

			allowing(kvalues).getKey(0); will(returnValue(0));
			allowing(kvalues).getKey(1); will(returnValue(1));
			allowing(kvalues).getKey(2); will(returnValue(2));
			
			allowing(kvalues).getIndex(0); will(returnValue(0));
			allowing(kvalues).getIndex(1); will(returnValue(1));
			allowing(kvalues).getIndex(2); will(returnValue(2));

			allowing(kvalues).getKeys(); will(returnValue(keyList));
		}});
		
	}
	
	/* CALCULATECOLUMNTOTAL METHOD TEST CASES */

	// POSITIVE COLUMN VALUES
	// Testing calculateColumnTotal method with column values having positive
	// values.
	// Result should be returning a total of the column's values that should all
	// return a positive total.
	@Test
	public void test_calculateColumnTotal_AllPositive() {
		// (2,3,4) in column 3 => (9) All positive in column of array v1
		double val1 = 2;
		double val2 = 3;
		double val3 = 4;
		int column = 3;
		double expected = 9;
		mockery.checking(new Expectations() {{
			allowing(value).getColumnCount(); will(returnValue(3));
			allowing(value).getRowCount(); will(returnValue(3));
			
			one(value).getValue(0, column); will(returnValue(val1));
			one(value).getValue(1, column); will(returnValue(val2));
			one(value).getValue(2, column); will(returnValue(val3));
		}});
		double actual = DataUtilities.calculateColumnTotal(value, column);

		assertEquals(expected, actual, 0);

		mockery.assertIsSatisfied(); // verify that the getValue method is called exactly 3
																// times
	}

	// NEGATIVE COLUMN VALUES
	// Testing calculateColumnTotal method with column values having negative
	// values.
	// Result should be returning a total of the column's values that should all
	// return a negative total.
	@Test
	public void test_calculateColumnTotal_AllNegative() {
		// (-5,-3,-7) in column 3 => (-15) All negative in column of array v1
		double val1 = -5;
		double val2 = -3;
		double val3 = -7;
		int column = 3;
		double expected = -15;
		mockery.checking(new Expectations() {{
			allowing(value).getColumnCount(); will(returnValue(3));
			allowing(value).getRowCount(); will(returnValue(3));
			
			one(value).getValue(0, column); will(returnValue(val1));
			one(value).getValue(1, column); will(returnValue(val2));
			one(value).getValue(2, column); will(returnValue(val3));
		}});
		double actual = DataUtilities.calculateColumnTotal(value, column);

		assertEquals(expected, actual, 0);
	}

	// MIXED COLUMN VALUES
	// Testing calculateColumnTotal method with column values having mixed values of
	// either a negative or positive value.
	// Result should be returning a total of the column's values.
	@Test
	public void test_calculateColumnTotal_MixedValues() {
		// (-5,-3,-7) in column 3 => (-15) All negative in column of array v1
		double val1 = -5;
		double val2 = 5;
		double val3 = -2;
		int column = 2;
		double expected = -2;
		mockery.checking(new Expectations() {{
			allowing(value).getColumnCount(); will(returnValue(3));
			allowing(value).getRowCount(); will(returnValue(3));
			
			one(value).getValue(0, column); will(returnValue(val1));
			one(value).getValue(1, column); will(returnValue(val2));
			one(value).getValue(2, column); will(returnValue(val3));
		}});
		double actual = DataUtilities.calculateColumnTotal(value, column);

		assertEquals(expected, actual, 0);
	}

	// Testing calculateColumnTotal method with column values all to equal null.
	// Result should be returning a total of Zero.
	@Test
	public void test_calculateColumnTotal_InvalidInput() {
		// (null,null,null) in column 1 => 0 if invalid inputs are entered in Values2D
		// object, a total of zero should be returned
		
		mockery.checking(new Expectations() {{
			one(value).getValue(0, 1); will(returnValue(null));
			one(value).getValue(1, 1); will(returnValue(null));
			one(value).getValue(2, 1); will(returnValue(null));	
		}});
		double actual = DataUtilities.calculateColumnTotal(value, 1);
		double expected = 0.0;

		assertEquals(expected, actual, 0);
		
		mockery.assertIsSatisfied();
	}
	
	// Testing calculateColumnTotal method with Values2D object being null making it
	// an invalid object.
	// Result should be throwing an InvalidParameterException exception.
	// Cannot test with different data objects like a string 2D array or a double 2D
	// array because the method cannot process it at all
	// and provides a red underline on code warning that the test program will not
	// run.
	// It throws a NullPointerException instead of the InvalidParameterException
	// when tested.
	@Test(expected = InvalidParameterException.class)
	public void test_calculateColumnTotal_ExceptionThrows_InvalidParameterException() {
		value = null;
		DataUtilities.calculateColumnTotal(value, 1);
	}

	
	/* CALCULATEROWTOTAL METHOD TEST CASES */

	// POSITIVE ROW VALUES
	// Testing calculateRowTotal method with row values having positive values.
	// Result should be returning a total of the row's values that should all return
	// a positive total.
	@Test
	public void test_calculateRowTotalMethod_AllPositive() {
		// (2,3,4) in row 3 => (9) All positive in row of array v1
		double val1 = 2;
		double val2 = 3;
		double val3 = 4;
		int row = 3;
		double expected = 9;
		mockery.checking(new Expectations() {{
			allowing(value).getColumnCount(); will(returnValue(3));
			allowing(value).getRowCount(); will(returnValue(3));
			
			one(value).getValue(row, 0); will(returnValue(val1));
			one(value).getValue(row, 1); will(returnValue(val2));
			one(value).getValue(row, 2); will(returnValue(val3));
		}});
		double actual = DataUtilities.calculateRowTotal(value, row);

		assertEquals(expected, actual, 0);

		mockery.assertIsSatisfied(); // verify that the getValue method is called exactly 3
																// times
	}

	// NEGATIVE ROW VALUES
	// Testing calculateRowTotal method with row values having negative values.
	// Result should be returning a total of the row's values that should all return
	// a negative total.
	@Test
	public void test_calculateRowTotalMethod_AllNegative() {
		// (-5,-3,-7) in row 3 => (-15) All negative in row of array v1
		double val1 = -5;
		double val2 = -3;
		double val3 = -7;
		int row = 2;
		double expected = -15;
		mockery.checking(new Expectations() {{
			allowing(value).getColumnCount(); will(returnValue(3));
			allowing(value).getRowCount(); will(returnValue(3));
			
			one(value).getValue(row, 0); will(returnValue(val1));
			one(value).getValue(row, 1); will(returnValue(val2));
			one(value).getValue(row, 2); will(returnValue(val3));
		}});
		double actual = DataUtilities.calculateRowTotal(value, row);

		assertEquals(expected, actual, 0);

		mockery.assertIsSatisfied();
	}

	// MIXED ROW VALUES
	// Testing calculateRowTotal method with row values having mixed values of
	// either a negative or positive value.
	// Result should be returning a total of the row's values.
	@Test
	public void test_calculateRowTotalMethod_MixedValues() {
		// (-5,5,-2) in row 3 => (-2) 2 negative, 1 positive
		double val1 = -5;
		double val2 = 5;
		double val3 = -2;
		int row = 3;
		double expected = -2;
		mockery.checking(new Expectations() {{
			allowing(value).getColumnCount(); will(returnValue(3));
			allowing(value).getRowCount(); will(returnValue(3));
			
			one(value).getValue(row, 0); will(returnValue(val1));
			one(value).getValue(row, 1); will(returnValue(val2));
			one(value).getValue(row, 2); will(returnValue(val3));
		}});
		double actual = DataUtilities.calculateRowTotal(value, row);

		assertEquals(expected, actual, 0);
		mockery.assertIsSatisfied();
	}

	// Testing calculateRowTotal method with row values all to equal null.
	// Result should be returning a total of Zero.
	@Test
	public void test_calculateRowTotal_InvalidInput() {
		// (null,null,null) in row 1 => 0 if invalid inputs are entered in Values2D
		// object, a total of zero should be returned
		mockery.checking(new Expectations() {{
			allowing(value).getValue(with(equal(1)), with(any(Integer.class))); will(returnValue(null));	
		}});
		double actual = DataUtilities.calculateRowTotal(value, 1);
		double expected = 0.0;

		assertEquals(expected, actual, 0.1);
	}
	
	// Testing calculateRowTotal method with Values2D object being null making it an
	// invalid object.
	// Result should be throwing an InvalidParameterException exception.
	// Cannot test with different data objects like a string 2D array or a double 2D
	// array because the method cannot process it at all
	// and provides a red underline on code warning that the test program will not
	// run.
	@Test(expected = InvalidParameterException.class)
	public void test_calculateRowTotal_ExceptionThrows_InvalidParameterException() {
		// Pass a null Values2D object => Throws IllegalArgumentException
		value = null;
		DataUtilities.calculateRowTotal(value, 1);
	}

//	@Test
//	@CsvSource({ "-0.28, 0", // Testing negative number
//			"0.42, 1", // Testing regular number
//			"0.85, 2" // Testing regular number
//	})
//	public void getCumulativePercentageTest(double expected, int a) {
//		KeyedValues actual = DataUtilities.getCumulativePercentages(kvalues);
//
//		assertEquals(expected, actual.getValue(a).doubleValue(), 0.01d);
//	}

	@Test(expected = IllegalArgumentException.class)
	public void testCumulativePercentageException_throwsIllegalArgumentException() {
		kvalues = null;
		DataUtilities.getCumulativePercentages(kvalues);
	}

	/* CREATE NUMBER ARRAY */
	@Test
	public void createNumberArray_Length() {
		oldArray = new double[] { 1, 3, 4, 5, 6, 1, 0, 3, -4, -2, -10, 0.5, -0.5, 1.25, -1.25 };
		newArray = DataUtilities.createNumberArray(oldArray);

		assertEquals(newArray.length, oldArray.length);
	}

	@Test
	public void createNumberArray_single_test() {
		oldArray = new double[] { 1 };
		newArray = DataUtilities.createNumberArray(oldArray);
		for (int i = 0; i < newArray.length; i++) {
			assertNotNull(newArray[i]);
			assertEquals(oldArray[i], newArray[i].doubleValue(), 0);
		}
		;
	}

	@Test(expected = IllegalArgumentException.class)
	public void createNumberArray_null_test() {
		oldArray = null;
		newArray = DataUtilities.createNumberArray(oldArray);
	}

	@Test
	public void createNumberArray_test() {
		oldArray = new double[] { 1, 3, 4, 5, 6, 1, 0, 3, -4, -2, -10, 0.5, -0.5, 1.25, -1.25 };
		newArray = DataUtilities.createNumberArray(oldArray);

		for (int i = 0; i < newArray.length; i++) {
			assertNotNull(newArray[i]);
			assertEquals(oldArray[i], newArray[i].doubleValue(), 0);
		}
		;
	}

	/* CREATE NUMBER ARRAY 2D */
	@Test(expected = IllegalArgumentException.class)
	public void createNumberArray2D_null_test() {
		old2DArray = null;
		DataUtilities.createNumberArray2D(old2DArray);
	}

	@Test
	public void createNumberArray2D_single_test() {
		old2DArray = new double[][] { new double[] { 1 } };
		new2DArray = DataUtilities.createNumberArray2D(old2DArray);

		for (int i = 0; i < new2DArray.length; i++) {
			for (int e = 0; e < new2DArray[i].length; e++) {
				assertNotNull(new2DArray[i][e]);
				assertEquals(new2DArray[i][e].doubleValue(), old2DArray[i][e], 0);
			}
			;
		}
	}

	@Test
	public void createNumberArray2D_test() {
		old2DArray = new double[][] { new double[] { 1, 3, 4, 5, 6, 1, 0, 3, -4, -2, -10, 0.5, -0.5, 1.25, -1.25 },
				new double[] { 0.5, -0.5, 1.25, -1.25, 10, 1000000 }, new double[] { 0 }, };
		new2DArray = DataUtilities.createNumberArray2D(old2DArray);

		for (int i = 0; i < new2DArray.length; i++) {
			for (int e = 0; e < new2DArray[i].length; e++) {
				assertNotNull(new2DArray[i][e]);
				assertEquals(new2DArray[i][e].doubleValue(), old2DArray[i][e], 0);
			}
			;
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

}
