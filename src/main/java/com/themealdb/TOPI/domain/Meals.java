package com.themealdb.TOPI.domain;

import java.io.Serializable;
import java.util.List;

public class Meals implements Serializable {

	private static final long serialVersionUID = 1760776784397418455L;
	
	private List<Meal> meals;

	public List<Meal> getMeals() {
		return meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}
}
