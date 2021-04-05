package dev.peertosir.littlemanager.dto;

import dev.peertosir.littlemanager.model.Account;
import dev.peertosir.littlemanager.model.Event;

import java.util.List;

public class UserDto {
    private long id;
    private String username;
    private String lastName;
    private Account account;
    private List<Event> events;

    public UserDto(long id, String username, String lastName, Account account, List<Event> events) {
        this.id = id;
        this.username = username;
        this.lastName = lastName;
        this.account = account;
        this.events = events;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getLastName() {
        return lastName;
    }

    public Account getAccount() {
        return account;
    }

    public List<Event> getEvents() {
        return events;
    }
}
