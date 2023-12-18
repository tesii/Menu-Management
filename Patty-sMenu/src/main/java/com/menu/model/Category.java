package com.menu.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int category_id;

    @Column(name = "name", length = 255, nullable = false)
    private String name;


 
    // Constructors, getters, and setters

    public Category() {
        // Default constructor
    }

    public Category(int category_id, String name) {
        this.category_id = category_id;
        this.name = name;
     
    }

    // Getters and setters for categoryId, name, description

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public Object getItems() {
		// TODO Auto-generated method stub
		return null;
	}



}
