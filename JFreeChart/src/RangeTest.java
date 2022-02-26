import org.jfree.data.Range;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.*;


public class RangeTest {
	private Range exampleRange;
	private Range exampleRange1;
	private Range exampleRange2;
	private Range expected;
	private double expectedValue;
	private double expectedValue2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	/* COMBINE METHOD TEST CASES */

	// Testing combine method with 2 Ranges that are positive and do not overlap.
	// Result should be a combined range of their lower and upper bound values.
	@Test
	public void test_CombineMethod_PositiveNoOverlap() {
		// (3,5) (7, 13) -> (3,13)
		exampleRange1 = new Range(3, 5);
		exampleRange2 = new Range(7, 13);
		expected = new Range(3, 13);
		Range actual = Range.combine(exampleRange1, exampleRange2);

		assertEquals(expected, actual);
		//"Combining (3,5) with (7,13) should result in (3,13)"
	}

	// Testing combine method with 2 Ranges that are positive but overlaps with each
	// other.
	// Result should be a combined range of their lower and upper bound values.
	@Test
	public void test_CombineMethod_PositivewithOverlap() {
		// (3,5) (4, 13) -> (3,13)
		exampleRange1 = new Range(3, 5);
		exampleRange2 = new Range(4, 13);
		expected = new Range(3, 13);
		Range actual = Range.combine(exampleRange1, exampleRange2);

		assertEquals(expected, actual);
		//"Combining (3,5) with (4,13) should result in (3,13)";
	}

	// Testing combine method with 2 Ranges that are positive but overlaps with each
	// other.
	// Result should be a combined range of their lower and upper bound values.
	@Test
	public void test_CombineMethod_PositivewithOverlap2() {
		// (3,15) (1,7) -> (1,15)
		exampleRange1 = new Range(3, 15);
		exampleRange2 = new Range(1, 7);
		expected = new Range(1, 15);
		Range actual = Range.combine(exampleRange1, exampleRange2);

		assertEquals(expected, actual);
		//"Combining (3,15) with (1,7) should result in (1,15)"
	}

	// Testing combine method with 2 Ranges that are positive but overlaps with each
	// other.
	// Result should be a combined range of their lower and upper bound values.
	@Test
	public void test_CombineMethod_PositivewithOverlap3() {
		// (3,15) (4,6) -> (3,15)
		exampleRange1 = new Range(3, 15);
		exampleRange2 = new Range(4, 6);
		expected = new Range(3, 15);
		Range actual = Range.combine(exampleRange1, exampleRange2);

		assertEquals(expected, actual);
		//"Combining (3,15) with (4,6) should result in (3,15)"
	}

	// Testing combine method with 2 Ranges that are positive but overlaps with each
	// other.
	// Result should be a combined range of their lower and upper bound values.
	@Test
	public void test_CombineMethod_PositivewithOverlap4() {
		// (7,12) (3,8) -> (3,12)
		exampleRange1 = new Range(7, 12);
		exampleRange2 = new Range(3, 8);
		expected = new Range(3, 12);
		Range actual = Range.combine(exampleRange1, exampleRange2);

		assertEquals(expected, actual);
		//"Combining (7,12) with (3,8) should result in (3,12)"
	}

	// Testing combine method with 2 Similarly Valued Ranges.
	// Result should be the same range.
	@Test
	public void test_CombineMethod_SimilarRanges() {
		// (3,5) (3,5) -> (3,5)
		exampleRange1 = new Range(3, 5);
		exampleRange2 = new Range(3, 5);
		expected = new Range(3, 5);
		Range actual = Range.combine(exampleRange1, exampleRange2);

		assertEquals(expected, actual);
		//"Combining (3,5) with (3,5) should result in (3,5)"
	}

	// Possible Test Case -- Invalid
	// Testing combine method with one range being invalid because the upper bound
	// is less than lower bound. (upper < lower)
	// Result should throw an exception due to the invalid creation of first range
	// (5,3) (7, 13) -> Throw Exception -> (invalid)

