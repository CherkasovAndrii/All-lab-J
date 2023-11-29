package com.example.demo3_laba2.service;

import com.example.demo3_laba2.entity.Student;
import com.example.demo3_laba2.model.request.StudentRequest;
import com.example.demo3_laba2.repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElse(null);
    }


    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }


    @Transactional
    public Student updateStudent(Long id, StudentRequest studentRequest) {
        // Поиск студента по идентификатору
        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent == null) {
            // Если студент не найден, вернуть null или выполнить другую обработку
            return null;
        }


        existingStudent.setName(studentRequest.getFirstName());
        existingStudent.setSurname(studentRequest.getLastName());
        existingStudent.setYearOfBirth(studentRequest.getDateOfBirth());
        // Другие поля также могут быть обновлены


        return studentRepository.save(existingStudent);
    }


    @Transactional
    public Student patchStudent(Long id, StudentRequest studentRequest) {
        // Найти студента по идентификатору
        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent == null) {
            return null;
        }

        // Обновление данных студента на основе данных из studentRequest
        if (!StringUtils.isEmpty(studentRequest.getFirstName())) {
            existingStudent.setName(studentRequest.getFirstName());
        }

        if (!StringUtils.isEmpty(studentRequest.getLastName())) {
            existingStudent.setSurname(studentRequest.getLastName());
        }

        if (!StringUtils.isEmpty(studentRequest.getDateOfBirth())) {
            existingStudent.setYearOfBirth(studentRequest.getDateOfBirth());
        }

        return studentRepository.save(existingStudent);
    }
}
