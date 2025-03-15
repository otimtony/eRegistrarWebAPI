package com.eRegistrarWebAPI.eRegistrarWebAPI.service;

import com.eRegistrarWebAPI.eRegistrarWebAPI.dto.StudentDTO;
import com.eRegistrarWebAPI.eRegistrarWebAPI.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    List<StudentDTO> getAllStudents();
    Optional<StudentDTO> getStudentById(Long id);
    StudentDTO registerStudent(StudentDTO studentDTO);
    StudentDTO updateStudent(Long id, StudentDTO studentDTO);
    void deleteStudent(Long id);
}
