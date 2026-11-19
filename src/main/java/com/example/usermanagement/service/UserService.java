package com.example.usermanagement.service;

import com.example.usermanagement.dto.UserRequestDto; 
import com.example.usermanagement.dto.UserResponseDto; 
import org.springframework.data.domain.Page; 
public interface UserService { 
    UserResponseDto createUser(UserRequestDto requestDto); 
    Page<UserResponseDto> getAllUsers(int page, int size, String sortBy, String sortDir); 
    UserResponseDto getUserById(Long id); 
    UserResponseDto updateUser(Long id, UserRequestDto requestDto); 
    void deleteUser(Long id); 
    Page<UserResponseDto> searchUsers(String keyword, int page, int size); 
} 
