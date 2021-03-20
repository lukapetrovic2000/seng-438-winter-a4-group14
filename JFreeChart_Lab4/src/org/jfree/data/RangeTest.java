package org.jfree.data;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import java.security.InvalidParameterException;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import org.jfree.data.Range;
import static org.junit.Assert.*;
import org.junit.*;



/**
 * 
 * @author Group 14 Noah Bradley, Vu Minh Ha, Luka Petrovic & Yazan Chama
 *
 */

public class RangeTest {
	private Range testRange;
	private Range exampleRange;
	private Range exampleRange1;
	private Range exampleRange2;
	private Range exampleRange3;
	private Range range;
	@Rule
	public ExpectedException e = ExpectedException.none();
	
	
	
	public RangeTest() {}
	
	@Before
	public void setUp() throws Exception { //left parameter must be >= to right parameter to instantiate
		exampleRange = new Range(2.0, 10.0);
		exampleRange1 = new Range (-5, 5);
		exampleRange2 = new Range (0,0);
		exampleRange3 = new Range(-1, 1);
		range = new Range(0.0, 10.0);
	}
		/**
		 * Testing the Constructor
		 */
		//Test Scenario: RG1 - Expecting Compilation Error
		@Test (expected = Error.class)
		public void testingConstWithInvalidParams1() {
			testRange = new Range("ABC", -10.0);
		}
		//Test Scenario: RG2 - Expecting Compilation Error
		@Test (expected = Error.class)
		public void testingConstWithInvalidParams2() {
			testRange = new Range(3.0, "&");
		}
		//Test Scenario: RG3 - Expecting test to PASS
		@Test 
		public void testingConstWithValidParams() {
			testRange = new Range(-10.0, -6.3);
		}
		//Test Scenario: RG4 - Expecting IllegalArgumentException
		@Test 
		public void testConstructorWithInvalidParameters1() {
			e.expectMessage("Range(double, double): require lower (" + -6.3
	                + ") <= upper (" + -10.0 + ").");
			e.expect(IllegalArgumentException.class);
			testRange = new Range(-6.3, -10.0);
		}
		
		
		/**
		 * Testing the GetCentralValue() method
	 	 */
		
		//Test Scenario: GCV1
		
		@Test 
		public void testingGetCentralVal1Pos1Neg() {
			testRange = new Range(-15.0,5.0);
			double acutualRes = testRange.getCentralValue();
			double expectedRes = (-15.0 + (5.0))/2;
			assertEquals(expectedRes, acutualRes, 0.00);
		}
		//Test Scenario: GCV2
		@Test 
		public void testingGetCentralVal2PosVal() {
			testRange = new Range(3.0, 4.0);
			double acutualRes = testRange.getCentralValue();
			double expectedRes = (3.0 + (4.0))/2;
			assertEquals(expectedRes, acutualRes, 0.00);
		}
		//Test Scenario: GCV3
		@Test 
		public void testingGetCentralVal2NegVal() {
			testRange = new Range(-4.0, -3.0);
			double acutualRes = testRange.getCentralValue();
			double expectedRes = (-4.0 + (-3.0))/2;
			assertEquals(expectedRes, acutualRes, 0.00);
		}
		//Test Scenario: GCV4
		@Test 
		public void testingGetCentralVal2EqVal() {
			testRange = new Range(6.2, 6.2);
			double acutualRes = testRange.getCentralValue();
			double expectedRes = (6.2 + (6.2))/2;
			assertEquals(expectedRes, acutualRes, 0.00);
		}
		
		/**
		 * Testing the intersects() method
		 */
		
		// Test Scenario IN1 - Expect Compilation Error
		@Test (expected = Error.class)
		public void testIntersectsWrongType1() {
			testRange = new Range(-10, 10);
			boolean actual = testRange.intersects(3, "Test");
			assertTrue("Range 0-10 intersects 3 - 'Test'", actual);
		}
		
		// Test Scenario IN2 - Expect Compilation Error
		@Test (expected = Error.class)
		public void testIntersectsWrongType2() {
			testRange = new Range(-10, 10);
			boolean actual = testRange.intersects("424", 19);
			assertFalse("Range -10-10 intersects 20-25", actual);
		}
		
