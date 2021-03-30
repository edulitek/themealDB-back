package com.themealdb.TOPI.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.themealdb.TOPI.domain.Meal;
import com.themealdb.TOPI.domain.ResponseMeals;
import com.themealdb.TOPI.repository.MealRepository;

@Service
public class MealDBService {

	private final MealRepository mealRepository;

	public MealDBService(MealRepository mealRepository) {
		this.mealRepository = mealRepository;
	}

	public ResponseMeals search(String search, Integer page, Integer limit) throws IOException {
		List<Meal> meals = this.mealRepository.search(search).orElse(new ArrayList<Meal>());
		if (page != null) {
			limit = limit == null ? 5 : limit;
			ArrayList<Meal> pagination = meals.stream().skip(page * limit).limit(limit)
					.collect(Collectors.toCollection(ArrayList::new));
			return new ResponseMeals(pagination, page, limit, meals.size(), search);
		} else {
			limit = null;
		}

		return new ResponseMeals(meals, page, limit, meals.size(), search);
	}
}
