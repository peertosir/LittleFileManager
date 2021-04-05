package dev.peertosir.littlemanager.repository.abstraction;

import dev.peertosir.littlemanager.exceptions.UserNotFoundException;

import java.io.Serializable;

public interface UserRepository <User, Long> extends GenericRepository<User, Long>{
    User getByUsername(String username) throws UserNotFoundException;
}
