package com.example.demo3_laba2.ControllerTest;

import com.example.demo3_laba2.controller.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = StudentController.class)
public class ControllerTest {

    @Autowired
    private StudentController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }


}
