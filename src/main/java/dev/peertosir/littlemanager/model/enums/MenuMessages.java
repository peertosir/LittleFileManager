package dev.peertosir.littlemanager.model.enums;

public enum MenuMessages {
    GREETING("Welcome to LittleFileManager!\nDo you have an account?"),
    STARTUP("Launching Little File Manager by peertosir");

    MenuMessages(String message) {
        this.message = message;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
