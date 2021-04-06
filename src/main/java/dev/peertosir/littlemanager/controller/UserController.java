package dev.peertosir.littlemanager.controller;

import dev.peertosir.littlemanager.exceptions.NotFoundException;
import dev.peertosir.littlemanager.exceptions.UserNotFoundException;
import dev.peertosir.littlemanager.model.Account;
import dev.peertosir.littlemanager.model.User;
import dev.peertosir.littlemanager.repository.abstraction.AccountRepository;
import dev.peertosir.littlemanager.repository.abstraction.UserRepository;
import dev.peertosir.littlemanager.repository.impl.JavaIOAccountRepository;
import dev.peertosir.littlemanager.repository.impl.JavaIOUserRepository;
import dev.peertosir.littlemanager.view.MainMenuView;
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

        String userInput = UserView.showUserInfo(user.getId(), user.getUsername(), user.getLastName(),
                account.getCreatedAt(), account.getStatus().name());
        switch (userInput) {
            case "back":
                MainMenuController.MainMenu(user);
                break;
            case "q":
                System.out.println("Bye-bye");
                break;
        }
    }

    public static void editUser(long id) {
        try {
            User user = userRepository.getById(id);
        } catch (NotFoundException ex) {
            System.out.println("Something went really wrong");
            return;
        }
        
    }

}
