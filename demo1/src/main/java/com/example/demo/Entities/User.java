package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Getter
    @Column(nullable = false)
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    @Getter
    @Column(nullable = false)
    private String password;
    @Getter
    @Column(nullable = false, unique = true)
    private String email;
    @Getter
    @Column(nullable = false)
    private String firstName;
    @Getter
    @Column(nullable = false)
    private String lastName;
    @Getter
    @Column(nullable = false)
    private Boolean isDeleted;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User(String username, String password, String email, String firstName, String lastName, Boolean isDeleted) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isDeleted = isDeleted;
    }


}
