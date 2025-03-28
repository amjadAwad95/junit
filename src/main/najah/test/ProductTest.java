package main.najah.test;

import main.najah.code.Product;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Product Tests")
@Execution(ExecutionMode.CONCURRENT)
public class ProductTest {
	Product p;

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Start Test");
		p = new Product("BO3", 60);
	}

	@Test
	@Disabled("For fixed the test case create the price 50")
	@DisplayName("Test Change Price")
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	void testChangePrice() {
		fail("Not yet implemented");
	}

	@Test
	@DisplayName("Test Negative Price")
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	void testNegativePrice() {
		assertThrows(IllegalArgumentException.class, () -> {
			Product product = new Product("BO3", -60);
		});
	}

	@ParameterizedTest
	@DisplayName("Test Invalid Discount")
	@ValueSource(doubles = {-1, 51})
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	void testInvalidDiscount(double discount) {
		assertThrows(IllegalArgumentException.class, () -> {
			p.applyDiscount(discount);
		});
	}

	@Test
	@DisplayName("Test Apply Discount")
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	void testApplyDiscount() {
		p.applyDiscount(0.5);
		assertEquals(0.5, p.getDiscount());
	}

	@Test
	@DisplayName("Test Get Price")
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	void testGetPrice() {
		assertEquals(60, p.getPrice());
	}

	@ParameterizedTest
	@DisplayName("Test Get Final Price")
	@CsvSource({"45,25", "48,20", "30,50"})
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	void testGetFinalPrice(double expexted, double discount) {
		p.applyDiscount(discount);
		assertEquals(expexted, p.getFinalPrice());
	}

	@Test
	@DisplayName("Test Get Price With Timeout")
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	void testGetPriceWithTimeout() throws InterruptedException {
		assertEquals(60, p.getPrice());
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("End Test");
	}

}
