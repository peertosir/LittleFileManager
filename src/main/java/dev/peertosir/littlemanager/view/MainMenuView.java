package dev.peertosir.littlemanager.view;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class MainMenuView extends BaseView {

    public static String showStartMenu() {
        System.out.println("Welcome to LittleFileManager!\nDo you have an account?");
        String menu = "1. Enter 'login' to log in\n2. Enter 'reg' to register\n3. Enter 'q' to quit";
        String[] options = {"login", "reg", "q"};
        return getMenuUserChoice(options, menu);
    }

    public static String showMainMenu(String username) {
        System.out.println("Hello, " + username);
        String[] options = {"profile", "files", "q", "events"};
        String menu = "1. Enter 'profile' to show your profile info" +
                "\n2. Enter 'files' to work with files" +
                "\n3. Enter 'events' to show all users events" +
                "\n4. Enter 'q' to quit";
        return getMenuUserChoice(options, menu);
    }
}
