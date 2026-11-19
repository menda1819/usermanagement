package com.example.usermanagement.service;

import com.example.usermanagement.dto.UserRequestDto; 
import com.example.usermanagement.dto.UserResponseDto; 
import com.example.usermanagement.exception.DuplicateResourceException; 
import com.example.usermanagement.model.Role; 
import com.example.usermanagement.model.User; 
import com.example.usermanagement.repository.UserJdbcRepository; 
import com.example.usermanagement.repository.UserRepository; 
import com.example.usermanagement.service.impl.UserServiceImpl; 
import org.junit.jupiter.api.BeforeEach; 
import org.junit.jupiter.api.Test; 
import org.mockito.*; 
import static org.junit.jupiter.api.Assertions.*; 
import static org.mockito.Mockito.*; 
class UserServiceTest { 
    @Mock 
    private UserRepository userRepository; 
    @Mock 
    private UserJdbcRepository userJdbcRepository; 
    @InjectMocks 
    private UserServiceImpl userService; 
    @BeforeEach 
    void setUp() { 
        MockitoAnnotations.openMocks(this); 
    }
    @Test 
    void createUser_Success() { 
        UserRequestDto dto = new UserRequestDto(); 
        dto.setFirstName("Alice"); 
        dto.setLastName("Wonder"); 
        dto.setEmail("alice@test.com"); 
        dto.setUsername("alice"); 
        dto.setRole(Role.USER); 
        when(userRepository.existsByEmail("alice@test.com")).thenReturn(false); 
        when(userRepository.existsByUsername("alice")).thenReturn(false); 
        User saved = new User(); 
        saved.setId(10L); 
        saved.setFirstName("Alice"); 
        saved.setLastName("Wonder"); 
        saved.setEmail("alice@test.com"); 
        saved.setUsername("alice"); 
        saved.setRole(Role.USER); 
        saved.setActive(true); 
        when(userRepository.save(any(User.class))).thenReturn(saved); 
        UserResponseDto res = userService.createUser(dto); 
        assertNotNull(res); 
        assertEquals(10L, res.getId()); 
        assertEquals("alice@test.com", res.getEmail()); 
    }
    @Test 
    void createUser_DuplicateEmail_ThrowsException() { 
        UserRequestDto dto = new UserRequestDto(); 
        dto.setEmail("exists@test.com"); 
        when(userRepository.existsByEmail("exists@test.com")).thenReturn(true); 
        assertThrows(DuplicateResourceException.class, () -> userService.createUser(dto)); 
        verify(userRepository, never()).save(any(User.class)); 
    }
} 