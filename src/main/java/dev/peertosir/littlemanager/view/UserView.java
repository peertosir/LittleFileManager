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
                "\n3. Enter 'myevents' to show your events\n3. Enter 'q' to quit";
        String[] options = {"back", "q", "edit", "myevents"};
        return getMenuUserChoice(options, menu);
    }

    public static void showUserUpdatedMessage() {
        System.out.println("User successfully updated");
    }
}
