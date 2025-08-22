package org.dhana.training.springjpa.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.dhana.training.springjpa.dto.StudentRequestDto;
import org.dhana.training.springjpa.dto.StudentResponseDto;
import org.dhana.training.springjpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

/**
 * Controller for managing student-related operations.
 * This class will perform Create User and Get User operations.
 */
@RestController
@RequestMapping("/api/students")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Void> createStudent(@Valid @RequestBody StudentRequestDto studentDto) {
        var studentResponseDto = studentService.createStudent(studentDto);
        log.info("Creating a new student");
        return ResponseEntity
                .created(URI.create("/api/students/" + studentResponseDto.id()))
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable String id) {
        log.info("Fetching student with ID: {}", id);
        var student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }
}
