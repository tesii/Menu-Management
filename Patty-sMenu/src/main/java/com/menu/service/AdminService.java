package com.menu.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.menu.model.Admin;
import com.menu.model.Item;
import com.menu.repository.AdminRepository;

@Service
public class AdminService {
	 @Autowired
	    private AdminRepository adminRepository;
   

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void save(Admin admin) {
    	 admin.setPassword(admin.getPassword());
         adminRepository.save(admin);
    }

	public List<Admin> getAdmin() {
		return adminRepository.findAll();
	}

	public boolean initiatePasswordReset(String email) {
		// TODO Auto-generated method stub
		return true;
	}

	public String getPasswordForEmail(String email) {
		   Admin admin = adminRepository.findByEmail(email);
	        return (admin != null) ? admin.getPassword() : null;
	}

	






 
}