		// Test Scenario IN3
		@Test
		public void testIntersectsLowerThanRange() {
			testRange = new Range(-10, 10);
			boolean actual = testRange.intersects(-15, -11);
			assertFalse("Range -10-10 intersects -15 - (-11)", actual);
		}
		
		// Test Scenario IN4
		@Test
		public void testIntersects1() {
			testRange = new Range(-10, 10);
			boolean actual = testRange.intersects(-15, -10);
			assertFalse("Range -10-10 intersects -15 - (-10)", actual);
		}
		
		// Test Scenario IN5
		@Test
		public void testIntersects2() {
			testRange = new Range(-10, 10);
			boolean actual = testRange.intersects(-20, 0);
			assertTrue("Range -10-10 intersects -20 - 0", actual);
		}
		
		// Test Scenario IN6
		@Test
		public void testIntersects3() {
			testRange = new Range(-10, 10);
			boolean actual = testRange.intersects(-11, -10);
			assertFalse("Range -10-10 intersects -11 - (-10)", actual);
		}
		
		// Test Scenario IN7
		@Test
		public void testIntersectsSameRange() {
			testRange = new Range(-10, 10);
			boolean actual = testRange.intersects(-10, 10);
			assertTrue("Range -10-10 intersects -10 - (-10)", actual);
		}
		
		// Test Scenario IN8
		@Test
		public void testIntersectsInsideRange() {
			testRange = new Range(-10, 10);
			boolean actual = testRange.intersects(-5, 3);
			assertTrue("Range -10-10 intersects -5 - 3", actual);
		}
		
		// Test Scenario IN9
		@Test
		public void testIntersects4() {
			testRange = new Range(-10, 10);
			boolean actual = testRange.intersects(-10, 15);
			assertTrue("Range -10-10 intersects -10 - 15", actual);
		}
		
		// Test Scenario IN10
		@Test
		public void testIntersects5() {
			testRange = new Range(-10, 10);
			boolean actual = testRange.intersects(9, 11);
			assertFalse("Range -10-10 intersects 9 - 11", actual);
		}
		
		// Test Scenario IN11
		@Test
		public void testIntersects6() {
			testRange = new Range(-10, 10);
			boolean actual = testRange.intersects(10, 15);
			assertFalse("Range -10-10 intersects 10 - 15", actual);
		}
		
		// Test Scenario IN12
		@Test
		public void testIntersectsHigherThanRange() {
			testRange = new Range(-10, 10);
			boolean actual = testRange.intersects(12, 20);
			assertFalse("Range -10-10 intersects 12 - 20", actual);
		}
		
		// Test Scenario IN12
				@Test
				public void testIntersectsLowerThanRange() {
					testRange = new Range(-10, 10);
					boolean actual = testRange.intersects(-20, -12);
					assertFalse("Range -10-10 intersects 12 - 20", actual);
				}
		
		// Test Scenario IN13
			@Test
			public void testIntersectsMutation1() {
				testRange = new Range(-10, 10);
				boolean actual = testRange.intersects(5, 10);
				assertFalse("Range -10-10 intersects 5 - 10", actual);
			}
		
		// Test Scenario IN14
					@Test
					public void testIntersectsMutation2() {
						testRange = new Range(-10, 10);
						boolean actual = testRange.intersects(5, 5);
						assertTrue("Range -10-10 intersects 5 - 9", actual);
					}
					
		/* -----------------------------------------
		 * Tests: constrain()
		 ------------------------------------------*/
		
					// Test Scenario CS1
					@Test (expected = Error.class)
					public void testConstrainWrongType() {
						testRange = new Range(-5.5, 5.9);
						double actual = testRange.constrain("Test");
						assertEquals(5, actual, 0.00);
					}
					
					// Test Scenario CS2
					@Test
					public void testConstrainOutsideRange1() {
						testRange = new Range(-5.5, 5.9);
						double actual = testRange.constrain(-10.3);
						assertEquals(-5.5, actual, 0.00);
					}
					
					// Test Scenario CS3
					@Test
					public void testConstrainInsideRange1() {
						testRange = new Range(-5.5, 5.9);
						double actual = testRange.constrain(4.5);
						assertEquals(4.5, actual, 0.00);
					}
					
