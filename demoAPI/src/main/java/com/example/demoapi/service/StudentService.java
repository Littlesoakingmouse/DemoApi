package com.example.demoapi.service;


import com.example.demoapi.dto.request.StudentRequestCreateDTO;
import com.example.demoapi.dto.request.StudentRequestUpdateDTO;
import com.example.demoapi.dto.response.StudentResponse;
import com.example.demoapi.entity.Student;

import java.util.List;

public interface StudentService {
    List<StudentResponse> getAllStudents();
    StudentResponse getStudentById(int id);
    void deleteStudentById(int id);
    void updateStudent(StudentRequestUpdateDTO dto);
    void addStudent(StudentRequestCreateDTO dto);
}
