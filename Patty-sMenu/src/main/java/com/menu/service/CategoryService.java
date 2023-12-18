package com.menu.service;

import com.menu.model.Category;
import com.menu.repository.CategoryRepository;
import com.menu.service.AdminService;
import com.menu.serviceImpl.CategoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CategoryImpl{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }



    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

 


@Override
public Category getCategory_id(int category_id) {
	Optional<Category> optional = categoryRepository.findById(category_id);
	Category category = null;
	if(optional.isPresent()) {
		category = optional.get();
	}else {
		throw new RuntimeException(" Employee not found for id :: " + category_id);
	}
	return category;
}

@Override
public void deleteCategoryById(int category_id) {
this.categoryRepository.deleteById(category_id);
	
}

@Override
public List<Category> getAllCategories() {
	return categoryRepository.findAll();
}
}
