package dev.peertosir.littlemanager.repository.abstraction;

import dev.peertosir.littlemanager.exceptions.NotFoundException;
import dev.peertosir.littlemanager.exceptions.UserFileNotFoundException;

import java.util.List;

public interface UserFileRepository<UserFile, Long> extends GenericRepository<UserFile, Long> {
    List<UserFile> getAllActive();
    String getFileContent(Long id) throws NotFoundException;
}
