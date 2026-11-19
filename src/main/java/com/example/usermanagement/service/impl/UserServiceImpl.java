package com.example.usermanagement.service.impl;

import com.example.usermanagement.dto.UserRequestDto; 
import com.example.usermanagement.dto.UserResponseDto; 
import com.example.usermanagement.exception.DuplicateResourceException; 
import com.example.usermanagement.exception.ResourceNotFoundException; 
import com.example.usermanagement.model.User; 
import com.example.usermanagement.repository.UserJdbcRepository; 
import com.example.usermanagement.repository.UserRepository; 
import com.example.usermanagement.service.UserService; 
import org.springframework.data.domain.*; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Transactional; 
@Service 
@Transactional 
public class UserServiceImpl implements UserService { 
    private final UserRepository userRepository; 
    private final UserJdbcRepository userJdbcRepository; 
    public UserServiceImpl(UserRepository userRepository, UserJdbcRepository userJdbcRepository) { 
        this.userRepository = userRepository; 
        this.userJdbcRepository = userJdbcRepository; 
    }
    @Override 
    public UserResponseDto createUser(UserRequestDto dto) { 
        if (userRepository.existsByEmail(dto.getEmail())) { 
            throw new DuplicateResourceException("Email already exists: " + dto.getEmail()); 
        } 
        if (userRepository.existsByUsername(dto.getUsername())) { 
            throw new DuplicateResourceException("Username already exists: " + dto.getUsername()); 
        } 
        User user = toEntity(dto); 
        User saved = userRepository.save(user); 
        return toDto(saved); 
    }
    @Override 
    @Transactional(readOnly = true) 
    public Page<UserResponseDto> getAllUsers(int page, int size, String sortBy, String sortDir) { 
        Sort sort = sortDir.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending(); 
        Pageable pageable = PageRequest.of(page, size, sort); 
        return userRepository.findAll(pageable).map(this::toDto); 
    }
    @Override 
    @Transactional(readOnly = true) 
    public UserResponseDto getUserById(Long id) { 
        User user = userRepository.findById(id) 
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id)); 
        return toDto(user); 
    }
    @Override 
    public UserResponseDto updateUser(Long id, UserRequestDto dto) { 
        User existing = userRepository.findById(id) 
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id)); 
        if (userRepository.existsByEmailAndIdNot(dto.getEmail(), id)) { 
            throw new DuplicateResourceException("Email is already taken: " + dto.getEmail()); 
        } 
        if (userRepository.existsByUsernameAndIdNot(dto.getUsername(), id)) { 
            throw new DuplicateResourceException("Username is already taken: " + dto.getUsername()); 
        } 
        existing.setFirstName(dto.getFirstName()); 
        existing.setLastName(dto.getLastName()); 
        existing.setEmail(dto.getEmail()); 
        existing.setUsername(dto.getUsername()); 
        existing.setPhone(dto.getPhone()); 
        existing.setRole(dto.getRole()); 
        existing.setActive(dto.isActive()); 
        return toDto(userRepository.save(existing)); 
    }
    @Override 
    public void deleteUser(Long id) { 
        if (!userRepository.existsById(id)) { 
            throw new ResourceNotFoundException("Cannot delete. User not found with id: " + id); 
        } 
        // Soft delete executed via Spring JdbcTemplate 
        userJdbcRepository.softDeleteById(id); 
    }
    @Override 
    @Transactional(readOnly = true) 
    public Page<UserResponseDto> searchUsers(String keyword, int page, int size) { 
        Pageable pageable = PageRequest.of(page, size, Sort.by("firstName").ascending()); 
        return userRepository.searchUsers(keyword, pageable).map(this::toDto); 
    }
    private User toEntity(UserRequestDto dto) { 
        User u = new User(); 
        u.setFirstName(dto.getFirstName()); 
        u.setLastName(dto.getLastName()); 
        u.setEmail(dto.getEmail()); 
        u.setUsername(dto.getUsername()); 
        u.setPhone(dto.getPhone()); 
        u.setRole(dto.getRole()); 
        u.setActive(dto.isActive()); 
        return u; 
    }
    private UserResponseDto toDto(User u) { 
        UserResponseDto dto = new UserResponseDto(); 
        dto.setId(u.getId()); 
        dto.setFirstName(u.getFirstName()); 
        dto.setLastName(u.getLastName()); 
        dto.setEmail(u.getEmail()); 
        dto.setUsername(u.getUsername()); 
        dto.setPhone(u.getPhone()); 
        dto.setRole(u.getRole()); 
        dto.setActive(u.isActive()); 
        dto.setCreatedAt(u.getCreatedAt()); 
        dto.setUpdatedAt(u.getUpdatedAt()); 
        return dto; 
    }
} 
