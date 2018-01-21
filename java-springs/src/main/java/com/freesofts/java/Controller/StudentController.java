package com.freesofts.java.Controller;

import com.freesofts.java.Model.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class StudentController {
    @RequestMapping("/student/{student_id}")
    public Student getStudent(@PathVariable(value="student_id") Integer id) {
        return new Student(id,"name","std");
    }	
}