					// Test Scenario CS4
					@Test
					public void testConstrainOutsideRange2() {
						testRange = new Range(-5.5, 5.9);
						double actual = testRange.constrain(10);
						assertEquals(5.9, actual, 0.00);
					}
					
					// Test Scenario CS5
					@Test
					public void testConstrainAtLowerBound() {
						testRange = new Range(1, 3);
						double actual = testRange.constrain(1);
						assertEquals(1, actual, 0.00);
					}
					
					// Test Scenario CS6
					@Test
					public void testConstrainAtUpperBound() {
						testRange = new Range(1, 3.3);
						double actual = testRange.constrain(3.3);
						assertEquals(3.3, actual, 0.00);
					}
					
					// Test Scenario CS7
					@Test
					public void testConstrainSameLowerUpper1() {
						testRange = new Range(8.7, 8.7);
						double actual = testRange.constrain(8.6);
						assertEquals(8.7, actual, 0.00);
					}
					
					// Test Scenario CS8
					@Test
					public void testConstrainSameLowerUpper2() {
						testRange = new Range(8.7, 8.7);
						double actual = testRange.constrain(8.9);
						assertEquals(8.7, actual, 0.00);
					}
					
					// Test Scenario CS8
						@Test
						public void testConstrainAllSameNumber() {
							testRange = new Range(8.9, 8.9);
							double actual = testRange.constrain(8.9);
							assertEquals(8.9, actual, 0.00);
						}
		
		
		/*================================================================
						Testing: getLength()
		================================================================*/
		
		@Test
		public void sameValuesTest() {
			exampleRange = new Range(0, 0);
			assertEquals("The length of range (0, 0) should be 0",
					0, exampleRange.getLength(), .000000001d);
		}
		
		@Test
		public void oppositeValuesTest() {
			exampleRange = new Range(-5, 5);
			assertEquals("The length of range (-5, 5) should be 10",
					10, exampleRange.getLength(), .000000001d);
		}
		
		@Test
		public void negativePositiveTest() {
			exampleRange = new Range(-10, 1);
			assertEquals("The length of range (-10, 1) should be 11",
					11, exampleRange.getLength(), .000000001d);
		}
		
		@Test
		public void positiveNegativeTest() {
			exampleRange = new Range(-4, 0);
			assertEquals("The length of range (-4, 0) should be 4",
					4, exampleRange.getLength(), .000000001d);
		}
		
		/*================================================================
							Testing: getUpperBound()
		================================================================*/
		
		@Test
		public void testingRange() { //should work but its broken
			assertEquals("Upper bound of -5 and 5 should be 5", 5, exampleRange1.getUpperBound(), .000000001d);
		}
		
		@Test
		public void testEqualValues() {
			assertEquals("Upper bound of 0 and 0 should be 0", 0, exampleRange2.getUpperBound(), .000000001d);
		}
		
			/*================================================================
							Testing: getLowerBound()
			================================================================*/

@Test
public void testingLowerBoundRange() { //should work but its broken
assertEquals("Lower bound of -5 and 5 should be -5", -5, exampleRange1.getLowerBound(), .000000001d);
}

@Test
public void testingLowerBoundEquals() {
assertEquals("Upper bound of 0 and 0 should be 0", 0, exampleRange2.getLowerBound(), .000000001d);
}

		/*================================================================
							Testing: equals()
		================================================================*/
		//Tests if an equal object is treated as equal
		@Test
		public void equalsTrue() {
			Range otherRange = new Range(-1, 1);
			assertTrue("Ranges should be equal", exampleRange3.equals(otherRange));
		}
		
		//test if a non-equal object is identified as false
		@Test
		public void equalsFalse() {
			Range otherRange = new Range(-5, 5);
			assertFalse("Ranges should not be equal", exampleRange3.equals(otherRange));
		}
		
		//tests if self equality holds true
		@Test
		public void selfEquals() {
			assertTrue("Self equality should be true", exampleRange3.equals(exampleRange3));
		}
		
		//tests if a null value as input returns false 
		@Test
		public void nullEquals() {
			assertFalse("null input should not give true result", exampleRange3.equals(null));
		}
		
