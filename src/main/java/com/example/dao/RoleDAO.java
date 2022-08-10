package com.example.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.*;

public interface RoleDAO extends JpaRepository<Role, String>{
    
}