	// Testing combine method with 2 Ranges that are of null value.
	// Result should be returning a null range.
	@Test
	public void test_CombineMethod_TwoNullRanges() {
		// (null) (null) -> (null)
		exampleRange1 = null;
		exampleRange2 = null;
		expected = null;
		Range actual = Range.combine(exampleRange1, exampleRange2);

		assertEquals(expected, actual);
		//"Combining (null) with (null) should result in (null)"
		assertNull(actual);
	}

	// Testing combine method with 1 Range that is of null value and the other an
	// actual range.
	// Result should be returning the range that exists from the two values passed.
	@Test
	public void test_CombineMethod_OneNullRange() {
		// (3,5) (null) -> (3,5) and
		// (null) (3,5) -> (3,5)
		exampleRange1 = new Range(3, 5);
		exampleRange2 = null;
		expected = new Range(3, 5);
		Range actual = Range.combine(exampleRange1, exampleRange2);
		Range actual2 = Range.combine(exampleRange2, exampleRange1);

		assertEquals(expected, actual);
		//"Combining (3,5) with (null) should result in (3,5)"
		assertEquals(expected, actual2);
		//"Combining (null) with (3,5) should result in (3,5)"
	}

	/* SHIFT METHOD (2 parameters) TEST CASES */

	// Testing shift method with Range parameter being null.
	// Result should be returning the InvalidParameterException.
	@Test (expected = InvalidParameterException.class)
	public void test_ShiftMethod_OneNullValue() {
		// (null) (2) -> InvalidParameterException Throws an exception if null Range
		// base parameter is passed in
			exampleRange1 = null;
			Range.shift(exampleRange1, 2);
	}

	// (5,10) (null) -> InvalidParameterException Throws an exception if null object
	// is passed in

	// Testing shift method with 1 Positive Range that is shifted by a positive
	// value.
	// Result should be returning the range with its lower and upper bound shifted
	// by the value passed.
	@Test
	public void test_ShiftMethod_PositiveRangeValue() {
		// (2,6) (2) -> (4,8) Positive Range shifted by a positive value
		exampleRange1 = new Range(2, 6);
		expected = new Range(4, 8);
		Range actual = Range.shift(exampleRange1, 2);

		assertEquals(expected, actual);
		//"Range should be shifted to (4,8)"
	}

	// Testing shift method with 1 Positive Range that is shifted by a negative
	// value.
	// Result should be returning the range with its lower and upper bound shifted
	// by the value passed.
	@Test
	public void test_ShiftMethod_PositiveRange_NegativeValue() {
		// (5,10) (-5) -> (0, 5) Positive Range shifted by a negative value
		exampleRange1 = new Range(5, 10);
		expected = new Range(0, 5);
		Range actual = Range.shift(exampleRange1, -5);

		assertEquals(expected, actual);
		//"Range should be shifted to (0,5)"
	}

	// Testing shift method with 1 Positive Range that is shifted by a negative
	// value.
	// Result should be returning the range with its lower and upper bound shifted
	// by the value passed.
	// Zero crossing is not allowed in this method so if the upper or lower bound
	// crosses 0, the upper or lower bound values will change to 0.
	@Test
	public void test_ShiftMethod_PositiveRange_NegativeValue2() {
		// (0 2) (-3) -> (0, 0) Positive Range shifted by a negative value (Does not
		// allow zero crossing so lower/upper becomes 0)
		exampleRange1 = new Range(0, 2);
		expected = new Range(0, 0);
		Range actual = Range.shift(exampleRange1, -3);

		assertEquals(expected, actual);
		//"Range should be shifted to (0,0)"
	}

	// Testing shift method with 1 Negative Range that is shifted by a negative
	// value.
	// Result should be returning the range with its lower and upper bound shifted
	// by the value passed.
	@Test
	public void test_ShiftMethod_NegativeRange_NegativeValue() {
		// (-5, -3) (-6) -> (-11, -9) Negative Range shifted by a negative value
		exampleRange1 = new Range(-5, -3);
		expected = new Range(-11, -9);
		Range actual = Range.shift(exampleRange1, -6);

		assertEquals(expected, actual);
		//"Range should be shifted to (-11,-9)"
	}

