package dev.peertosir.littlemanager.controller;

import dev.peertosir.littlemanager.model.User;
import dev.peertosir.littlemanager.view.MainMenuView;

import java.util.Scanner;

public class MainMenuController {

    private static final Scanner scanner = new Scanner(System.in);

    public static void StartMenu() {
        String userInput = MainMenuView.showStartMenu();
        switch (userInput) {
            case "login":
                AuthController.login();
                break;
            case "reg":
                AuthController.register();
                break;
            case "q":
                System.out.println("Bye-bye");
                break;
        }
    }

    public static void MainMenu(User user) {
        String userInput = MainMenuView.showMainMenu(user.getUsername());
        switch (userInput) {
            case "profile":
                UserController.getUser(user.getId());
                break;
            case "files":
                UserFileController.getMainUserFileMenu(user);
                break;
            case "events":
                EventController.getAllEvents(user, false);
                break;
            case "q":
                System.out.println("Bye-bye");
                break;
        }
    }
}