		//tests if a non-object input is handled and identified as non-equal
		@Test
		public void equalsInvalidInput() {
			int i = 5;
			assertFalse("should not equal invalid input", exampleRange3.equals(i));
		}
		
		@Test
		public void equalsNotInstanceTest() {
			exampleRange = new Range(-5, 1);
			double x = 3;
			assertFalse("exampleRange should not be equal to x since x is type double", exampleRange.equals(x));
		}
		
		@Test
		public void equalsTest() {
			exampleRange = new Range(-5, 1);
			Range exampleRange2 = new Range(-5, 1);
			assertTrue("exampleRange and exampleRange2 should be equal", exampleRange.equals(exampleRange2));
		}
		
		@Test
		public void equalsUpperTest() {
			exampleRange = new Range(-5, 1);
			Range exampleRange2 = new Range(-8, 1);
			assertFalse("ExampleRange should not be equal to exampleRange2 since lower bounds are different", exampleRange.equals(exampleRange2));
		}
		
		@Test
		public void equalsLowerTest() {
			exampleRange = new Range(-5, 1);
			Range exampleRange2 = new Range(-5, 0);
			assertFalse("ExampleRange should not be equal to exampleRange2 since upper bounds are different", exampleRange.equals(exampleRange2));
		}
		
		
		
		/**
		 * Testing the Contains() method
		 */
		
		// Test Scenario CT1 - Expecting Compilation Error
		@Test (expected = Error.class)
		public void testContainsWithStringValue() {
			testRange = new Range(-2.5, 5.9);
			boolean actual = testRange.contains("Test");
			assertTrue("Range -2.5 - 5.9 contains a String", actual);
		}
		
		// Test Scenario CT2
		@Test
		public void testContainsAtLowerBound1() {
			testRange = new Range(-2.5, 5.9);
			boolean actual = testRange.contains(-2.6);
			assertFalse("Testing if Range -2.5 - 5.9 contains -2.6", actual);
		}
		
		// Test Scenario CT3
		@Test
		public void testContainsAtLowerBound2() {
			testRange = new Range(-2.5, 5.9);
			boolean actual = testRange.contains(-2.5);
			assertTrue("Testing if Range -2.5 - 5.9 contains -2.5", actual);
		}
		
		// Test Scenario CT4
		@Test
		public void testContainsAtLowerBound3() {
			testRange = new Range(-2.5, 5.9);
			boolean actual = testRange.contains(-2.4);
			assertTrue("Testing if Range -2.5 - 5.9 contains -2.4", actual);
		}
		
		// Test Scenario CT5
		@Test
		public void testContainsAtMiddleOfRange() {
			testRange = new Range(-2.5, 5.9);
			boolean actual = testRange.contains(3.5);
			assertTrue("Testing if Range -2.5 - 5.9 contains 3.5", actual);
		}
		
		// Test Scenario CT6
		@Test
		public void testContainsAtUpperBound1() {
			testRange = new Range(-2.5, 5.9);
			boolean actual = testRange.contains(5.9);
			assertTrue("Testing if Range -2.5 - 5.9 contains 5.9", actual);
		}
		
		// Test Scenario CT7
		@Test
		public void testContainsAtUpperBound2() {
			testRange = new Range(-2.5, 5.9);
			boolean actual = testRange.contains(6.0);
			assertFalse("Testing if Range -2.5 - 5.9 contains 6.0", actual);
		}
		
		// Test Scenario CT8
		@Test
		public void testContainsAtUpperBound3() {
			testRange = new Range(-2.5, 5.9);
			boolean actual = testRange.contains(5.8);
			assertTrue("Testing if Range -2.5 - 5.9 contains 5.8", actual);
		}
		
		/*================================================================
							Testing: expand()
		================================================================*/
		
//		@Test(expected = IllegalArgumentException.class)
		@Test
		public void expandNullTest()
		{
			exampleRange = null;
			double lower = 4;
			double upper = 9;
//			e.expectMessage("Null 'range' argument.");
			e.expect(IllegalArgumentException.class);
			Range.expand(exampleRange, lower, upper);
		}
		
