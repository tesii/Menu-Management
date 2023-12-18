package com.menu.serviceImpl;

import org.springframework.data.domain.Page;

import com.menu.model.Category;
import com.menu.model.Item;

public interface ItemImpl {
	
void saveItem(Item item);
	Item getItem_id(int item_id);
	void deleteItemById(int item_id);
	Page<Item> findPaginated(int pageNo, int pageSize);
	
}
