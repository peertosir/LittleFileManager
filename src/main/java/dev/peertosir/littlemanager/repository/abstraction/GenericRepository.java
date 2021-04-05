package dev.peertosir.littlemanager.repository.abstraction;

import dev.peertosir.littlemanager.exceptions.NotFoundException;
import dev.peertosir.littlemanager.exceptions.UserNotFoundException;

import java.io.IOException;
import java.util.List;

public interface GenericRepository <T, ID> {
    List<T> getAll();
    T getById(ID id) throws NotFoundException;
    long create(T t);
    void update(T t, ID id);
    void delete(ID id);
}