		@Test
		public void expandTest()
		{
			exampleRange = new Range(-3, 2);
			double lower = 4;
			double upper = 9;
			Range expanded = Range.expand(exampleRange, lower, upper);
			double expectedLower = -23;
			double expectedUpper = 47;
			assertNotNull("expanded should not be null", expanded);
			assertEquals("In expandTest(), expanded.getLowerBound() should be equal to expectedLower", expectedLower, expanded.getLowerBound(), 0);
			assertEquals("In expandTest(), expanded.getUpperBound() should be equal to expectedUpper", expectedUpper, expanded.getUpperBound(), 0);
		}
		
		
		/*================================================================
					Testing: combine()
		================================================================*/
		
		@Test
		public void combineTestFirstNull() {
			exampleRange = new Range(-1, 1);
			Range exampleRange2 = null;
			Range combined = Range.combine(exampleRange2, exampleRange);
			double expectedUpper = 1;
			double expectedLower = -1;
			assertNotNull("combined should not be null", combined);
			assertEquals("In combineTestFirstNull() combined.getUpperBound() should be equal to expectedUpper", expectedUpper, combined.getUpperBound(), 0);
			assertEquals("In combineTestFirstNull() combined.getLowerBound() should be equal to expectedLower", expectedLower, combined.getLowerBound(), 0);
		}
		
		@Test
		public void combineTestSecondNull() {
			exampleRange = new Range(-1, 1);
			double expectedUpper = 1;
			double expectedLower = -1;
			Range combined = Range.combine(exampleRange, null);
			assertEquals("In combineSecondNull() combined.getUpperBound() should be equal to expectedUpper", expectedUpper, combined.getUpperBound(), 0);
			assertEquals("In combineTestFirstNull() combined.getLowerBound() should be equal to expectedLower", expectedLower, combined.getLowerBound(), 0);
		}
		
		@Test
		public void combineTestNotNull() {
			exampleRange = new Range(-1, 1);
			Range exampleRange2 = new Range(-2, 2);
			Range combined = Range.combine(exampleRange, exampleRange2);
			double expectedUpper = 2;
			double expectedLower = -2;
			assertNotNull("combined should not be null", combined);
			assertEquals("In combineTestNotNull() combined.getUpperBound() should be equal to expectedUpper", expectedUpper, combined.getUpperBound(), 0);
			assertEquals("In combineTestNotNull() combined.getLowerBound() should be equal to expectedLower", expectedLower, combined.getLowerBound(), 0);
		}
		
		@Test
		public void combineTestBothNull() {
			exampleRange = null;
			Range exampleRange2 = null;
			Range combined = Range.combine(exampleRange, exampleRange2);
			assertNull("combined should be null", combined);
		}
		
		//Testing expandToInclude RANGE: (-1,1)
		
		@Test
		public void ExpandToIncludeNumberLessThanLowerBoundTest() {
			assertEquals("The returned object is not equal to what was expected ", new Range(1.0, 10.0), Range.expandToInclude(this.exampleRange, 1.0));
		}
		
		/**
		 * Test if the range will expand to include a number that is greater than the upper bound
		 */
		@Test
		public void ExpandToIncludeNumberMoreThanUpperBoundTest() {
			assertEquals("The returned object is not equal to what was expected ", new Range(2.0, 100.0), Range.expandToInclude(this.exampleRange, 100.0));
		}
		
		/**
		 * Test if the range will stay the same if it trys to include a number that is already in range
		 */
		@Test
		public void ExpandToIncludeNumberAlreadyInRangeTest() {
			assertEquals("Number is not included in this range ", this.exampleRange, Range.expandToInclude(this.exampleRange, 5.0));
		}
		
		@Test
		public void ExpandToIncludeNumberMutation1() {
			assertEquals("Number is not included in this range ", this.exampleRange, Range.expandToInclude(this.exampleRange, 2.0));
		}
		
		
		/**
		 * Test if a null range will expand to include only the single number that is passed to it
		 */
		@Test
		public void RangeIsNullTest() {
			assertEquals("Ranges are not equal", new Range(5.0, 5.0), Range.expandToInclude(null, 5.0));
		}
		
		//Testing Testing shift two parameters
		
		@Test
		public void testDeltaPositive() {
			Range resultRange = exampleRange1.shift(exampleRange1, 4);
			Range expectedRange = new Range(-1, 9);
			assertTrue("New range should match custom range", resultRange.equals(expectedRange));
		}
		
