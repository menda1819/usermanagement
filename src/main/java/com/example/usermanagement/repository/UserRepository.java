package com.example.usermanagement.repository;

import com.example.usermanagement.model.User; 
import org.springframework.data.domain.Page; 
import org.springframework.data.domain.Pageable; 
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Query; 
import org.springframework.data.repository.query.Param; 
import org.springframework.stereotype.Repository; 
@Repository 
public interface UserRepository extends JpaRepository<User, Long> { 
    boolean existsByEmail(String email); 
    boolean existsByUsername(String username); 
    boolean existsByEmailAndIdNot(String email, Long id); 
    boolean existsByUsernameAndIdNot(String username, Long id); 
    @Query("SELECT u FROM User u WHERE " + 
           "LOWER(u.firstName) LIKE LOWER(CONCAT('%', :kw, '%')) OR " + 
           "LOWER(u.lastName) LIKE LOWER(CONCAT('%', :kw, '%')) OR " + 
           "LOWER(u.email) LIKE LOWER(CONCAT('%', :kw, '%'))") 
    Page<User> searchUsers(@Param("kw") String keyword, Pageable pageable); 
}
