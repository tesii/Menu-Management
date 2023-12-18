package com.menu.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.menu.model.Admin;
import com.menu.model.ChangePasswordForm;
import com.menu.repository.AdminRepository;

@Service
public class ChangePasswordService {

    @Autowired
    private AdminRepository adminRepository;

    public boolean changePassword(ChangePasswordForm changePasswordForm) {
        String email = changePasswordForm.getEmail();
        String oldPassword = changePasswordForm.getOldPassword();
        String newPassword = changePasswordForm.getNewPassword();

        // Retrieve the Admin entity from the database
        Optional<Admin> optionalAdmin = Optional.of(adminRepository.findByEmail(email));

        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();

            // Check if the entered old password matches the current password
            if (oldPassword.equals(admin.getPassword())) {
                // Update the password
                admin.setPassword(newPassword);
                // Save the updated Admin entity
                adminRepository.save(admin);
                return true;
            } else {
                return false; // Incorrect old password
            }
        } else {
            return false; // Admin with the provided email not found
        }
    }
}
