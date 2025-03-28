package main.najah.test;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;
import main.najah.code.RecipeException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RecipeBookTest {

	static RecipeBook recipeBook;

	@BeforeAll
	static void init() {
		recipeBook = new RecipeBook();
		System.out.println("Start RecipeBookTest Test");
	}

	@Test
	@DisplayName("Test Add Recipe")
	@Order(1)
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	void testAddRecipe() throws RecipeException {
		Recipe recipe = new Recipe();
		recipe.setName("Test1");
		recipe.setPrice("10");
		assertEquals(true, recipeBook.addRecipe(recipe));
		assertEquals(recipe, recipeBook.getRecipes()[0]);
		assertEquals(null, recipeBook.getRecipes()[1]);
	}

	@Test
	@DisplayName("Test Add Exists Recipe")
	@Order(2)
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	void testAddExistsRecipe() throws RecipeException {
		Recipe recipe = new Recipe();
		recipe.setName("Test1");
		recipe.setPrice("10");

		assertEquals(false, recipeBook.addRecipe(recipe));
	}

	@ParameterizedTest
	@DisplayName("Test Add Four Recipe")
	@Order(3)
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	@CsvSource({"false,Test1,10", "true,Test2,20", "true,Test3,30", "true,Test4,40"})
	void testAddFoureRecipe(boolean expexted, String name, String price) throws RecipeException {
		Recipe recipe = new Recipe();
		recipe.setName(name);
		recipe.setPrice(price);
		assertEquals(expexted, recipeBook.addRecipe(recipe));
	}

	@Test
	@DisplayName("Test Delete Recipe")
	@Order(4)
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	void testDeleteRecipe() throws RecipeException {
		assertEquals("Test1", recipeBook.deleteRecipe(0));
		assertEquals("Test4", recipeBook.deleteRecipe(3));
	}

	@ParameterizedTest
	@DisplayName("Test Delete Not Exists Recipe")
	@Order(5)
	@ValueSource(ints = {0, 3})
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	void testDeleteNotExistsRecipe(int index) throws RecipeException {
		assertEquals("", recipeBook.deleteRecipe(index));
	}

	@Test
	@DisplayName("Test Update Recipe")
	@Order(6)
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	void testUpdateRecipe() throws RecipeException {
		Recipe recipe = new Recipe();
		recipe.setName("Test10");
		recipe.setPrice("100");
		assertEquals("Test3", recipeBook.editRecipe(2, recipe));
		assertEquals("", recipeBook.getRecipes()[2].getName());
	}

	@Test
	@DisplayName("Test Update Not Exists Recipe")
	@Order(7)
	@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
	void testUpdateNotExistsRecipe() throws RecipeException {
		Recipe recipe = new Recipe();
		recipe.setName("Test10");
		recipe.setPrice("100");
		assertEquals("", recipeBook.editRecipe(0, recipe));
	}

	@AfterAll
	static void end() {
		recipeBook = null;
		System.out.println("End RecipeBookTest Test");
	}
}
