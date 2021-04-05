package dev.peertosir.littlemanager.view;


import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class MainMenuView {

    private static Scanner scanner = new Scanner(System.in);

    public static String ShowStartMenu() {
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
}
