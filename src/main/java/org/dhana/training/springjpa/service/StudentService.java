package org.dhana.training.springjpa.service;

import org.dhana.training.springjpa.dto.StudentRequestDto;
import org.dhana.training.springjpa.dto.StudentResponseDto;

public interface StudentService {
    StudentResponseDto createStudent(StudentRequestDto studentRequestDto);

    StudentResponseDto getStudentById(String id);
}
