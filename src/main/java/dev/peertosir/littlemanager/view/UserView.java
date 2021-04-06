package dev.peertosir.littlemanager.view;

import java.util.Arrays;
import java.util.Locale;


public class UserView extends BaseView {

    public static String getUserInfo(Long id,
                                     String username, String lastname,
                                     String createdAt, String accountStatus) {
        System.out.println("ID: " + id);
        System.out.println("User: " + username);
        System.out.println("Last Name: " + lastname);
        System.out.println("Account created at: " + createdAt);
        System.out.println("Account status: " + accountStatus);
        String menu = "1. Enter 'back' to return to main menu\n2. Enter 'edit' to update user info" +
                "\n3. Enter 'q' to quit";
        System.out.println(menu);
        String[] options = {"back", "q", "edit"};
        String userInput = scanner.nextLine().toLowerCase(Locale.ROOT);
        while (Arrays.stream(options).noneMatch(userInput::equals)) {
            System.out.println("Please enter valid option.\n" + menu);
            userInput = scanner.nextLine().toLowerCase(Locale.ROOT);
        }
        return userInput;
    }

    public static void showUserUpdatedMessage() {
        System.out.println("User successfully updated");
    }
}
