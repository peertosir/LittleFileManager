package dev.peertosir.littlemanager.repository.impl;

import dev.peertosir.littlemanager.exceptions.NotFoundException;
import dev.peertosir.littlemanager.exceptions.UserNotFoundException;
import dev.peertosir.littlemanager.model.User;
import dev.peertosir.littlemanager.repository.abstraction.UserRepository;
import dev.peertosir.littlemanager.utils.Helpers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JavaIOUserRepository implements UserRepository<User, Long> {

    private String filesDir;
    private final JavaIOAccountRepository accountRepository = new JavaIOAccountRepository();

    public JavaIOUserRepository() {
        this.filesDir = System.getProperty("user.dir") + System.getProperty("file.separator") +
                "files" + System.getProperty("file.separator");
    }

    @Override
    public List<User> getAll() {
        List<User> returnValue = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filesDir + "users.txt"))) {
            returnValue = ((List<User>)ois.readObject());
        } catch (FileNotFoundException | NullPointerException e) {
            System.out.print("");
        } catch (IOException | ClassNotFoundException ioException) {
            System.out.println("IO error occured");
        }
        return returnValue;
    }

    @Override
    public User getById(Long id) throws UserNotFoundException {
        List<User> users = getAll().stream().filter(user -> user.getId() == id).collect(Collectors.toList());
        if (users.size() == 0) {
            throw new UserNotFoundException();
        }
        return users.get(0);
    }

    @Override
    public long create(User user){
        List<User> users = getAll();
        user.setId(Helpers.calcEntityId(users, user));
        users.add(user);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filesDir + "users.txt") {
        }))
        {
            oos.writeObject(users);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return user.getId();
    }

    @Override
    public void update(User user, Long id) {
        List<User> users = getAll();
        User userToEdit;
        try {
            userToEdit = getById(id);
        } catch (NotFoundException ex) {
            System.out.println("Something strange happened. We cannot find you :(");
            return;
        }
        userToEdit.setUsername(user.getUsername());
        userToEdit.setPassword(user.getPassword());
        userToEdit.setLastName(user.getLastName());

        users = users.stream().map(u -> {
            if (u.getId() == id) {
                u = userToEdit;
            }
            return u;
        }).collect(Collectors.toList());

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filesDir + "users.txt") {
        }))
        {
            oos.writeObject(users);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        List<User> users = getAll();
        users = users.stream().filter(u -> u.getId() != id).collect(Collectors.toList());
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filesDir + "users.txt") {
        }))
        {
            oos.writeObject(users);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public User getByUsername(String username) throws UserNotFoundException {
        List<User> users = getAll().stream().filter(user -> user.getUsername().equals(username))
                .collect(Collectors.toList());
        if (users.size() == 0) {
            throw new UserNotFoundException();
        }
        return users.get(0);
    }
}
