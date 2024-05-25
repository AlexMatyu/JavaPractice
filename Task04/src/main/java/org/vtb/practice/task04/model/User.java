package org.vtb.practice.task04.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Getter
    @Column(name = "fio")
    private String fio;

    @OneToMany(mappedBy = "user_id")
    private List<Login> logins;

    public User() {
    }

    public User(String username, String fio) {
        this.username = username;
        this.fio = fio;
    }
}
