package com.test.authserver.model.entity.log;

import com.test.authserver.model.entity.AuthUser;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "aserver_log")
@Data
public class AuthServerLog {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    private AuthUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Log log;

    @Column(name = "date")
    private Date date;
}
