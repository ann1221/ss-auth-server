package com.test.authserver.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "grant_type")
@Data
public class GrantType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
}
