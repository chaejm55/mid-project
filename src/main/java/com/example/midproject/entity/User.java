package com.example.midproject.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "users")
public class User extends BaseTimeEntity {
    @Id
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservationList = new ArrayList<>();

    @Builder
    public User(String name, Role role) {
        this.name = name;
        this.role = role;
    }
}
