package com.apper;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Account {

    private String id;

    private Double balance;

    private String firstName;
    private String lastName;
    private String username;
    private String clearPassword;
    private String verificationCode;
    private String email;
    private String password;

    private LocalDateTime creationDate;
    private LocalDateTime lastUpdated;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
