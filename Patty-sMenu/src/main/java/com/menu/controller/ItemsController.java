package com.menu.controller;

import com.menu.model.Category;

import com.menu.model.Item;

import com.menu.repository.ItemRepository;
import com.menu.service.CategoryService;

import com.menu.service.ItemsService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
public class ItemsController {

    @Autowired
    private ItemsService itemsService;

    @Autowired
    private CategoryService categoryService;


    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/addItem")
    public String showAddItemForm(Model model) { // Add Model parameter here
        List<Category> categories = categoryService.getAllCategories();

        Item item = new Item();
        model.addAttribute("categories", categories);
      
        model.addAttribute("item", item);
        return "addItem";
    }

    @PostMapping("/saveItem")
    public String saveItem(@ModelAttribute("item") Item item) {
        itemsService.saveItem(item);
        return "redirect:/listItem";
    }

    @GetMapping("/listItem")
    public String listItem(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("/listItem/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;

        Page<Item> page = itemsService.findPaginated(pageNo, pageSize);
        List<Item> listItems = page.getContent();

        model.addAttribute("currentPage", pageNo); // Add 1 to display a 1-based index to users
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listItems", listItems);
       

        return "listItem";
    }


    @GetMapping("/editItem/{item_id}")
    public String editItem(@PathVariable(value = "item_id") int item_id, Model model) {
    	   List<Category> categories = categoryService.getAllCategories();
    	   List<Item> items = itemsService.getAllItems();
        Item item = itemsService.getItem_id(item_id);
        model.addAttribute("categories", categories);
       
        model.addAttribute("item", item);
        return "editItem";
    }
    
    @PostMapping("/editItem")
    public String updateItem(@ModelAttribute("item") Item item) {
        itemsService.saveItem(item); // Assuming your service method handles both save and update
        return "redirect:/listItem";
    }

    @GetMapping("/deleteItem/{item_id}")
    public String deleteItem(@PathVariable(value = "item_id") int item_id) {
        this.itemsService.deleteItemById(item_id);
        return "redirect:/listItem";
    }
  
    @GetMapping("/search")
    public String getSearchForm(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        List<Item> items = itemsService.getAllItems(); // Assuming you have a method to get all items

        model.addAttribute("categories", categories);
        model.addAttribute("items", items);

        // You can populate any other model attributes needed for the search form

        return "searchForm";
    }


    @PostMapping("/search")
    public String searchItems(@RequestParam String item_name, @RequestParam int category_id, Model model) {
        List<Item> searchResults = itemRepository.searchItemByItem_nameAndCategory_id(item_name, category_id);
        model.addAttribute("searchResults", searchResults);
        return "searchResults";
    }
}
