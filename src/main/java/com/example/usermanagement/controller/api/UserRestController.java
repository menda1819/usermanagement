package com.example.usermanagement.controller.api;

import com.example.usermanagement.dto.UserRequestDto; 
import com.example.usermanagement.dto.UserResponseDto; 
import com.example.usermanagement.service.UserService; 
import jakarta.validation.Valid; 
import org.springframework.data.domain.Page; 
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.*; 
@RestController 
@RequestMapping("/api/users") 
public class UserRestController { 
    private final UserService userService; 
    public UserRestController(UserService userService) { 
        this.userService = userService; 
    }
    @PostMapping 
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody UserRequestDto dto) { 
        return new ResponseEntity<>(userService.createUser(dto), HttpStatus.CREATED); 
    }
    @GetMapping 
    public ResponseEntity<Page<UserResponseDto>> listUsers( 
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size, 
            @RequestParam(defaultValue = "id") String sortBy, 
            @RequestParam(defaultValue = "asc") String sortDir) { 
        return ResponseEntity.ok(userService.getAllUsers(page, size, sortBy, sortDir)); 
    }
    @GetMapping("/{id}") 
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) { 
        return ResponseEntity.ok(userService.getUserById(id)); 
    }
    @PutMapping("/{id}") 
    public ResponseEntity<UserResponseDto> updateUser( 
            @PathVariable Long id, 
            @Valid @RequestBody UserRequestDto dto) { 
        return ResponseEntity.ok(userService.updateUser(id, dto)); 
    }
    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) { 
        userService.deleteUser(id); 
        return ResponseEntity.noContent().build(); 
    }
    @GetMapping("/search") 
    public ResponseEntity<Page<UserResponseDto>> searchUsers( 
            @RequestParam String keyword, 
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size) { 
        return ResponseEntity.ok(userService.searchUsers(keyword, page, size)); 
    }
} 
