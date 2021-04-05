package dev.peertosir.littlemanager.model;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
