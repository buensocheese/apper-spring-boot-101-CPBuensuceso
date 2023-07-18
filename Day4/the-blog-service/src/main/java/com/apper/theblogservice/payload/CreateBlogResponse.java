package com.apper.theblogservice.payload;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateBlogResponse {
    private String id;
    private String bloggerId;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdated;
}
