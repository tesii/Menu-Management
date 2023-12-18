package com.menu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.menu.model.Admin;



public interface AdminRepository  extends JpaRepository<Admin, String> {

	
	
	    Admin findByEmailAndPassword(String email, String password);

		Admin findByEmail(String email);

	
}