	// Testing shift method with 1 Negative Range that is shifted by a positive
	// value.
	// Result should be returning the range with its lower and upper bound shifted
	// by the value passed.
	// Zero crossing is not allowed in this method so if the upper or lower bound
	// crosses 0, the upper or lower bound values will change to 0.
	@Test
	public void test_ShiftMethod_NegativeRange_PositiveValue() {
		// (-3, -1) (5) -> (0, 0) Negative Range shifted by a positive value (Does not
		// allow zero crossing so lower/upper becomes 0)
		exampleRange1 = new Range(-3, -1);
		expected = new Range(0, 0);
		Range actual = Range.shift(exampleRange1, 5);

		assertEquals(expected, actual);
		//"Range should be shifted to (0,0)"
	}

	/* CONSTRAIN METHOD TEST CASES */

	// Testing constrain method with 1 Positive Range using constrain on a value
	// existing within the range.
	// Result should be returning the existing passed value
	@Test
	public void test_ConstrainMethod_PositiveRange_Middle() {
		// (10,15) (13) -> (13) Positive Range with value within Range returns specified
		// value
		exampleRange1 = new Range(10, 15);
		expectedValue = 13;
		double actual = exampleRange1.constrain(13);

		assertEquals(expectedValue, actual, 0);
		//"constrained result should be 13"
	}

	// Testing constrain method with 1 Positive Range using constrain on a value
	// existing outside the Upper Bound value.
	// Result should be returning the upperBound value
	@Test
	public void test_ConstrainMethod_PositiveRange_UpperBound() {
		// (10,15) (18) -> (15) Positive Range with value outside upperBound range
		// returns upperBound value
		exampleRange1 = new Range(10, 15);
		expectedValue = 15;
		double actual = exampleRange1.constrain(18);

		assertEquals(expectedValue, actual, 0);
		//"constrained result should be 15"
	}

	// Testing constrain method with 1 Positive Range using constrain on a value
	// existing outside the Lower Bound value.
	// Result should be returning the lowerBound value
	@Test
	public void test_ConstrainMethod_PositiveRange_LowerBound() {
		// (10,15) (7) -> (10) Positive Range with value outside lowerBound range
		// returns lowerBound value
		exampleRange1 = new Range(10, 15);
		expectedValue = 10;
		double actual = exampleRange1.constrain(7);

		assertEquals(expectedValue, actual, 0);
		//"constrained result should be 10"
	}

	// Testing constrain method with 1 Positive Range using constrain on a values
	// being exactly the upper and lower bound values.
	// Result should be returning the lowerBound or upperBound value
	@Test
	public void test_ConstrainMethod_PositiveRange_EdgeBounds() {
		// (10, 15) (10) & (15) -> (10) (15) Positive Range testing edge cases of
		// upper/lower bound value to return upper/lower values.
		exampleRange1 = new Range(10, 15);
		expectedValue = 10;
		expectedValue2 = 15;

		double actual = exampleRange1.constrain(10);
		double actual2 = exampleRange1.constrain(15);

		assertEquals(expectedValue, actual, 0);
		//"constrained result should be 10"
		assertEquals(expectedValue2, actual2, 0);
		//"constrained result should be 15"
	}

	// Testing constrain method with 1 Negative Range using constrain on a value
	// existing within the range.
	// Result should be returning the existing passed value
	@Test
	public void test_ConstrainMethod_NegativeRange_Middle() {
		// (-8,-4) (-5) -> (-5) Negative range with value within Range returns specified
		// value
		exampleRange1 = new Range(-8, -4);
		expectedValue = -5;
		double actual = exampleRange1.constrain(-5);

		assertEquals(expectedValue, actual, 0);
		//"constrained result should be -5"
	}

