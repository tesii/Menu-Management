package com.menu.controller;

import com.menu.model.Category;
import com.menu.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class CategoryController {

    private final CategoryService categoryService;
 

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/addCategory")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "addCategory";
    }

    @GetMapping("/listCategories")
    public ModelAndView listCategories() {
        List<Category> list = categoryService.getAllCategories();
        return new ModelAndView("listCategories", "categories", list);
    }

    @PostMapping("/saveCategory")
    public String saveCategory(@ModelAttribute ("category") Category category) {
        categoryService.saveCategory(category);
        return "redirect:/listCategories";
    }
   
    @GetMapping("/editCategory/{category_id}")
    public String editCategory(@PathVariable (value = "category_id") int category_id, Model model) {
        // Retrieve the category by ID from the service
      Category category = categoryService.getCategory_id(category_id);

            model.addAttribute("category", category);
      

        // Return the view name for the editCategories template
        return "editCategories";
    }


   

    @GetMapping("/deleteCategory/{category_id}")
    public String deleteCategory(@PathVariable (value = "category_id") int category_id) {
     this.categoryService.deleteCategoryById(category_id);
     return "redirect:/listCategories";
    }
}


