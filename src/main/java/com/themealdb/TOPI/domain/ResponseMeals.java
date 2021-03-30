package com.themealdb.TOPI.domain;

import java.io.Serializable;
import java.util.List;

public class ResponseMeals implements Serializable {
	private static final long serialVersionUID = 6707097569479450261L;

	private Integer page;
	private Integer limit;
	private Integer size;
	private String searchValue;
	private List<Meal> meals;

	public ResponseMeals(List<Meal> meals, Integer page, Integer limit, int size, String search) {
		this.meals = meals;
		this.page = page;
		this.limit = limit;
		this.size = size;
		this.searchValue = search;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public List<Meal> getMeals() {
		return meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
}
