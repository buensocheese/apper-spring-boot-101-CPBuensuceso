package com.apper.estore.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

// Added necessary packages
import java.time.LocalDate;
import java.time.Period;

@Data
public class CreateUserRequest {
    @NotBlank(message = "`email` is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "`password` is required")
    @Size(min = 8, message = "`password` must be at least 8 characters")
    private String password;

    @JsonProperty("first_name")
    @NotBlank(message = "`first_name` is required")
    private String firstName;

    @JsonProperty("middle_name")
    private String middleName;

    @JsonProperty("last_name")
    @NotBlank(message = "`last_name` is required")
    private String lastName;

    @JsonProperty("birth_date")
    @NotBlank(message = "`birth_date` is required")
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "Invalid birth date format")
    private String birthDate;

    // Age Checker based on birthDate
    public boolean isAgeValid() {
        LocalDate today = LocalDate.now();
        LocalDate parsedBirthDate = LocalDate.parse(birthDate);
        int age = Period.between(parsedBirthDate, today).getYears();
        return age >= 15;
    }
}
