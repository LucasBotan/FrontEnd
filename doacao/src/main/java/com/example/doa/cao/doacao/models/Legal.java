package com.example.doa.cao.doacao.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Legal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "term_of_use")
    private String text;

}
