package com.example.demo3_laba2.ServiceTest;

import com.example.demo3_laba2.entity.Student;
import com.example.demo3_laba2.model.request.StudentRequest;
import com.example.demo3_laba2.repository.StudentRepository;
import com.example.demo3_laba2.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class ServiceTest {
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StudentService studentService;

    @Autowired

    private StudentRepository studentRepository;

    @Test
    public void testGetStudent() {
        // Save a student to the database
        Student savedStudent = studentRepository.save(createSampleStudent());
        studentRepository.flush();
        // Retrieve the student using the service
        Student retrievedStudent = studentService.getStudent(savedStudent.getId());

        // Assert that the retrieved student is not null and has the expected properties
        Assert.notNull(retrievedStudent, "Retrieved student should not be null");
        Assert.isTrue(savedStudent.getName().equals(retrievedStudent.getName()), "Names should match");
        Assert.isTrue(savedStudent.getSurname().equals(retrievedStudent.getSurname()), "Surnames should match");
    }

    @Test
    public void testUpdateStudent() {
        // Save a student to the database
        Student savedStudent = studentRepository.save(createSampleStudent());

        // Create a StudentRequest with updated information
        StudentRequest updatedStudentRequest = new StudentRequest();
        updatedStudentRequest.setFirstName("UpdatedName");
        updatedStudentRequest.setLastName("UpdatedSurname");
        updatedStudentRequest.setDateOfBirth(2000);

        // Update the student using the service
        Student updatedStudent = studentService.updateStudent(savedStudent.getId(), updatedStudentRequest);

        // Assert that the updated student is not null and has the expected properties
        Assert.notNull(updatedStudent, "Updated student should not be null");
        Assert.isTrue(updatedStudent.getName().equals(updatedStudentRequest.getFirstName()), "Names should match");
        Assert.isTrue(updatedStudent.getSurname().equals(updatedStudentRequest.getLastName()), "Surnames should match");
    }
    @Test
    @Rollback(false) // To commit the transaction and persist the data in the database
    public void testDeleteStudent() {
        // Create a sample student
        Student student = createSampleStudent();
        studentRepository.save(student);

        // Delete the student
        studentService.deleteStudent(student.getId());

        // Verify that the student is deleted
        assertThat(studentRepository.findById(student.getId())).isEmpty();

    }
    @Test
    @Rollback(false) // To commit the transaction and persist the data in the database
    public void testPatchStudent() {
        // Create a sample student
        Student student = createSampleStudent();
        studentRepository.save(student);

        // Create a student request for patching
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setFirstName("UpdatedFirstName");

        // Patch the student
        Student patchedStudent = studentService.patchStudent(student.getId(), studentRequest);

        // Verify that the student is patched
        assertThat(patchedStudent).isNotNull();
        assertThat(patchedStudent.getName()).isEqualTo("UpdatedFirstName");
        assertThat(patchedStudent.getSurname()).isEqualTo(student.getSurname());
   }
/*
    @Test
    public void testCreateStudent() throws Exception {
        // Create a sample StudentRequest
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setFirstName("John");
        studentRequest.setLastName("Doe");
        studentRequest.setDateOfBirth(2003);

        // Convert StudentRequest to JSON

        String jsonRequest = objectMapper.writeValueAsString(studentRequest);

        // Perform POST request to the "/students/create" endpoint
        this.mockMvc.perform(post("/students/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());

        // Verify that the save method is called with the correct Student object
        verify(studentRepository).save(Mockito.any());
    }
*/


    // Helper method to create a sample student
    private Student createSampleStudent() {
        Student student = new Student();
        student.setName("John");
        student.setSurname("Doe");
        student.setYearOfBirth(1995);
        return student;
    }

}