	// Testing constrain method with 1 Negative Range using constrain on a value
	// existing outside the Upper Bound value.
	// Result should be returning the upperBound value
	@Test
	public void test_ConstrainMethod_NegativeRange_UpperBound() {
		// (-8,-4) (-2) -> (-4) Negative range with value outside upperBound range
		// returns upperBound value
		exampleRange1 = new Range(-8, -4);
		expectedValue = -4;
		double actual = exampleRange1.constrain(-2);

		assertEquals(expectedValue, actual, 0);
		//"constrained result should be -4"
	}

	// Testing constrain method with 1 Negative Range using constrain on a value
	// existing outside the Lower Bound value.
	// Result should be returning the lowerBound value
	@Test
	public void test_ConstrainMethod_NegativeRange_LowerBound() {
		// (-8,-4) (-20) -> (-8) Negative range with value outside lowerBound range
		// returns lowerBound value
		exampleRange1 = new Range(-8, -4);
		expectedValue = -8;
		double actual = exampleRange1.constrain(-20);

		assertEquals(expectedValue, actual, 0);
		//"constrained result should be -8"
	}

	// Testing constrain method with 1 Negative Range using constrain on a values
	// being exactly the upper and lower bound values.
	// Result should be returning the lowerBound or upperBound value
	@Test
	public void test_ConstrainMethod_NegativeRange_EdgeBounds() {
		// (-8, -4) (-8) & (-4) -> (-8) (-4) Negative Range testing edge cases of
		// upper/lower bound value to return upper/lower values.
		exampleRange1 = new Range(-8, -4);
		expectedValue = -8;
		expectedValue2 = -4;

		double actual = exampleRange1.constrain(-8);
		double actual2 = exampleRange1.constrain(-4);

		assertEquals(expectedValue, actual, 0);
		//"constrained result should be -8"
		assertEquals(expectedValue2, actual2, 0);
		//"constrained result should be -4"
	}

	/* CONTAINS METHOD TEST CASES */

	// Testing contains method with verifying that using a Positive Range with the
	// LowerBound value to check its existence in the range.
	// Result should be True.
	@Test
	public void test_ContainsMethod_PositiveRange_LowerBound() {
		// (2,10) (2) -> Returns True: LowerBound value is checked
		exampleRange1 = new Range(2, 10);
		assertTrue(exampleRange1.contains(2));
	}

	// Testing contains method with verifying that using a Positive Range with the
	// UpperBound value to check its existence in the range.
	// Result should be True.
	@Test
	public void test_ContainsMethod_PositiveRange_UpperBound() {
		// (2,10) (10) -> Returns True: UpperBound value is checked
		exampleRange1 = new Range(2, 10);
		assertTrue(exampleRange1.contains(10));
	}

	// Testing contains method with verifying that using a Positive Range with any
	// value within the Range to check its existence.
	// Result should be True.
	@Test
	public void test_ContainsMethod_PositiveRange_Middle() {
		// (2,10) (5) -> Returns True: A value existing in between lower and upper bound
		// values in range.
		exampleRange1 = new Range(2, 10);
		assertTrue(exampleRange1.contains(5));
	}

	// Testing contains method with verifying that checking if an invalid value
	// below lower bound exists in a Positive Range.
	// Result should be False.
	@Test
	public void test_ContainsMethod_InvalidValue_PositiveRange_LowerBound() {
		// (5, 8) (3) -> Returns False: checking number below lower bound value of range
		exampleRange1 = new Range(5, 8);
		assertFalse(exampleRange1.contains(3));
	}

	// Testing contains method with verifying that checking if an invalid value
	// above upper bound exists in a Positive Range.
	// Result should be False.
	@Test
	public void test_ContainsMethod_InvalidValue_PositiveRange_UpperBound() {
		// (5, 8) (11) -> Returns False: checking number above upper bound value of
		// range
		exampleRange1 = new Range(5, 8);
		assertFalse(exampleRange1.contains(11));
	}

	// Testing contains method with verifying that using a Negative Range with the
	// LowerBound value to check its existence in the range.
	// Result should be True.
	@Test
	public void test_ContainsMethod_NegativeRange_LowerBound() {
		// (-6,-2) (-6) -> Returns True: Negative Range Negative Lower Bound is checked
		exampleRange1 = new Range(-6, -2);
		assertTrue(exampleRange1.contains(-6));
	}

