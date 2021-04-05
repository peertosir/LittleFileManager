package dev.peertosir.littlemanager.model;

import java.io.Serializable;
import java.util.List;

public class User extends BaseEntity {
    private String username;
    private String lastName;
    private long account;
    private List<Event> events;
    private String password;

    public User(long id, String username, String lastName, long accountId, List<Event> events, String password) {
        setId(id);
        this.username = username;
        this.lastName = lastName;
        this.account = accountId;
        this.events = events;
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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
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
