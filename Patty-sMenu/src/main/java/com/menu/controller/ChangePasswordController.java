package com.menu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.menu.model.ChangePasswordForm;
import com.menu.service.ChangePasswordService;
@Controller
public class ChangePasswordController {
	 @Autowired
	    private ChangePasswordService changePasswordService;
	 @GetMapping("/passwordchange")
	 public String showChangePasswordForm(Model model) {
	     model.addAttribute("changePasswordForm", new ChangePasswordForm());
	     return "passwordchange";
	 }

	 @PostMapping("/passwordchange")
	 public String processChangePasswordForm(@ModelAttribute("changePasswordForm") ChangePasswordForm changePasswordForm,
	                                         Model model) {
	     boolean passwordChanged = changePasswordService.changePassword(changePasswordForm);

	     if (passwordChanged) {
	         model.addAttribute("message", "Password changed successfully");
	     } else {
	         model.addAttribute("errorMessage", "Failed to change password. Admin not found or incorrect credentials.");
	     }
	     return "passwordchange-result";
	 }

}
