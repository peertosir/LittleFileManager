package dev.peertosir.littlemanager.view;

import dev.peertosir.littlemanager.exceptions.UserNotFoundException;
import dev.peertosir.littlemanager.utils.AuthValidator;

import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class UserAuthView {
    private static final Scanner scanner = new Scanner(System.in);

    public static void showEnterFieldMessage(String field) {
        System.out.println("Enter " + field + ": ");
    }

    public static boolean requestYesNo() {
        System.out.println("Enter 'y' - to continue/ 'n' - to return");
        String[] options = {"y", "n"};
        String userInput = scanner.nextLine().toLowerCase(Locale.ROOT).strip();
        while (Arrays.stream(options).noneMatch(userInput::equals)) {
            System.out.println("This is not valid variant. Please enter only 'n' or 'y'");
            userInput = scanner.nextLine().toLowerCase(Locale.ROOT).strip();
        }
        return userInput.equals("y");
    }

    public static String requestFieldFromUser(String field, int minValidLength) {
        String data;
        boolean isValid = false;
        do {
            showEnterFieldMessage(field);
            data = scanner.nextLine().toLowerCase(Locale.ROOT).strip();
            isValid = AuthValidator.validate(data, minValidLength);

            if (!isValid) {
                showValidationLengthFailedMessage(3, "Login");
            }
        } while (!isValid);
        return data;
    }

    public static void showValidationLengthFailedMessage(long length, String field) {
        System.out.println(field + " should be at least " + length + " characters long");
    }
    public static void showUsernameDuplicated() {
        System.out.println("Username is already taken");
    }

    public static void userCreatedSuccessfully(String username) {
        System.out.println("User '" + username +"' has been registered successfully. You can now login");
    }

}
