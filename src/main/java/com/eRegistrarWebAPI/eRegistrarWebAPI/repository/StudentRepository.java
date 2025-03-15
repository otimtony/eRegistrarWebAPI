package com.eRegistrarWebAPI.eRegistrarWebAPI.repository;

import com.eRegistrarWebAPI.eRegistrarWebAPI.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
