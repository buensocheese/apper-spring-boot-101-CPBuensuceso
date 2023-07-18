package com.apper.theblogservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "BLOG")
@Data
public class Blog {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "BODY", columnDefinition = "TEXT")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BLOGGER_ID")
    private Blogger blogger;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "LAST_UPDATED")
    private LocalDateTime lastUpdated;

    @PrePersist
    public void setInitialTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        lastUpdated = now;
    }

    @PreUpdate
    public void setLastUpdated() {
        lastUpdated = LocalDateTime.now();
    }
}
