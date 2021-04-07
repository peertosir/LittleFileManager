package dev.peertosir.littlemanager.utils;

import java.io.File;
import java.io.IOException;

public class FilesUtility {
    public static void createDirectoryForFiles() throws IOException {
        File dir = new File("files");
        if (dir.exists()) {
            return;
        }
        if (dir.mkdirs()) {
            return;
        }
        throw new IOException("Failed to create directory '" + dir.getAbsolutePath() + "' for an unknown reason.");
    }

    public static void createDirectoryForUserFiles() throws IOException {
        File dir = new File("files/users_files");
        if (dir.exists()) {
            return;
        }
        if (dir.mkdirs()) {
            return;
        }
        throw new IOException("Failed to create directory '" + dir.getAbsolutePath() + "' for an unknown reason.");
    }
}
