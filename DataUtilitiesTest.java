package org.jfree.data;

import static org.junit.Assert.*;


import java.security.InvalidParameterException;
import java.util.Random;

import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Before;
import org.junit.BeforeClass;


import org.jmock.Mockery;
import org.jmock.Expectations;

/**
 * 
 * @author Group 14 Noah Bradley, Vu Minh Ha, Luka Petrovic & Yazan Chama
 *
 */

public class DataUtilitiesTest extends DataUtilities {
	
	private double[][] parameter;
	private Number[][] returnValue;
	private Number[] numbers;
	private Mockery mockery1;
	private Values2D values1;
	private Mockery mockery2;
	private Values2D values2;
	private Mockery mockery3;
	private Values2D values3;

	public DataUtilitiesTest() {
		
	}
	/**
	 * Used for catching exceptions in tests. 
	 */
	@Rule
	public ExpectedException exception = ExpectedException.none();

	/*================================================================
					Testing: getCumulativePercentages()
	================================================================*/
	
	/**
	 * Testing when the input data param is null.
	 */
	private Mockery mockingContext;
	private Mockery mockingActualCumulativePercentages;
	private Mockery mockingExpectedCumulativePercentages;
	
	private KeyedValues actual;
	private KeyedValues expected;
	
	@Before
	public void settingUp() throws Exception {
		mockingContext = new Mockery();
		mockingActualCumulativePercentages = new Mockery();
		mockingExpectedCumulativePercentages = new Mockery();
		actual = mockingActualCumulativePercentages.mock(KeyedValues.class);
		expected = mockingExpectedCumulativePercentages.mock(KeyedValues.class);
		
		mockingActualCumulativePercentages.checking(new Expectations() {
			{
				atLeast(1).of(actual).getItemCount();
				will(returnValue(3));
				
				atLeast(1).of(actual).getKey(0);
				will(returnValue(0));
				atLeast(1).of(actual).getKey(1);
				will(returnValue(1));
				atLeast(1).of(actual).getKey(2);
				will(returnValue(2));

				atLeast(1).of(actual).getValue(0);
				will(returnValue(5));
				atLeast(1).of(actual).getValue(1);
				will(returnValue(9));
				atLeast(1).of(actual).getValue(2);
				will(returnValue(2));
			}
		});
		
		mockingExpectedCumulativePercentages.checking(new Expectations() {
			{
				atLeast(1).of(actual).getItemCount();
				will(returnValue(3));
				
				atLeast(1).of(actual).getKey(0);
				will(returnValue(0));
				atLeast(1).of(actual).getKey(1);
				will(returnValue(1));
				atLeast(1).of(actual).getKey(2);
				will(returnValue(2));
				
				atLeast(1).of(actual).getValue(0);
				will(returnValue(0.3125));
				atLeast(1).of(actual).getValue(1);
				will(returnValue(0.875));
				atLeast(1).of(actual).getValue(2);
				will(returnValue(1.0));
			}
		});
	}
	
	@Test
	public void getCumulativePercentages_ForNullData() {
		boolean result = true;
		try {
			getCumulativePercentages(null);
			fail("Expected an IllegalArgumentException to be thrown");
		} catch (IllegalArgumentException e) {
			result = false;
		}
		assertEquals(false, result);
	}
	
	@Test
	public void testingGetCumalativePercFirstKey() {
		assertEquals(0.3125, getCumulativePercentages(actual).getValue(0));
	}
	
	//Testing When Values are NULL for the mutation 
	@Test
	public void ValuesAreNullPercentageTest() {
		Mockery mockingContext1 = new Mockery(); 
		final KeyedValues keyvals = mockingContext1.mock(KeyedValues.class);
		mockingContext1.checking(new Expectations() { 
			{
				atLeast(1).of(keyvals).getItemCount();
				will(returnValue(2));
				
				atLeast(1).of(keyvals).getKey(0);
				will(returnValue(0));
				
				atLeast(1).of(keyvals).getValue(0);
				will(returnValue(null));
				
				atLeast(1).of(keyvals).getKey(1);
				will(returnValue(1));
				
				atLeast(1).of(keyvals).getValue(1);
				will(returnValue(null));
				
			} 
		});
		
		Mockery mockingContext2 = new Mockery();
		final KeyedValues result = mockingContext2.mock(KeyedValues.class);
		mockingContext2.checking(new Expectations() {
			{
				atLeast(1).of(result).getItemCount(); 
				will(returnValue(2));
			
				atLeast(1).of(result).getKey(0);
				will(returnValue(0));
				
				atLeast(1).of(result).getValue(0);
				will(returnValue(null));
				
				atLeast(1).of(result).getKey(1);
				will(returnValue(1));
				
				atLeast(1).of(result).getValue(1);
				will(returnValue(null));
			} 
		});
		
		assertFalse("The two results are not equal", DataUtilities.getCumulativePercentages(keyvals).equals(result));
	}
	
