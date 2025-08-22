package org.dhana.training.springjpa.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {
     @ExceptionHandler(StudentNotFoundException.class)
     public ResponseEntity<Map> handleStudentNotFound(StudentNotFoundException ex) {
         Map<String, String> errorResponse = new HashMap<>();
         String details = ex.getMessage();

         errorResponse.put("error", "Student not found");
         errorResponse.put("details", details);
         return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
     }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Map> handleGlobalError(Throwable ex) {
        Map<String, String> errorResponse = new HashMap<>();
        String details = ex.getMessage();

        errorResponse.put("error", "Internal Server error.");
        errorResponse.put("details", details);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        log.error("Validation error occurred: {}", errors);
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, String> errorResponse = new HashMap<>();

        String details = ex.getRootCause() != null
                ? ex.getRootCause().getMessage()  // Full DB error message
                : ex.getMessage();

        errorResponse.put("error", "Database error occurred");
        errorResponse.put("details", details);
        log.error("Data Integrity error occurred: {}", errorResponse);


        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleInvalidJson(HttpMessageNotReadableException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Malformed JSON request");
        errorResponse.put("details", "Provide proper JSON format in the request body");

        return ResponseEntity.badRequest().body(errorResponse);
    }
}
