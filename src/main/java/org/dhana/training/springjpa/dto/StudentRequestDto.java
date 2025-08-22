package org.dhana.training.springjpa.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record StudentRequestDto(
        @NotEmpty(message = "Name cannot be blank.")
        String name,

        @Email(message = "Email should be a valid email.")
        String email,

        @Size(min = 10, max = 10, message = "Phone number must be exactly 10 digits.")
        String phoneNumber) {
}
