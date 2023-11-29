package com.example.demo3_laba2.repository;

import com.example.demo3_laba2.entity.ERole;
import com.example.demo3_laba2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
