package com.apper.theblogservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "BLOGGER")
@Data
public class Blogger {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "COMPLETE_NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @Column(name = "LAST_UPDATE")
    private LocalDateTime lastUpdate;

    @PrePersist
    public void setInitialTimestamp() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        lastUpdate = now;
    }

    @PreUpdate
    public void setLastUpdate() {
        lastUpdate = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "blogger", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Blog> blogs;

}