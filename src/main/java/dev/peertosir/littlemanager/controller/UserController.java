package dev.peertosir.littlemanager.controller;

import dev.peertosir.littlemanager.exceptions.NotFoundException;
import dev.peertosir.littlemanager.exceptions.UserNotFoundException;
import dev.peertosir.littlemanager.model.Account;
import dev.peertosir.littlemanager.model.User;
import dev.peertosir.littlemanager.repository.abstraction.AccountRepository;
import dev.peertosir.littlemanager.repository.abstraction.UserRepository;
import dev.peertosir.littlemanager.repository.impl.JavaIOAccountRepository;
import dev.peertosir.littlemanager.repository.impl.JavaIOUserRepository;
import dev.peertosir.littlemanager.view.UserAuthView;
import dev.peertosir.littlemanager.view.UserView;

public class UserController {
    private static final UserRepository<User, Long> userRepository = new JavaIOUserRepository();
    private static final AccountRepository<Account, Long> accountRepository = new JavaIOAccountRepository();

    public static void getUser(long id) {
        User user;
        Account account;
        try {
            user = userRepository.getById(id);
            account = accountRepository.getById(user.getAccount());
        } catch (NotFoundException ex) {
            System.out.println("User not found. How can it be?");
            return;
        }

        String userInput = UserView.getUserInfo(user.getId(), user.getUsername(), user.getLastName(),
                account.getCreatedAt(), account.getStatus().name());

        switch (userInput) {
            case "back":
                MainMenuController.MainMenu(user);
                break;
            case "myevents":
                EventController.getAllEvents(user, true);
                break;
            case "edit":
                UserController.editUser(user.getId());
                break;
            case "q":
                System.out.println("Bye-bye");
                break;
        }
    }

    public static void editUser(long id) {
        User user;
        try {
             user = userRepository.getById(id);
        } catch (NotFoundException ex) {
            System.out.println("Something went really wrong");
            return;
        }

        if (UserView.getChangeRequest("username")) {
            String username = getUsername();
            user.setUsername(username);
        }

        if (UserView.getChangeRequest("last name")) {
            String lastName = UserView.requestFieldFromUser("last name", 0);
            user.setLastName(lastName);
        }
        if (UserView.getChangeRequest("password")) {
            String password = UserView.requestFieldFromUser("password", 5);
            user.setPassword(password);
        }

        userRepository.update(user, id);
        UserView.showUserUpdatedMessage();
        MainMenuController.MainMenu(user);
    }

    protected static String getUsername() {
        String username = UserView.requestFieldFromUser("username", 4);
        User sameUsernameUser;
        while (true) {
            try {
                sameUsernameUser = userRepository.getByUsername(username);
                System.out.println("User with this" + username + "already exists");
                username = UserAuthView.requestFieldFromUser("username", 4);
            } catch (UserNotFoundException ex) {
                break;
            }
        }
        return username;
    }
}
