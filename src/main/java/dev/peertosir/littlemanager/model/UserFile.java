package dev.peertosir.littlemanager.model;

public class UserFile extends BaseEntity {
    private String fileName;

    public UserFile(long id, String fileName) {
        setId(id);
        this.fileName = fileName;
    }


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
