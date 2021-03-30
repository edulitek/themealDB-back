package com.themealdb.TOPI;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.themealdb.TOPI.domain.ResponseMeals;
import com.themealdb.TOPI.resource.MealDBResource;
import com.themealdb.TOPI.service.MealDBService;

public class MealTests extends TopiApplicationTests {

	private MockMvc mockMvc;
	
	@Value("${mealdb.api}")
	private String api;

	@Autowired
	private MealDBResource mealDBResource;
	
	@Autowired
	private MealDBService mealDBService;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(mealDBResource).build();
	}

	@Test
	void theMealApi() {
		assertEquals(api, "https://www.themealdb.com/api/json/v1/1");
	}
	
	@Test
	void searchMeals() throws Exception {
		ResultActions perform = this.mockMvc.perform(MockMvcRequestBuilders.get("/search"));
		perform.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	void mealServiceSearch() throws IOException {
		ResponseMeals response = this.mealDBService.search("", 0, 5);
		assertEquals(response.getPage(), 0);
		assertEquals(response.getLimit(), 5);
		assertEquals(response.getSearchValue(), "");
		assertEquals(response.getMeals().isEmpty(), false);
	}
	
	@Test
	void mealServiceSearchNullValues() throws IOException {
		ResponseMeals response = this.mealDBService.search(null, null, null);
		assertEquals(response.getPage(), null);
		assertEquals(response.getLimit(), null);
		assertEquals(response.getSearchValue(), null);
		assertEquals(response.getMeals().size(), response.getSize());
	}
	
	@Test
	void mealServiceSearchOnlyePagedValues() throws IOException {
		ResponseMeals response = this.mealDBService.search(null, 1, null);
		assertEquals(response.getPage(), 1);
		assertEquals(response.getLimit(), 5);
		assertEquals(response.getSearchValue(), null);
		assertEquals(response.getMeals().size(),5);
	}
	
	@Test
	void mealServiceSearchOnlyLimitValues() throws IOException {
		ResponseMeals response = this.mealDBService.search(null, null, 5);
		assertEquals(response.getPage(), null);
		assertEquals(response.getLimit(), null);
		assertEquals(response.getSearchValue(), null);
		assertEquals(response.getMeals().size(), response.getSize());
	}
	
	@Test
	void mealServiceSearchValues() throws IOException {
		ResponseMeals response = this.mealDBService.search("Arrabiata", 0, 5);
		assertEquals(response.getPage(), 0);
		assertEquals(response.getLimit(), 5);
		assertEquals(response.getSearchValue(), "Arrabiata");
		assertEquals(response.getMeals().get(0).getStrMeal(), "Spicy Arrabiata Penne");
	}
}
