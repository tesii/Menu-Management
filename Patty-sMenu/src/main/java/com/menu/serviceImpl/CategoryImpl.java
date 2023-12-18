package com.menu.serviceImpl;

import java.util.List;

import com.menu.model.Category;

public interface CategoryImpl {
	List<Category> getAllCategories();
	Category getCategory_id(int category_id);
	void deleteCategoryById(int category_id);
}
