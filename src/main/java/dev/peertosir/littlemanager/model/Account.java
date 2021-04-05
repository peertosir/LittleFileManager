package dev.peertosir.littlemanager.model;

import dev.peertosir.littlemanager.model.enums.AccountStatus;

public class Account extends BaseEntity {
    private String createdAt;
    private AccountStatus status;

    public Account(long id, String createdAt, AccountStatus status) {
        this.setId(id);
        this.createdAt = createdAt;
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }
}
