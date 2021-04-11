package com.test.authserver.model.entity.log;

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

    private AuthServerLog(String name, String descr, Date date, Level level, String username, String ipAddr) {
        this.name = name;
        this.description = descr;
        this.date = date;
        this.level = level;
        this.username = username;
        this.ipAddr = ipAddr;
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

    @Column(name = "ip_addr")
    private String ipAddr;

    @Enumerated(EnumType.STRING)
    private Level level;

    public static class AuthServerLogBuilder {
        private LogName name;
        private String descr;
        private Date date;
        private Level level;
        private String username;
        private String ipAddr;

        private AuthServerLogBuilder() { }

        public AuthServerLogBuilder logName(LogName logName) {
            this.name = logName;
            return this;
        }

        public AuthServerLogBuilder description(String descr) {
            this.descr = descr;
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

        public AuthServerLogBuilder ipAddr(String ipAddr) {
            this.ipAddr = ipAddr;
            return this;
        }

        public AuthServerLog build() {
            return new AuthServerLog(name.getFull(), descr, date, level, username, ipAddr);
        }
    }
}
