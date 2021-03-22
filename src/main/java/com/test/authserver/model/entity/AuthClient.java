package com.test.authserver.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "aclient")
@Data
public class AuthClient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "secret")
    private String secret;

    @Column(name = "is_auto_approved")
    private Boolean isAutoApproved;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "aclient_grant_type",
            joinColumns = { @JoinColumn(name = "aclient_id") },
            inverseJoinColumns = { @JoinColumn(name = "grant_type_id") }
    )
    Set<GrantType> grantTypes;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "aclient_resource",
            joinColumns = { @JoinColumn(name = "aclient_id") },
            inverseJoinColumns = { @JoinColumn(name = "resource_id") }
    )
    Set<Resource> resources;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "aclient_scope",
            joinColumns = { @JoinColumn(name = "aclient_id") },
            inverseJoinColumns = { @JoinColumn(name = "scope_id") }
    )
    Set<Scope> scopes;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "aclient_redirect",
            joinColumns = { @JoinColumn(name = "aclient_id") },
            inverseJoinColumns = { @JoinColumn(name = "redirect_id") }
    )
    Set<RedirectUri> redirectUris;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinTable(
            name = "aclient_authority",
            joinColumns = { @JoinColumn(name = "aclient_id") },
            inverseJoinColumns = { @JoinColumn(name = "authority_id") }
    )
    Set<Authority> authorities;
}
