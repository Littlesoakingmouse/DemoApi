package com.example.demoapi.service.impl;

import com.example.demoapi.dto.request.StudentRequestCreateDTO;
import com.example.demoapi.dto.request.StudentRequestUpdateDTO;
import com.example.demoapi.dto.response.StudentResponse;
import com.example.demoapi.entity.Student;
import com.example.demoapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private static List<Student> students = new ArrayList<>();


    @Override
    public List<StudentResponse> getAllStudents() {
        return students.stream().map(student -> {
            StudentResponse studentResponse = new StudentResponse();
            studentResponse.setName(student.getName());
            studentResponse.setAge(student.getAge());
            studentResponse.setId(student.getId());
            return studentResponse;
        }).toList();
    }

    @Override
    public StudentResponse getStudentById(int id) {
        StudentResponse studentResponse = students.stream().filter(student -> student.getId() == id).map(student -> {
            StudentResponse response = new StudentResponse();
            response.setName(student.getName());
            response.setAge(student.getAge());
            response.setId(student.getId());
            return response;
        }).findFirst().orElse(null);
        if (studentResponse == null) {
            throw new RuntimeException("Khong tim thay sinh vien co id = " + id);
        }
        return studentResponse;
    }

    @Override
    public void deleteStudentById(int id) {
        students.removeIf(student -> student.getId() == id);
    }

    @Override
    public void updateStudent(StudentRequestUpdateDTO dto) {
        Student student = students.stream().filter(s -> s.getId() == dto.getId()).findFirst().orElse(null);
        if (student == null) {
            throw new RuntimeException("Khong tim thay sinh vien co id = " + dto.getId());
        }
        students.removeIf(s -> s.getId() == dto.getId());
        student.setName(dto.getName());
        student.setAge(LocalDate.now().getYear() - dto.getYear());
        student.setId(dto.getId());
        students.add(student);
    }

    @Override
    public void addStudent(StudentRequestCreateDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setAge(LocalDate.now().getYear() - dto.getYear());
        student.setId(Integer.parseInt(Math.round(Math.random() * 999999) + ""));
        students.add(student);
    }
}
