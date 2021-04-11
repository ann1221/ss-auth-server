package com.test.authserver.model.entity;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "auser")
@Data
public class AuthUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    @Column(name = "expired_at")
    private Date expiredAt = null;

    @Column(name = "lock_until")
    private Date lockUntil = null;

    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled = true;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JoinTable(name="auser_role",
//            joinColumns = @JoinColumn(name = "auser_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private List<Role> roles;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "auser_role",
            joinColumns = { @JoinColumn(name = "auser_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") }
    )
    private Set<Role> roles;

//    @ManyToMany
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    @JoinTable(name="auser_authority",
//            joinColumns = @JoinColumn(name = "auser_id"),
//            inverseJoinColumns = @JoinColumn(name = "authority_id"))
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<Authority> authorities;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "auser_authority",
            joinColumns = { @JoinColumn(name = "auser_id") },
            inverseJoinColumns = { @JoinColumn(name = "authority_id") }
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private Set<Authority> authorities;

    public Boolean isAccountNonLocked() {
        if (lockUntil == null) return true;
        return lockUntil.before(new Date());
    }

    public Boolean isAccountNonExpired() {
        if (expiredAt == null) return true;
        return expiredAt.after(new Date());
    }
}

