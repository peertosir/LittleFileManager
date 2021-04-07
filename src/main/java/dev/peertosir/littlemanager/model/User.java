package dev.peertosir.littlemanager.model;

import java.io.Serializable;
import java.util.List;

public class User extends BaseEntity {
    private String username;
    private String lastName;
    private long account;

    private String password;

    public User(long id, String username, String lastName, long accountId,  String password) {
        setId(id);
        this.username = username;
        this.lastName = lastName;
        this.account = accountId;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getAccount() {
        return account;
    }

    public void setAccount(long account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", username='" + username + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
