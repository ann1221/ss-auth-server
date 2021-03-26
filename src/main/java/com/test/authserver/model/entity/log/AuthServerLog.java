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

    public AuthServerLog() {}

    private AuthServerLog(String name, Date date, Level level, String username) {
        this.name = name;
        this.date = date;
        this.level = level;
        this.username = username;
    }

    public static AuthServerLog.AuthServerLogBuilder withLogName(LogName logName) {
        return new AuthServerLogBuilder().logName(logName);
    }

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "date")
    private Date date;

    @Column(name = "description")
    private String description;

    @Column(name = "username")
    private String username;

    @Enumerated(EnumType.STRING)
    private Level level;

    public static class AuthServerLogBuilder {
        private LogName name;
        private Date date;
        private Level level;
        private String username;

        private AuthServerLogBuilder() { }

        public AuthServerLogBuilder logName(LogName logName) {
            this.name = logName;
            return this;
        }

        public AuthServerLogBuilder date(Date date) {
            this.date = date;
            return this;
        }

        public AuthServerLogBuilder currDate() {
            this.date = new Date();
            return this;
        }

        public AuthServerLogBuilder level(Level level) {
            this.level = level;
            return this;
        }

        public AuthServerLogBuilder username(String username) {
            this.username = username;
            return this;
        }

        public AuthServerLog build() {
            return new AuthServerLog(name.getFull(), date, level, username);
        }
    }
}