	// Testing contains method with verifying that using a Negative Range with the
	// UpperBound value to check its existence in the range.
	// Result should be True.
	@Test
	public void test_ContainsMethod_NegativeRange_UpperBound() {
		// (-6, -2) (-2) -> Returns True: Negative Range Negative Upper Bound is checked
		exampleRange1 = new Range(-6, -2);
		assertTrue(exampleRange1.contains(-2));
	}

	// Testing contains method with verifying that using a Negative Range with any
	// value within the Range to check its existence.
	// Result should be True.
	@Test
	public void test_ContainsMethod_NegativeRange_Middle() {
		// (-6, -2) (-4) -> Returns True: Negative Range Middle
		exampleRange1 = new Range(-6, -2);
		assertTrue(exampleRange1.contains(-4));
	}

	// Testing contains method with verifying that checking if an invalid value
	// below lower bound exists in a Negative Range.
	// Result should be False.
	@Test
	public void test_ContainsMethod_InvalidValue_NegativeRange_LowerBound() {
		// (-12, -8) (-15) -> Returns False: Negative Range Below Lower Bound
		exampleRange1 = new Range(-12, -8);
		assertFalse(exampleRange1.contains(-15));
	}

	// Testing contains method with verifying that checking if an invalid value
	// above upper bound exists in a Negative Range.
	// Result should be False.
	@Test
	public void test_ContainsMethod_InvalidValue_NegativeRange_UpperBound() {
		// (-12, -8) (-6) -> Returns False: Negative Range Above Upper Bound
		exampleRange1 = new Range(-12, -8);
		assertFalse(exampleRange1.contains(-6));
	}

	/* EQUALS METHOD TEST CASES */

	// Testing equals method with similar Positive Ranges.
	// Result should be True.
	@Test
	public void test_EqualsMethod_PositiveRange_SimilarValues() {
		// (3,5) (3,5) -> Returns True: Positive Range Similar Values
		exampleRange1 = new Range(3, 5);
		exampleRange2 = new Range(3, 5);
		assertTrue(exampleRange1.equals(exampleRange2));
	}

	// Testing equals method with different Positive Ranges.
	// Result should be False.
	@Test
	public void test_EqualsMethod_PositiveRange_DifferentValues() {
		// (3,5) (7,10) -> Returns False: Positive Ranges Different Values
		exampleRange1 = new Range(3, 5);
		exampleRange2 = new Range(7, 10);
		assertFalse(exampleRange1.equals(exampleRange2));
	}

	// Testing equals method with similar Null Ranges. Null is permitted to function
	// in this method. So the equals method should execute.
	// Result should be False.
	@Test
	public void test_EqualsMethod_OneNullRange_OneActualRange() {
		// (12,15) (null) -> Returns False: One null different values
		// (null) (12,15) -> Returns False: One null different values
		exampleRange1 = new Range(12, 15);
		exampleRange2 = null;

		assertFalse(exampleRange1.equals(exampleRange2));
	}

	// Testing equals method with similar Negative Ranges.
	// Result should be True.
	@Test
	public void test_EqualsMethod_NegativeRange_SimilarValues() {
		// (-5,-1) (-5,-1) -> Returns True: Negative Ranges Similar Values
		exampleRange1 = new Range(-5, -1);
		exampleRange2 = new Range(-5, -1);
		assertTrue(exampleRange1.equals(exampleRange2));
	}

	// Testing equals method with different Negative Ranges.
	// Result should be False.
	@Test
	public void test_EqualsMethod_NegativeRange_DifferentValues() {
		// (-5,-1) (-20, -17) -> Returns False: Negative Ranges Different Values
		exampleRange1 = new Range(-5, -1);
		exampleRange2 = new Range(-20, -17);
		assertFalse(exampleRange1.equals(exampleRange2));
	}

