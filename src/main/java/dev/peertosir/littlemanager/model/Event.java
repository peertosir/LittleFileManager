package dev.peertosir.littlemanager.model;

import dev.peertosir.littlemanager.model.enums.FileStatus;

import java.util.Date;

public class Event extends BaseEntity {
    private UserFile userFile;
    private Date occuredAt;
    private FileStatus fileStatus;
    private Long userId;

    public Event(long id, UserFile userFile, Date occuredAt, FileStatus status, Long userId) {
        setId(id);
        this.userFile = userFile;
        this.occuredAt = occuredAt;
        this.fileStatus = status;
        this.userId = userId;
    }


    public UserFile getFile() {
        return userFile;
    }

    public void setFile(UserFile userFile) {
        this.userFile = userFile;
    }

    public Date getOccuredAt() {
        return occuredAt;
    }

    public void setOccuredAt(Date occuredAt) {
        this.occuredAt = occuredAt;
    }

    public FileStatus getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(FileStatus fileStatus) {
        this.fileStatus = fileStatus;
    }

    public UserFile getUserFile() {
        return userFile;
    }

    public void setUserFile(UserFile userFile) {
        this.userFile = userFile;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
