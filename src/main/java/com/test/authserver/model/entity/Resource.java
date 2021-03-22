package com.test.authserver.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "resource")
@Data
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
}