	// Testing equals method with 1 different Positive Range and 1 different
	// Negative Range.
	// Result should be False.
	@Test
	public void test_EqualsMethod_PositiveandNegativeRange_DifferentValues() {
		// (6,8) (-9, -7) -> Returns False: One Positive and One Negative different
		// Values
		exampleRange1 = new Range(6, 8);
		exampleRange2 = new Range(-9, -7);
		assertFalse(exampleRange1.equals(exampleRange2));
	}

	/* EXPAND TEST */
	// testing expand to create a faulty range of 3-2 should throw error
	@Test (expected = IllegalArgumentException.class)
	public void expand_ErrorRangeTest() {
		
		exampleRange = Range.expand(new Range(2, 6), -0.25, -1);
		//"The lowerrange should be 3, the upperrange 2"
	}

	// testing if null value is not permitted like it says in the api
	@Test (expected = IllegalArgumentException.class)
	public void expand_NullRangeTest() {
		
			exampleRange = Range.expand(null, -0.25, -1);
		//"The lowerrange should be 3, the upperrange 2"
	}

	public void expand_example_test() {
		double origLower = 2;
		double origHigher= 6;
		double lowerMod =0.25;
		double higherMod = 0.5; 
		double newLower = 1;
		double newHigher = 8;
		exampleRange = Range.expand(new Range(origLower, origHigher), lowerMod, higherMod);
		assertEquals(newLower, exampleRange.getLowerBound(), 0);
		assertEquals(newHigher, exampleRange.getUpperBound(), 0);
	}

	public void expandToInclude_example_test(double origLower, double origHigher, double newValue, double newLower,
			double newHigher, String expected) {
		exampleRange = Range.expandToInclude(new Range(1, 10), 5);
		assertEquals(true, exampleRange.contains(5));
		assertEquals(1, exampleRange.getLowerBound(), 0);
		assertEquals(10, exampleRange.getUpperBound(), 0);
	}

	public void expandToInclude_null_test() {
		exampleRange = Range.expandToInclude(null, 0);
		assertEquals(true, exampleRange.contains(0));
	}

	/* CONTAINS TEST */
	public void contains_true_test() {
		exampleRange = new Range(-1, 0);
		assertEquals(true, exampleRange.contains(0));
	}

	public void contains_false_test() {
		exampleRange = new Range(5, 10);
		assertEquals(false, exampleRange.contains(3));
	}

	/* GET CENTRAL VALUE TEST */

	public void getCentralValue_example_test() {
		exampleRange = new Range(0, 10);
		assertEquals(5, exampleRange.getCentralValue(), 0);
	}

	/* GET LENGTH TEST */
	public void getLength_example_test() {
		exampleRange = new Range(0, 10);
		assertEquals(10, exampleRange.getLength(),0);
	}

	@Test (expected = NullPointerException.class)
	public void getLength_voidRange_test() {
		exampleRange = null;
		
		exampleRange.getLength();
		
	}

	/* TO STRING TEST */
	public void toString_example_test() {
		exampleRange = new Range(-1000000, 1000000);
		assertEquals("Range[" + -1000000 + "," + 1000000 + "]", exampleRange.toString());
	}

	@Test (expected = NullPointerException.class)
	public void toString_voidRange_test() {
		exampleRange = null;
		
		exampleRange.toString();
		
	}

	@Test (expected = InvalidParameterException.class)
	public void testShiftException() {
		exampleRange = null;
		Range.shift(exampleRange, 1, true);
	}

	public void testLowerBound() {
		exampleRange = new Range(-3, 10);
		assertEquals(-3, exampleRange.getLowerBound(), 0);
	}

	public void testUpperBound() {
		exampleRange = new Range(-10, 9);
		assertEquals(9, exampleRange.getUpperBound(), 0);
	}


	// Testing when ranges intersect at 1 point
	public void testIntersects() {
		exampleRange = new Range(-10, -3);
		assertEquals(expected, exampleRange.intersects(-3, 11));
	}
	public void testShift() {
		exampleRange = new Range(-5, -1);
		Range expectedRange = new Range(-1, 0);
		assertEquals(expectedRange, Range.shift(exampleRange, 4, false));
	}

	@After
	public void tearDown() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

}
