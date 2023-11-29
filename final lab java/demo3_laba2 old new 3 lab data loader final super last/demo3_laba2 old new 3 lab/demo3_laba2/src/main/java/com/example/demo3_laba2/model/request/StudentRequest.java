package com.example.demo3_laba2.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudentRequest {
    private String firstName;
    private String lastName;
    private int dateOfBirth;
    private int course;

}