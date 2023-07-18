package com.apper.theblogservice.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateBloggerRequest {
    // Email Requirements
    @NotBlank(message = "`email` is required")
    @Email(message = "Invalid email format")
    private String email;

    // Password Requirements
    @Size(min = 5, message = "`password` must be at least 5 characters")
    @NotBlank(message = "`password` is required")
    private String password;

    // Name Requirements
    @NotBlank(message = "`Name` is required")
    private String name;
}
