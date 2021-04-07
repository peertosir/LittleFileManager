package dev.peertosir.littlemanager;

import dev.peertosir.littlemanager.controller.MainMenuController;
import dev.peertosir.littlemanager.utils.FilesUtility;

import java.io.IOException;

public class Manager {
    public static void main(String[] args) {
        System.out.println("Launching Little File Manager by peertosir");
        try {
            FilesUtility.createDirectoryForFiles();
            FilesUtility.createDirectoryForUserFiles();
            MainMenuController.StartMenu();
        } catch (IOException ioException) {
            System.out.println("Cannot create directory for files. Aborting program...");
        }
    }
}
