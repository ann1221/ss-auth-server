package com.test.authserver.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "oauth_access_token")
@Data
public class OAuthAccessToken {
    @Id
    @Column(name = "authentication_id")
    private String authenticationId;

    @Column(name = "token_id", nullable = false)
    private String tokenId;

    @Column(name = "token")
    @Lob
    private Blob token;

    @Column(name="user_name", nullable = false)
    private String userName;

    @Column(name="client_id", nullable = false)
    private String ClientId;

    @Column(name="authentication")
    @Lob
    private Blob authentication;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;
}



