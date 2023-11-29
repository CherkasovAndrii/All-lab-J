package com.example.demo3_laba2.repository;

import com.example.demo3_laba2.entity.Faculty;
import com.example.demo3_laba2.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}
