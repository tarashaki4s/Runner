package com.example.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.*;

import java.util.Optional;

public interface RoleDAO extends JpaRepository<Role, String>{
  Optional<Role> findByName(ERole name);
}
