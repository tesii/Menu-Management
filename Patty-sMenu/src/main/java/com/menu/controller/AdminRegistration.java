package com.menu.controller;


import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.menu.model.Admin;
import com.menu.model.ChangePasswordForm;
import com.menu.model.Item;
import com.menu.repository.AdminRepository;
import com.menu.service.AdminService;

import jakarta.validation.Valid;


@Controller

public class AdminRegistration {

	  @Autowired
	    private AdminService service;
	  

	  
	  private final AdminRepository adminRepository;

	    @Autowired
	    public AdminRegistration(AdminRepository adminRepository) {
	        this.adminRepository = adminRepository;
	    }
	  
		@GetMapping("/")
		public String home() {
			return "home";
		}
		
    @GetMapping("/add")
    public String showAddAdminForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "addAdmin";
    }
    @GetMapping("/listAdmin")
    public ModelAndView listAdmin() {
        List<Admin> list = service.getAdmin();
        return new ModelAndView("listAdmin", "admins", list); // Create a Thymeleaf template for listing academic units
    }
  
    @PostMapping("/saveAdmin")
    public String addAdmin(@Valid @ModelAttribute Admin admin, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            // If validation errors exist, return to the form with error messages
            return "addAdmin";
        }
        service.save(admin);
        redirectAttributes.addFlashAttribute("success", "Admin added successfully");
        return "redirect:/listAdmin";
    }


    @GetMapping("/login")
    public String showLoginForm() {
        return "login2";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, @RequestParam String password, Model model) {
        Admin admin = adminRepository.findByEmailAndPassword(email, password);

        if (admin != null) {
            String role = admin.getRole();

            // Additional role checks
            if ("ROLE_ADMIN".equals(role)) {
                // Admin login successful, redirect to admin dashboard
            	 model.addAttribute("message", "Admin logged in successfully");
                return "admindashboard";
            
            } else if ("ROLE_USER".equals(role) ) {
                // User login successful, display a message
                model.addAttribute("message", "User logged in successfully");
                return "userdashboard"; // Replace with the appropriate user dashboard view
            } else {
                // Unknown role, handle accordingly
                model.addAttribute("error", "Invalid role");
                return "login2";
            }
        } else {
            // Login failed, add an error message to the model
            model.addAttribute("error", "Invalid email or password");
            return "login2";
        }
    }
    @GetMapping("/forgotpass")
    public String showForgotForm() {
        return "forgotpass";
    }

    @PostMapping("/forgotpass")
    public String processForgotForm(@RequestParam String email, Model model) {
        boolean resetInitiated = service.initiatePasswordReset(email);

        if (resetInitiated) {
            model.addAttribute("emailEntered", email);
            model.addAttribute("password", service.getPasswordForEmail(email));
            return "forgotpass-result";
        } else {
            model.addAttribute("errorMessage", "Failed to initiate password reset. Please check your email.");
            return "forgotpass-result";
        }
    }



}

