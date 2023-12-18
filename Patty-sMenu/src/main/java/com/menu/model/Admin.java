package com.menu.model;
import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Data
@Entity
@Table(name = "admin")
public class Admin {

    @Id
   
   
    @Email(message = "Invalid email address")
    @NotBlank(message = "Email is required")
    @Column(name = "email", length = 255)  
    private String email;

    
    @NotBlank(message = "Name is required")
   @Size(min = 2, message = "name should have atleast 2 characters")
    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "phone_number", length = 20)

    private String phone_number;

    @Column(name = "password", columnDefinition = "LONGTEXT")

    private String password;

    @Column(name = "role", length = 50)
  
    private String role;

  

   
    
    // Constructors, getters, setters, and other methods

    public Admin() {
        // Default constructor
    }

    public Admin(String email, String name, String phone_number, String password, String role) {
        this.email = email;
        this.name = name;
        this.phone_number = phone_number;
        this.password = password;
        this.role = role;
        
    }

    // Add getters and setters for other fields

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

  
}
