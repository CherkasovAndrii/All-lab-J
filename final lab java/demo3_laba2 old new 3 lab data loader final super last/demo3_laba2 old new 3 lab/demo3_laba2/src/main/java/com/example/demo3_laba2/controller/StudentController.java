package com.example.demo3_laba2.controller;

import com.example.demo3_laba2.entity.Student;
import com.example.demo3_laba2.model.request.StudentRequest;
import com.example.demo3_laba2.repository.StudentRepository;
import com.example.demo3_laba2.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;
    private StudentRequest studentRequest;


    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student student = studentService.getStudent(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/create")
    public void createStudent(@RequestBody StudentRequest studentRequest) {
        // Преобразование данных из StudentRequest в объект Student
        Student student = new Student();
        student.setName(studentRequest.getFirstName());
        student.setSurname(studentRequest.getLastName());
        student.setYearOfBirth(studentRequest.getDateOfBirth());


        studentRepository.save(student);

    }
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody StudentRequest studentRequest) {
        Student updatedStudent = studentService.updateStudent(id, studentRequest);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Student> patchStudent(@PathVariable Long id, @RequestBody StudentRequest studentRequest) {
        Student updatedStudent = studentService.patchStudent(id, studentRequest);
        if (updatedStudent != null) {
            return ResponseEntity.ok(updatedStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}