package com.menu.service;


import java.util.List;
import java.util.Optional;
import java.io.IOException;
import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;
import com.menu.model.Admin;
import com.menu.model.Category;

import com.menu.model.Item;
import com.menu.repository.CategoryRepository;

import com.menu.repository.ItemRepository;
import com.menu.serviceImpl.ItemImpl;

import jakarta.transaction.Transactional;

@Service
public class ItemsService implements ItemImpl {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;


    @Autowired
    public ItemsService(ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
       
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItem_id(int item_id) {
        Optional<Item> optional = itemRepository.findById(item_id);
        return optional.orElseThrow(() -> new RuntimeException("Item not found for id :: " + item_id));
    }

    @Override
    public void deleteItemById(int item_id) {
        this.itemRepository.deleteById(item_id);
    }

    @Override
    public Page<Item> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.itemRepository.findAll(pageable);
    }

 

	@Override
	public void saveItem(Item item) {
		itemRepository.save(item);
		
	}
	   public List<Item> searchItemsByNameAndCategory(String item_name, int category_id) {
	        return itemRepository.searchItemByItem_nameAndCategory_id(item_name, category_id);
	    }
}
