package com.example.usermanagement.controller.web;

import com.example.usermanagement.dto.UserRequestDto; 
import com.example.usermanagement.dto.UserResponseDto; 
import com.example.usermanagement.model.Role; 
import com.example.usermanagement.service.UserService; 
import jakarta.validation.Valid; 
import org.springframework.data.domain.Page; 
import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model; 
import org.springframework.validation.BindingResult; 
import org.springframework.web.bind.annotation.*; 
@Controller 
@RequestMapping("/users") 
public class UserWebController { 
    private final UserService userService; 
    public UserWebController(UserService userService) { 
        this.userService = userService; 
    }
    @GetMapping 
    public String listUsers( 
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size, 
            @RequestParam(required = false) String keyword, 
            Model model) { 
        Page<UserResponseDto> userPage = (keyword != null && !keyword.isBlank()) 
                ? userService.searchUsers(keyword, page, size) 
                : userService.getAllUsers(page, size, "id", "asc"); 
        model.addAttribute("users", userPage.getContent()); 
        model.addAttribute("currentPage", page); 
        model.addAttribute("totalPages", userPage.getTotalPages()); 
        model.addAttribute("keyword", keyword); 
        return "users/list"; 
    }
    @GetMapping("/new") 
    public String showCreateForm(Model model) { 
        model.addAttribute("user", new UserRequestDto()); 
        model.addAttribute("roles", Role.values()); 
        model.addAttribute("isEdit", false); 
        return "users/form"; 
    }
    @PostMapping("/save") 
    public String saveUser( 
            @Valid @ModelAttribute("user") UserRequestDto dto, 
            BindingResult result, 
            @RequestParam(required = false) Long id, 
            Model model) { 
        if (result.hasErrors()) { 
            model.addAttribute("roles", Role.values()); 
            model.addAttribute("isEdit", id != null); 
            return "users/form"; 
        } 
        if (id == null) { 
            userService.createUser(dto); 
        } else { 
            userService.updateUser(id, dto); 
        } 
        return "redirect:/users"; 
    }
    @GetMapping("/edit/{id}") 
    public String showEditForm(@PathVariable Long id, Model model) { 
        UserResponseDto resp = userService.getUserById(id); 
        UserRequestDto req = new UserRequestDto(); 
        req.setFirstName(resp.getFirstName()); 
        req.setLastName(resp.getLastName()); 
        req.setEmail(resp.getEmail()); 
        req.setUsername(resp.getUsername()); 
        req.setPhone(resp.getPhone()); 
        req.setRole(resp.getRole()); 
        req.setActive(resp.isActive()); 
        model.addAttribute("user", req); 
        model.addAttribute("userId", id); 
        model.addAttribute("roles", Role.values()); 
        model.addAttribute("isEdit", true); 
        return "users/form"; 
    }
    @GetMapping("/view/{id}") 
    public String viewUser(@PathVariable Long id, Model model) { 
        model.addAttribute("user", userService.getUserById(id)); 
        return "users/view"; 
    }
    @PostMapping("/delete/{id}") 
    public String deleteUser(@PathVariable Long id) { 
        userService.deleteUser(id); 
        return "redirect:/users"; 
    }
}
