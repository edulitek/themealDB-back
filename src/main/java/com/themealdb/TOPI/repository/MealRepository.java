package com.themealdb.TOPI.repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.themealdb.TOPI.domain.Meal;
import com.themealdb.TOPI.domain.Meals;

@Repository
public class MealRepository {

	@Value("${mealdb.api}")
	private String mealdbApi;

	private final RestTemplate restTemplate;

	public MealRepository(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public Optional<List<Meal>> search(String search) throws IOException {
		try {
			search = search != null ? search : "";
			String url = mealdbApi + "/search.php?s=" + search;
			ResponseEntity<Meals> responseEntity = restTemplate.getForEntity(url, Meals.class);
			Meals meals = responseEntity.getBody();
			return Optional.ofNullable(meals.getMeals());
		} catch (Exception ex) {
			throw new IOException(ex.getMessage(), ex);
		}

	}
}
