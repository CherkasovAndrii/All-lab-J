package com.example.demo3_laba2.RepositoryTest;

import com.example.demo3_laba2.entity.Student;
import com.example.demo3_laba2.entity.User;
import com.example.demo3_laba2.repository.StudentRepository;
import com.example.demo3_laba2.repository.UserRepository;
import com.example.demo3_laba2.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest

public class RepositoryTest {

    @Autowired(required = true)
    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private StudentRepository studentRepository;

    @Test
    @Order(1)
    public void TestFindUserByName() {
        // Given
        User user = new User();
        user.setUsername("john_doe");
        user.setEmail("john.doe@example.com");
        when(userRepository.findByUsername("john_doe")).thenReturn(Optional.of(user));
        assertThat(userRepository.findByUsername("john_doe").isPresent()).isTrue();

    }

    @Test
    @Order(2)
    public void TestFindNonExistentUser() {
        // Given
        when(userRepository.findByUsername("non_existent_user")).thenReturn(Optional.empty());
        assertThat(userRepository.findByUsername("non_existent_user").isEmpty());

    }
    @Test
    @Order(3)
    public void TestStudentSaveUser() {
        Student student = new Student();
        student.setName("Andrii");
        student.setCourse(3);
        student.setYearOfBirth(2004);
        studentRepository.save(student);
        studentRepository.flush();
        assertThat(studentRepository).isNotNull();
    }
    @Test
    @Order(4)
    public void DeleteUser() {
        User user = new User();
        user.setUsername("john_doe");
        user.setEmail("john.doe@example.com");
        userRepository.save(user);
        userRepository.deleteAll();
        assertThat(userRepository.count()).isEqualTo(0);
    }
    @Test
    @Order(5)
    public void DeleteStudent() {
        Student student = new Student();
        student.setName("Andrii");
        student.setCourse(3);
        student.setYearOfBirth(2004);
        studentRepository.save(student);
        studentRepository.delete(student);
        assertThat(studentRepository.count()).isEqualTo(0);
    }

}
