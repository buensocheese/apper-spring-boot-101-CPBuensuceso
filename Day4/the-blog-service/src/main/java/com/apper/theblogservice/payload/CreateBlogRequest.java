package com.apper.theblogservice.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateBlogRequest {

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Body is required")
    private String body;

    @NotBlank(message = "Blogger ID is required")
    private String bloggerId;
}
