package dev.peertosir.littlemanager.repository.abstraction;

import java.util.List;

public interface UserFileRepository<UserFile, Long> extends GenericRepository<UserFile, Long> {
    List<UserFile> getAllActive();
}
