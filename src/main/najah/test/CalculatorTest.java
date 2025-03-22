package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import main.najah.code.Calculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.concurrent.TimeUnit;

@DisplayName("Calculator Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorTest {
	
    Calculator calc;

	@BeforeAll
	static  void init() {
		System.out.println("Start Test");
	}

	@BeforeEach
	void setUp() throws Exception {
		calc = new Calculator();
	}

	@Test
	@DisplayName("Test Add")
	@Order(1)
	void testAdd() {
		assertEquals(calc.add(1, 2), 3);
		assertEquals(calc.add(1, 2, 3), 6);
		assertEquals(calc.add(1,2,3,4,5,6,7,8,9), 45);
	}

	@ParameterizedTest
	@DisplayName("Test Divide")
	@CsvSource({"0,1,2",
				"0,2,3",
				"0,3,4",
				"0,4,5",
				"1,6,5",
				"2,10,5"})
	@Order(2)
	void testDivide(float expexted , int dividend, int divisor) {
		assertEquals(expexted,calc.divide(dividend,divisor));
	}

	@Test
	@DisplayName("Test Divide On Zero")
	@Order(3)
	void testDivideOnZero() {
		assertThrows(ArithmeticException.class, () -> {calc.divide(1,0);});
	}

	@ParameterizedTest
	@DisplayName("Test factorial")
	@CsvSource({"1,0",
				"1,1",
				"2,2",
				"6,3",
				"24,4",
				"120,5"})
	@Order(4)
	void testFactorial(int expexted , int factorial) {
		assertEquals(expexted,calc.factorial(factorial));
	}

	@Test
	@DisplayName("Test factorial in negative numbers")
	@Order(5)
	void testFactorialInNegativeNumbers() {
		assertThrows(IllegalArgumentException.class, () -> {calc.factorial(-1);});
	}

	@Test
	@DisplayName("Test Time out")
	@Timeout(value = 1000 , unit = TimeUnit.MILLISECONDS)
	@Disabled
	void testTimeOut() throws InterruptedException {
		try{
			Thread.sleep(2000);
			assertTrue(true);
		}catch (InterruptedException e){
			e.printStackTrace();
		}
	}

	@AfterAll
	static void end() {
		System.out.println("End Test");
	}
}
