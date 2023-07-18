package com.apper.theblogservice.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BLOGGER")
@Data
public class Blogger {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "Complete_Name")
    private String name;

    @Column(name = "Password")
    private String password;

    @Column(name = "Email") // Unique constraint: no duplicate email addresses.
    private String email;

    @Column(name = "Created_At")
    private LocalDateTime createdAt;

    @Column(name = "Last_Updated")
    private LocalDateTime lastUpdated;

    @OneToMany(mappedBy = "Blogger", cascade = CascadeType.ALL)
    private List<Blog> blogs = new ArrayList<>();

    // Set createdAt and lastUpdated before entity persistence.
    @PrePersist
    public void setCreatedAt() {
        createdAt = LocalDateTime.now();
        lastUpdated = LocalDateTime.now();
    }

    // Update lastUpdated before entity update.
    @PreUpdate
    public void setLastUpdated() {
        lastUpdated = LocalDateTime.now();
    }
}
