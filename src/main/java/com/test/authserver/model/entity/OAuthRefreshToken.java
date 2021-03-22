package com.test.authserver.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "oauth_refresh_token")
@Data
public class OAuthRefreshToken {
    @Id
    @Column(name = "token_id")
    private String tokenId;

    @Column(name = "token", nullable = false)
    @Lob
    private Blob token;

    @Column(name="authentication")
    @Lob
    private Blob authentication;
}
