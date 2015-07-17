package ru.wv.persistence.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USERS", uniqueConstraints = {
        @UniqueConstraint(name = "user_email_unique", columnNames = {"email"}),
        @UniqueConstraint(name = "user_name_unique", columnNames = {"name"})
})
@SequenceGenerator(name = "userIdGenerator", sequenceName = "user_id_seq")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdGenerator")
    private long id;
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String pwdHash;

    private String token;
    private long tokenExpires;

    @NotNull
    private Role role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwdHash() {
        return pwdHash;
    }

    public void setPwdHash(String pwdHash) {
        this.pwdHash = pwdHash;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getTokenExpires() {
        return tokenExpires;
    }

    public void setTokenExpires(long tokenExpires) {
        this.tokenExpires = tokenExpires;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
