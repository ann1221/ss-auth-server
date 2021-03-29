package com.test.authserver.model.entity;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinTable(name="role_authority",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Authority> authorities;

    @Override
    @Transient
    public String getAuthority() {
        return ROLE_PREFIX + name.toUpperCase();
    }
}
