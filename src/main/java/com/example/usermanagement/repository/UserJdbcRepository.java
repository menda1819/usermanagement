package com.example.usermanagement.repository;

import org.springframework.jdbc.core.JdbcTemplate; 
import org.springframework.stereotype.Repository; 
@Repository 
public class UserJdbcRepository { 
    private final JdbcTemplate jdbcTemplate; 
    public UserJdbcRepository(JdbcTemplate jdbcTemplate) { 
        this.jdbcTemplate = jdbcTemplate; 
    }
    public int countActiveUsers() { 
        String sql = "SELECT COUNT(*) FROM dbo.users WHERE active = 1"; 
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class); 
        return count != null ? count : 0; 
    }
    public int softDeleteById(Long id) { 
        String sql = "UPDATE dbo.users SET active = 0, updated_at = SYSUTCDATETIME() WHERE id = ?"; 
        return jdbcTemplate.update(sql, id); 
    }
} 
