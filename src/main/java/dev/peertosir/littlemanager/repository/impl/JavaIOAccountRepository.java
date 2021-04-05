package dev.peertosir.littlemanager.repository.impl;

import dev.peertosir.littlemanager.exceptions.UserNotFoundException;
import dev.peertosir.littlemanager.model.Account;
import dev.peertosir.littlemanager.model.User;
import dev.peertosir.littlemanager.repository.abstraction.AccountRepository;
import dev.peertosir.littlemanager.utils.Helpers;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class JavaIOAccountRepository implements AccountRepository<Account, Long> {

    private String filesDir;


    public JavaIOAccountRepository() {
        this.filesDir = System.getProperty("user.dir") + System.getProperty("file.separator") +
                "files" + System.getProperty("file.separator");

    }

    @Override
    public List<Account> getAll() {
        List<Account> returnValue = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filesDir + "accounts.txt"))) {
            returnValue = ((List<Account>)ois.readObject());
        } catch (FileNotFoundException | NullPointerException e) {
            System.out.println("No accounts exists yet");
        } catch (IOException | ClassNotFoundException ioException) {
            System.out.println("IO error occured");
        }
        return returnValue;
    }

    @Override
    public Account getById(Long aLong) throws UserNotFoundException {
        return null;
    }

    @Override
    public long create(Account account) {
        List<Account> accs = getAll();
        account.setId(Helpers.calcEntityId(accs, account));
        accs.add(account);
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filesDir + "accounts.txt") {
        }))
        {
            oos.writeObject(accs);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return account.getId();
    }

    @Override
    public void update(Account account, Long aLong) {

    }

    @Override
    public void delete(Long aLong) {

    }
}