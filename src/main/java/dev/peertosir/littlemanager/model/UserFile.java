package dev.peertosir.littlemanager.model;

import dev.peertosir.littlemanager.model.enums.FileStatus;

public class UserFile extends BaseEntity {
    private String fileName;
    private FileStatus fileStatus;
    private String content;

    public UserFile(long id, String fileName, FileStatus fileStatus, String content) {
        setId(id);
        this.fileName = fileName;
        this.fileStatus = fileStatus;
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FileStatus getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(FileStatus fileStatus) {
        this.fileStatus = fileStatus;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
