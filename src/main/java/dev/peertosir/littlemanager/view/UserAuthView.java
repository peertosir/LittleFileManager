package dev.peertosir.littlemanager.view;

public class UserAuthView extends BaseView {
    public static void showUsernameDuplicated() {
        System.out.println("Username is already taken");
    }

    public static void userCreatedSuccessfully(String username) {
        System.out.println("User '" + username +"' has been registered successfully. You can now login");
    }
}
