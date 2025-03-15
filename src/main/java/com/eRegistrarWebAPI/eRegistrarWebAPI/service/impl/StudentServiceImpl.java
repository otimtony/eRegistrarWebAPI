package com.eRegistrarWebAPI.eRegistrarWebAPI.service.impl;

import com.eRegistrarWebAPI.eRegistrarWebAPI.dto.StudentDTO;
import com.eRegistrarWebAPI.eRegistrarWebAPI.model.Student;
import com.eRegistrarWebAPI.eRegistrarWebAPI.repository.StudentRepository;
import com.eRegistrarWebAPI.eRegistrarWebAPI.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StudentDTO> getStudentById(Long id) {
        return studentRepository.findById(id).map(this::convertToDTO);
    }

    @Override
    public StudentDTO registerStudent(StudentDTO studentDTO) {
        Student student = convertToEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return convertToDTO(savedStudent);
    }

    @Override
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        return studentRepository.findById(id).map(existingStudent -> {
            existingStudent.setStudentNumber(studentDTO.getStudentNumber());
            existingStudent.setCgpa(studentDTO.getCgpa());
            existingStudent.setFirstName(studentDTO.getFirstName());
            existingStudent.setFirstName(studentDTO.getLastName());
            existingStudent.setMiddleName(studentDTO.getMiddleName());
            existingStudent.setIsInternational(studentDTO.getIsInternational());
            existingStudent.setEnrollmentDate(studentDTO.getEnrollmentDate());

            Student updatedStudent = studentRepository.save(existingStudent);
            return convertToDTO(updatedStudent);
        }).orElseThrow(
                () -> new RuntimeException("Student not found with id: " + id)
        );
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    private StudentDTO convertToDTO(Student student){
        return new StudentDTO(
                student.getStudentId(),
            student.getStudentNumber(),
            student.getFirstName(),
            student.getMiddleName(),
            student.getLastName(),
            student.getCgpa(),
            student.getEnrollmentDate(),
            student.getIsInternational()
        );
    }

    private Student convertToEntity(StudentDTO dto) {
        return new Student(
                null, // Student ID is auto-generated
                dto.getStudentNumber(),
                dto.getFirstName(),
                dto.getMiddleName(),
                dto.getLastName(),
                dto.getCgpa(),
                dto.getEnrollmentDate(),
                dto.getIsInternational()
        );
    }
}
