package com.test.authserver.model.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Data
public class Role implements GrantedAuthority {
    private static final String ROLE_PREFIX = "ROLE_";

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Override
    @Transient
    public String getAuthority() {
        return ROLE_PREFIX + name.toUpperCase();
    }
}
