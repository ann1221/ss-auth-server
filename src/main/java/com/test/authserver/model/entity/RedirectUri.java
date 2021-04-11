package com.test.authserver.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "redirect")
@Data
public class RedirectUri {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
}
