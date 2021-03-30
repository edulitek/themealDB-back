package com.themealdb.TOPI.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.themealdb.TOPI.domain.ResponseMeals;
import com.themealdb.TOPI.service.MealDBService;

@RestController
public class MealDBResource {

	private final MealDBService mealDBService;

	public MealDBResource(MealDBService mealDBService) {
		this.mealDBService = mealDBService;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET, produces = { "application/json" })
	public ResponseEntity<ResponseMeals> search(@RequestParam(defaultValue = "", name = "s") String search,
			@RequestParam(defaultValue = "5", name = "limit") Integer limit,
			@RequestParam(defaultValue = "0", name = "page") Integer page) {
		try {
			ResponseMeals responseMeals = this.mealDBService.search(search, page, limit);
			return new ResponseEntity<ResponseMeals>(responseMeals, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<ResponseMeals>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
