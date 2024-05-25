package org.vtb.practice.task04.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Logins")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "access_date")
    private Timestamp access_date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user_id;

    @Getter
    @Column(name = "application")
    private String application;

    public Login() {
    }

    public Login(Timestamp access_date, User user_id, String application) {
        this.access_date = access_date;
        this.user_id = user_id;
        this.application = application;
    }
}
