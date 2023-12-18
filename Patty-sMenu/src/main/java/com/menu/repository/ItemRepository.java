package com.menu.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.menu.model.Category;
import com.menu.model.Item;


public interface ItemRepository extends JpaRepository<Item, Integer>{

	Page<Item> findByCategory(Category category, Pageable pageable);

	/*
	 * List<Item> searchItemByItem_nameAndCategory_id(String item_name, int
	 * category_id);
	 */
	  @Query("SELECT i FROM Item i WHERE LOWER(i.item_name) LIKE LOWER(CONCAT('%', :item_name, '%')) AND i.category.category_id = :category_id")
	    List<Item> searchItemByItem_nameAndCategory_id(@Param("item_name") String item_name, @Param("category_id") int category_id);

}


