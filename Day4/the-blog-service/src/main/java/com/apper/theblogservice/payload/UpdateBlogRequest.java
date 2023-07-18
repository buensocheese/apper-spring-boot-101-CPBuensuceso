package com.apper.theblogservice.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateBlogRequest {

    // Title Requirements
    @JsonProperty("title")
    @NotBlank(message = "Title is required")
    private String title;

    // Body Requirements
    @JsonProperty("body")
    @NotBlank(message = "Body is required")
    private String body;

}
