package com.eRegistrarWebAPI.eRegistrarWebAPI.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentDTO {

    private Long studentId;
    @NotBlank(message = "Student number is required")
    private String studentNumber;

    @NotBlank(message = "First Name is required")
    private String FirstName;

    private String MiddleName;

    @NotBlank(message = "Last Name is required")
    private String LastName;

    @DecimalMin(value = "0.0", inclusive = true, message = "CGPA must be at least 0.0")
    @DecimalMax(value = "4.0", inclusive = true, message = "CGPA must not exceed 4.0")
    private Double cgpa;

    @NotNull(message = "Enrollment date is required")
    private LocalDate enrollmentDate;

    @NotBlank(message = "isInternation is required")
    private String isInternational;

}
