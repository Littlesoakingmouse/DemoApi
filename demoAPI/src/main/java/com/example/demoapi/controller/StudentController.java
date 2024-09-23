package com.example.demoapi.controller;

import com.example.demoapi.dto.request.StudentRequestCreateDTO;
import com.example.demoapi.dto.request.StudentRequestUpdateDTO;
import com.example.demoapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/student-management")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("list")
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<?> detail(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody StudentRequestCreateDTO dto) {
        studentService.addStudent(dto);
        return ResponseEntity.ok("CREATED");
    }

    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody StudentRequestUpdateDTO dto) {
        studentService.updateStudent(dto);
        return ResponseEntity.ok("UPDATED");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.ok("DELETED");
    }

}