		@Test
		public void testDeltaNegative() {
			Range resultRange = exampleRange1.shift(exampleRange1, -4);
			Range expectedRange = new Range(-9, 1);
			assertTrue("New range should match custom range", resultRange.equals(expectedRange));
		}
		
		@Test
		public void testDeltaZero() {
			Range resultRange = exampleRange1.shift(exampleRange1, 0);
			assertTrue("New range should match custom range", resultRange.equals(exampleRange1));
		}
		
		@Test
		public void testLowerCrossZero() {
			Range resultRange = exampleRange1.shift(exampleRange1, 6);
			Range expectedRange = new Range(0, 11);
			assertTrue("New range should match custom range", resultRange.equals(expectedRange));
		}
		
		@Test
		public void testUpperCrossZero() {
			Range resultRange = exampleRange1.shift(exampleRange1, -8);
			Range expectedRange = new Range(-13, 0);
			assertTrue("New range should match custom range", resultRange.equals(expectedRange));
		}
		
		@Test
		public void testShiftMutation1() {
			Range testRange = new Range(0.1, 0.9);
			Range result = Range.shift(testRange, -1, false);
			Range expectedRange = new Range(0, 0);
			assertTrue("New range should match custom range", result.equals(expectedRange));
		}
		
		@Test
		public void testShiftMutation2() {
			Range testRange = new Range(-0.9, -0.1);
			Range result = Range.shift(testRange, 1, false);
			Range expectedRange = new Range(0, 0);
			assertTrue("New range should match custom range", result.equals(expectedRange));
		}
		
		@Test
		public void testShiftMutation3() {
			Range testRange = new Range(0.0, 5.0);
			Range result = Range.shift(testRange, -1, false);
			Range expectedRange = new Range(-1.0, 4.0);
			assertTrue("New range should match custom range", result.equals(expectedRange));
		}
		
		
		// Testing shift method with three paramters
		
		@Test
		public void testDeltaPositiveTrue() {
			Range resultRange = exampleRange1.shift(exampleRange1, 6, true);
			Range expectedRange = new Range(1, 11);
			assertTrue("New range should match custom range", resultRange.equals(expectedRange));
		}
		
		@Test
		public void testDeltaNegativeTrue() {
			Range resultRange = exampleRange1.shift(exampleRange1, -10, true);
			Range expectedRange = new Range(-15, -5);
			assertTrue("New range should match custom range", resultRange.equals(expectedRange));
		}
		
		@Test
		public void testDeltaZeroTrue() {
			Range resultRange = exampleRange1.shift(exampleRange1, 0, true);
			assertTrue("New range should match custom range", resultRange.equals(exampleRange1));
		}
		
		@Test
		public void testLowerCrossZeroTrue() {
			Range resultRange = exampleRange1.shift(exampleRange1, 7, true);
			Range expectedRange = new Range(2, 12);
			assertTrue("New range should match custom range", resultRange.equals(expectedRange));
		}
		
		@Test
		public void testUpperCrossZeroTrue() {
			Range resultRange = exampleRange1.shift(exampleRange1, -8, true);
			Range expectedRange = new Range(-13, -3);
			assertTrue("New range should match custom range", resultRange.equals(expectedRange));
		}
		
		@Test
		public void testZeroValueZeroFalse() {
			Range resultRange = exampleRange1.shift(exampleRange1, 0, false);
			assertTrue("New range should match custom range", exampleRange1.equals(resultRange));
		}
		

		/* -----------------------------------------
		 * toString() tests
		 ------------------------------------------*/
		@Test
		public void testToString() {
			testRange = new Range(-2, 3);
			String expected = "Range[-2.0,3.0]";
			assertEquals("Strings should be equivalent", expected, testRange.toString());;
		}
		
		@Test
	    public void testHashCode() {
	        testRange = new Range(-1, 1);
	        assertNotNull(testRange.hashCode());
	    }
		

		
		@After
		public void tearDown() throws Exception {
			System.out.println("Tearing down object...");
			exampleRange  = null;
			testRange 	  = null;
			exampleRange1 = null;
			exampleRange2 = null;
			exampleRange3 = null;
		}
		
		
	}


