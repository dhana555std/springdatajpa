package org.dhana.training.springjpa.service;

import org.dhana.training.springjpa.dto.StudentRequestDto;
import org.dhana.training.springjpa.dto.StudentResponseDto;
import org.dhana.training.springjpa.entity.Student;
import org.dhana.training.springjpa.exception.StudentNotFoundException;
import org.dhana.training.springjpa.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepo studentRepo;

    @Override
    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {
        var student =  studentRepo.save(new Student(studentRequestDto.name(), studentRequestDto.email(),
                studentRequestDto.phoneNumber()));
        return new StudentResponseDto(student.getId(), student.getName(), student.getEmail(), student.getPhoneNumber());
    }

    @Override
    public StudentResponseDto getStudentById(String id) {
        var student = studentRepo.findById(id).orElseThrow(
                () -> new StudentNotFoundException("Student not found with id: " + id)
        );
        return new StudentResponseDto(student.getId(), student.getName(), student.getEmail(), student.getPhoneNumber());
    }
}
