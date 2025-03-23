package main.najah.test;

import main.najah.code.UserService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Execution(ExecutionMode.CONCURRENT)
class UserServiceSimpleTest {

	static UserService userService;

	@BeforeAll
	static void init() {
		userService = new UserService();
		System.out.println("Start UserServiceSimpleTest");
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Start Test");
	}

	@Test
	@DisplayName("Test Null Email")
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	void testNullEmail() {
		assertEquals(false, userService.isValidEmail(null));
	}

	@ParameterizedTest
	@DisplayName("Test Invalid Email")
	@ValueSource(strings = {"amjadawad", "amjadawad@gmail", "amjadawad.com"})
	void testInvalidEmail(String email) {
		assertEquals(false, userService.isValidEmail(email));
	}

	@Test
	@DisplayName("Test Valid Email")
	void testValidEmail() {
		assertEquals(true, userService.isValidEmail("amjadawad@gmail.com"));
	}

	@ParameterizedTest
	@DisplayName("Test Authenticate")
	@CsvSource({"false,amjad,1234", "false,admin,7539", "false,amjad,8526", "true,admin,1234"})
	void testInvalidAuthenticate(boolean expexted, String username, String password) {
		assertEquals(expexted, userService.authenticate(username, password));
	}


	@AfterEach
	void tearDown() throws Exception {
		System.out.println("End Test");
	}

	@AfterAll
	static void end() {
		System.out.println("End UserServiceSimpleTest");
	}
}
