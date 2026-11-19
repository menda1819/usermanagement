package com.example.usermanagement.dto;

import com.example.usermanagement.model.Role; 
import jakarta.validation.constraints.*; 
public class UserRequestDto { 
    @NotBlank(message = "First name is required") 
    @Size(max = 50, message = "First name must not exceed 50 characters") 
    private String firstName; 
    @NotBlank(message = "Last name is required") 
    @Size(max = 50, message = "Last name must not exceed 50 characters") 
    private String lastName; 
    @NotBlank(message = "Email is required") 
    @Email(message = "Email must be a valid email format") 
    @Size(max = 100, message = "Email must not exceed 100 characters") 
    private String email; 
    @NotBlank(message = "Username is required") 
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters") 
    private String username; 
    @Pattern(regexp = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*$", message = "Invalid phone number format") 
    private String phone; 
    @NotNull(message = "Role is required (ADMIN, MANAGER, USER)") 
    private Role role; 
    private boolean active = true; 
    public UserRequestDto() { 
    }
    public String getFirstName() { return firstName; } 
    public void setFirstName(String firstName) { this.firstName = firstName; } 
    public String getLastName() { return lastName; } 
    public void setLastName(String lastName) { this.lastName = lastName; } 
    public String getEmail() { return email; } 
    public void setEmail(String email) { this.email = email; } 
    public String getUsername() { return username; } 
    public void setUsername(String username) { this.username = username; } 
    public String getPhone() { return phone; } 
    public void setPhone(String phone) { this.phone = phone; } 
    public Role getRole() { return role; } 
    public void setRole(Role role) { this.role = role; } 
    public boolean isActive() { return active; } 
    public void setActive(boolean active) { this.active = active; } 
} 
