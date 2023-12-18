package com.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.menu.model.Admin;
import com.menu.model.Category;



public interface CategoryRepository  extends JpaRepository<Category, Integer> {

	Category findByName(String category);





	
}
