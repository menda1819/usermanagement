package com.example.usermanagement.dto;

import com.example.usermanagement.model.Role; 
import java.time.LocalDateTime; 
public class UserResponseDto { 
    private Long id; 
    private String firstName; 
    private String lastName; 
    private String email; 
    private String username; 
    private String phone; 
    private Role role; 
    private boolean active; 
    private LocalDateTime createdAt; 
    private LocalDateTime updatedAt; 
    public UserResponseDto() { 
    }
    public Long getId() { return id; } 
    public void setId(Long id) { this.id = id; } 
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
    public LocalDateTime getCreatedAt() { return createdAt; } 
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; } 
    public LocalDateTime getUpdatedAt() { return updatedAt; } 
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; } 
}
