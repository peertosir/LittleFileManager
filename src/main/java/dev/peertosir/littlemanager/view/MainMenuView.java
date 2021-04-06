package dev.peertosir.littlemanager.view;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class MainMenuView {

    private static Scanner scanner = new Scanner(System.in);

    public static String showStartMenu() {
        System.out.println("Welcome to LittleFileManager!\nDo you have an account?" +
                "\n1. Enter 'login' to log in\n2. Enter 'reg' to register\n3. Enter 'q' to quit");
        String[] options = {"login", "reg", "q"};
        String userInput = scanner.nextLine().toLowerCase(Locale.ROOT);
        while (Arrays.stream(options).noneMatch(userInput::equals)) {
            System.out.println("Please enter valid option: " +
                    "\n1. Enter 'login' to log in\n2. Enter 'reg' to register\n3. Enter 'q' to quit");
            userInput = scanner.nextLine().toLowerCase(Locale.ROOT);
        }
        return userInput;
    }

    public static String showMainMenu(String username) {
        System.out.println("Hello, " + username);
        String[] options = {"profile", "files", "q"};
        String menu = "1. Enter 'profile' to show your profile info" +
                "\n2. Enter 'files' to work with files" +
                "\n3. Enter 'q' to quit";
        System.out.println(menu);
        String userInput = scanner.nextLine().toLowerCase(Locale.ROOT).strip();
        while (Arrays.stream(options).noneMatch(userInput::equals)) {
            System.out.println("Please enter valid option: " + menu);
            userInput = scanner.nextLine().toLowerCase(Locale.ROOT);
        }
        return userInput;
    }
}
