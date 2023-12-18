package com.menu.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

@Entity
@Table(name = "item")
public class Item {

	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "item_id")
	    private int item_id;

	    @Column(name = "item_name", length = 255)
	    private String item_name;

	    @Column(name = "description")
	    private String description;

	    @Column(name = "item_price")
	    private double item_price;

	    @ManyToOne
	    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
	    private Category category;

	   

    public Item() {
        // Default constructor
    }

    public Item(int item_id, String item_name, double item_price, String description, Category category) {
       
    	this.item_id = item_id;
        this.item_name = item_name;
        this.item_price = item_price;
        this.description = description;
        this.category = category;
      
        
        }
    

    // Add getters and setters for other fields

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public double getItem_price() {
        return item_price;
    }

    public void setItem_price(double item_price) {
        this.item_price = item_price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

  

 

    @Override
    public String toString() {
        return "Item [item_id=" + item_id + ", item_name=" + item_name + ", description=" + description
                + ", item_price=" + item_price + ", category=" + category + "]";
    }
}
