package com.example.doa.cao.doacao.models;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Data
@Entity
@Table(name = "TB_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "USER_NAME")
    private String name;

    @Column(nullable = false, unique = true, name = "USER_EMAIL")
    private String email;

    @Column(nullable = false, name = "USER_PASSWORD")
    private String password;

    @Column(name = "USER_PHONE")
    private String phone;

    @Column(name = "USER_GENDER")
    private String gender;

    @Column(name = "USER_BIRTH")
    private String birth;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

}