	/*================================================================
						Testing: calculateRowTotal()
	================================================================*/
	
	/**
	 * Testing when data parameter is null. 
	 */
	@Test
	public void calculateRowTotalGivenNullData() { 		
		exception.expectMessage("data should not be null");
		exception.expect(InvalidParameterException.class);
		DataUtilities.calculateRowTotal(null, 0);
	}

	
	/**
	 * Given valid data table
	 */
	@Test
	public void testCalcRowTabWithValidInput() { 		
		
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class); 
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getColumnCount();
				will(returnValue(2));
				
				one(values).getValue(0, 0);
				will(returnValue(1));
				one(values).getValue(0, 1);
				will(returnValue(2));
			}
		});
		
		
		double result = DataUtilities.calculateRowTotal(values, 0);
		
		
		assertEquals(3, result, .000000001d);
	}
	
	/**
	 * Given number of columns on Nominal values. 
	 */
	@Test
	public void testCalcRowTabForColsValid() { 		
	
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class); 
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getColumnCount();
				will(returnValue(3));
				
				one(values).getValue(0, 0);
				will(returnValue(1));
				one(values).getValue(0, 1);
				will(returnValue(1));
				one(values).getValue(0, 2);
				will(returnValue(1));
				one(values).getValue(1, 0);
				will(returnValue(2));
				one(values).getValue(1, 1);
				will(returnValue(2));
				one(values).getValue(1, 2);
				will(returnValue(2));
			}
		});
		
		
		double result = DataUtilities.calculateRowTotal(values, 0);
		
		
		assertEquals(3, result, .000000001d);
	}
	
	
	
	/**
	 * Lower bound Row index input, test num of rows. 
	 */
	@Test
	public void testCalcRowTabForRowLB() { 		

		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class); 
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(2));
				one(values).getColumnCount();
				will(returnValue(3));
				
				one(values).getValue(0, 0);
				will(returnValue(1));
				one(values).getValue(0, 1);
				will(returnValue(1));
				one(values).getValue(0, 2);
				will(returnValue(1));
				one(values).getValue(1, 0);
				will(returnValue(2));
				one(values).getValue(1, 1);
				will(returnValue(2));
				one(values).getValue(1, 2);
				will(returnValue(2));
			}
		});
		
		
		double result = DataUtilities.calculateRowTotal(values, 0);
		
	
		assertEquals(3, result, .000000001d);
	}
	
	/**
	 * Given number of rows based on nominal input. 
	 */
	@Test
	public void testCalcRowTabForRowValid() { 		
	
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class); 
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(3));
				one(values).getColumnCount();
				will(returnValue(3));
				
				one(values).getValue(0, 0);
				will(returnValue(1));
				one(values).getValue(0, 1);
				will(returnValue(1));
				one(values).getValue(0, 2);
				will(returnValue(1));
				one(values).getValue(1, 0);
				will(returnValue(2));
				one(values).getValue(1, 1);
				will(returnValue(2));
				one(values).getValue(1, 2);
				will(returnValue(2));
				one(values).getValue(2, 0);
				will(returnValue(3));
				one(values).getValue(2, 1);
				will(returnValue(3));
				one(values).getValue(2, 2);
				will(returnValue(3));
			}
		});
		
		double result = DataUtilities.calculateRowTotal(values, 2);
		
	
		assertEquals(9, result, .000000001d);
		
		
	}
	
	@Test
	public void testingNullRowTable() { //populate wrong column - should fail
		
		mockery2 = new Mockery();
		values2 = mockery2.mock(Values2D.class);
		
		mockery2.checking(new Expectations(){{
			one(values2).getColumnCount();
			will(returnValue(1));
			one(values2).getRowCount();
			will(returnValue(1));
			one(values2).getValue(0, 0);
			will(returnValue(null));
		}});
		double result = DataUtilities.calculateRowTotal(values2, 0);
		assertEquals(result, 0.0, .000000001d);
	}

	
	
	
	/*================================================================
		Testing: createNumberArray2D
	================================================================*/
	@BeforeClass public static void setUpBeforeClass() {
		
		System.out.println("Before class....");
			//throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
		Random rand = new Random();
		System.out.println("Before....");
		parameter = new double[3][4];
		for(int i=0; i<parameter.length; i++)
			for(int j=0; j<parameter[i].length; j++)
				parameter[i][j] = rand.nextDouble();
		mockery1 = new Mockery();
		values1 = mockery1.mock(Values2D.class);
		mockery2 = new Mockery();
		values2 = mockery2.mock(Values2D.class);
		mockery3 = new Mockery();
		values3 = mockery3.mock(Values2D.class);
	}
	
	@Test
	public void testParameters() {
		returnValue = DataUtilities.createNumberArray2D(parameter);
		assertEquals("Double array (not null) being passed as argument and should not give any exception",
				parameter[0][0], (double)returnValue[0][0], .000000001d);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testNullParameters() {
		createNumberArray2D(null);
		fail("Expected IllegalArgumentException. A different error was thrown");
	}
	
	@Test
	public void testEmptyParameter() {
		double[][] emptyArray = {{}};
		Number [][] n = {{}};
		returnValue = DataUtilities.createNumberArray2D(emptyArray);
		assertArrayEquals (n, returnValue);
	}
	
	/*================================================================
					Testing: calculateColumnTotal()
	================================================================*/
	
	@Test
	public void testingThreeRowTable() { //tests adding of 3 rows in a single column
		
		mockery1.checking(new Expectations(){{
			one(values1).getRowCount();
			will(returnValue(3));
			one(values1).getValue(0, 0);
			will(returnValue(7.5));
			one(values1).getValue(1,0);
			will(returnValue(2.5));
			one(values1).getValue(2,0);
			will(returnValue(12.0));
			
		}});
		
		double result = DataUtilities.calculateColumnTotal(values1, 0);
		assertEquals(result, 22.0, .000000001d);
	}
	
	@Test
	public void testingSingleRowTable() { //populate wrong column - should fail
		
		mockery2 = new Mockery();
		values2 = mockery2.mock(Values2D.class);
		
		mockery2.checking(new Expectations(){{
			one(values2).getRowCount();
			will(returnValue(1));
			one(values2).getValue(0, 0);
			will(returnValue(5.5));
		}});
		
		double result = DataUtilities.calculateColumnTotal(values2, 0);
		assertEquals(result, 5.5, .000000001d);
	}
	
	@Test
	public void testingZeroRowTable() { //invalid input no rows populated - returns 0
		
		mockery3.checking(new Expectations(){{
			one(values3).getRowCount();
			will(returnValue(0));
		}});
		
		double result = DataUtilities.calculateColumnTotal(values3, 1);
		assertEquals(result, 0.0, .000000001d);
	}

	@Test
	public void calculateColumnTotalGivenNullData() { 		
		boolean actual = true;
		try {
			// exercise 
			DataUtilities.calculateColumnTotal(null, 0);
		} catch (Exception e) {
			actual = false;
		}
		assertEquals(false, actual);
	}
	
	/**
	 * When number of rows in data is one and col val is null.  
	 */
	@Test
	public void calculateColumnTotalForMutationWhenValueIsNull() { 
		// setup
		Mockery mockingContext = new Mockery();
		final Values2D values = mockingContext.mock(Values2D.class); 
		mockingContext.checking(new Expectations() {
			{
				one(values).getRowCount();
				will(returnValue(1));
				one(values).getValue(0, 0);
				will(returnValue(null));
			}
		});
		
		// exercise 
		double result = DataUtilities.calculateColumnTotal(values, 0);
		
		// verify
		assertNotSame(null, result);
		

	}
	
	/*================================================================
					Testing: createNumberArray
	================================================================*/
	
	
	//tests if a non-empty array is correctly copied
		@Test
		public void validArrayTest() {
			double[] data = {0,1,2,3,4,5,6,7,8,9}; 
			Number[] expected = {0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0}; 
			Number[] actual = DataUtilities.createNumberArray(data);
			assertArrayEquals("data sets should be identical", expected,  actual);
		}
		
		//tests if an empty array is correctly copied
		@Test 
		public void emptyArrayTest() {
			Number[] expected = {};
			double[] data = {};
			Number[] actual = DataUtilities.createNumberArray(data);
			assertArrayEquals("data sets should be identical", expected, actual);
		}
		
		@Test(expected=IllegalArgumentException.class)
		public void nullDataTest() {
			createNumberArray(null);
			fail("Expected IllegalArgumentException. A different error was thrown");
		}
		
		//checks if compilation error occurs when passing an invalid type to function
//		@Test(expected = Error.class)
//		public void testCompiliationError() {
//			DataUtilities.createNumberArray("hello");
//		}
			
		@After
		public void tearDown() throws Exception {
			System.out.println("Tearing down parameter...");
			parameter = null;
			System.out.println("Tearing down returnValue...");
			returnValue = null;
			values1 = null;
			mockery1 = null;
			values2 = null;
			mockery2 = null;
			values3 = null;
			mockery3 = null;
			numbers = null;
		}

		
	
}