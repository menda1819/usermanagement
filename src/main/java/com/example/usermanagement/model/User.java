package com.example.usermanagement.model;

import jakarta.persistence.*; 
import java.time.LocalDateTime; 
@Entity 
@Table(name = "users") 
public class User { 
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; 
    @Column(name = "first_name", nullable = false, length = 50) 
    private String firstName; 
    @Column(name = "last_name", nullable = false, length = 50) 
    private String lastName; 
    @Column(name = "email", nullable = false, unique = true, length = 100) 
    private String email; 
    @Column(name = "username", nullable = false, unique = true, length = 50) 
    private String username; 
    @Column(name = "phone", length = 20) 
    private String phone; 
    @Enumerated(EnumType.STRING) 
    @Column(name = "role", nullable = false, length = 20) 
    private Role role; 
    @Column(name = "active", nullable = false) 
    private boolean active = true; 
    @Column(name = "created_at", nullable = false, updatable = false) 
    private LocalDateTime createdAt; 
    @Column(name = "updated_at", nullable = false) 
    private LocalDateTime updatedAt; 
    public User() { 
    }
    @PrePersist 
    protected void onCreate() { 
        this.createdAt = LocalDateTime.now(); 
        this.updatedAt = LocalDateTime.now(); 
    }
    @PreUpdate 
    protected void onUpdate() { 
        this.updatedAt = LocalDateTime.now(); 
    }
    // Getters and Setters 
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