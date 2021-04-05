package dev.peertosir.littlemanager.controller;

import dev.peertosir.littlemanager.exceptions.UserNotFoundException;
import dev.peertosir.littlemanager.model.Account;
import dev.peertosir.littlemanager.model.Event;
import dev.peertosir.littlemanager.model.User;
import dev.peertosir.littlemanager.model.enums.AccountStatus;
import dev.peertosir.littlemanager.repository.abstraction.AccountRepository;
import dev.peertosir.littlemanager.repository.abstraction.UserRepository;
import dev.peertosir.littlemanager.repository.impl.JavaIOAccountRepository;
import dev.peertosir.littlemanager.repository.impl.JavaIOUserRepository;
import dev.peertosir.littlemanager.view.MainMenuView;
import dev.peertosir.littlemanager.view.UserAuthView;

import java.util.*;

public class AuthController {

    private static final UserRepository<User, Long> userRepository = new JavaIOUserRepository();
    private static final AccountRepository<Account, Long> accountRepository = new JavaIOAccountRepository();

    public static void register() {
        User user = null;

        String username = UserAuthView.requestFieldFromUser("username", 4);
        while (true) {
            try {
                user = userRepository.getByUsername(username);
                username = UserAuthView.requestFieldFromUser("username", 4);
            } catch (UserNotFoundException ex) {
                break;
            }
        }


        String lastName = UserAuthView.requestFieldFromUser("last name", 0);

        String password = UserAuthView.requestFieldFromUser("password", 5);
        //TODO: add password hashing + validation through hash

        Account account = new Account(0, new Date().toString(), AccountStatus.ACTIVE);
        long accountId = accountRepository.create(account);

        List<Event> events = new ArrayList<>();

        user = new User(0, username, lastName, accountId, events, password);
        userRepository.create(user);
        UserAuthView.userCreatedSuccessfully(username);
        MainMenuController.StartMenu();
    }

    public static void login() {
        User user;
        String username = UserAuthView.requestFieldFromUser("username", 3);
        while (true) {
            try {
                user = userRepository.getByUsername(username);
                break;
            } catch (UserNotFoundException ex) {
                boolean continueTrying = UserAuthView.requestYesNo();
                if (continueTrying) {
                    username = UserAuthView.requestFieldFromUser("username", 4);
                } else {
                    MainMenuController.StartMenu();
                    return;
                }
            }
        }

        String password = UserAuthView.requestFieldFromUser("password", 5);
        while (!password.equals(user.getPassword())) {
            boolean continueTrying = UserAuthView.requestYesNo();
            if (continueTrying) {
                username = UserAuthView.requestFieldFromUser("password", 5);
            } else {
                MainMenuController.StartMenu();
                return;
            }
        }

        MainMenuController.MainMenu(user);
    }

}
