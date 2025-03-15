package com.eRegistrarWebAPI.eRegistrarWebAPI.controller;

import com.eRegistrarWebAPI.eRegistrarWebAPI.dto.StudentDTO;
import com.eRegistrarWebAPI.eRegistrarWebAPI.model.Student;
import com.eRegistrarWebAPI.eRegistrarWebAPI.repository.StudentRepository;
import com.eRegistrarWebAPI.eRegistrarWebAPI.service.impl.StudentServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentDTO> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id){
        Optional<StudentDTO> studentDTO = studentService.getStudentById(id);
        return studentDTO.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public ResponseEntity<StudentDTO> registerStudent(@Valid @RequestBody StudentDTO studentDTO){
        StudentDTO savedStudent = studentService.registerStudent(studentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO){
        studentDTO.setStudentId(id); // Ensure ID is set for update
        return ResponseEntity.ok(studentService.updateStudent(id, studentDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<StudentDTO> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
