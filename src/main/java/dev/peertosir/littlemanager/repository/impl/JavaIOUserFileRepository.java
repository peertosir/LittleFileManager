package dev.peertosir.littlemanager.repository.impl;

import dev.peertosir.littlemanager.exceptions.NotFoundException;
import dev.peertosir.littlemanager.exceptions.UserFileNotFoundException;
import dev.peertosir.littlemanager.model.UserFile;
import dev.peertosir.littlemanager.model.enums.FileStatus;
import dev.peertosir.littlemanager.repository.abstraction.UserFileRepository;
import dev.peertosir.littlemanager.utils.Helpers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JavaIOUserFileRepository implements UserFileRepository<UserFile, Long> {

    private String userFilesDir;
    private String filesDir;

    public JavaIOUserFileRepository() {
        this.filesDir = System.getProperty("user.dir") + System.getProperty("file.separator") +
                "files" + System.getProperty("file.separator");
        this.userFilesDir = System.getProperty("user.dir") + System.getProperty("file.separator") +
                "files" + System.getProperty("file.separator") + "users_files" + System.getProperty("file.separator");
    }

    @Override
    public List<UserFile> getAll() {
        List<UserFile> returnValue = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filesDir + "files.txt"))) {
            returnValue = ((List<UserFile>)ois.readObject());
        } catch (FileNotFoundException | NullPointerException e) {
            System.out.print("");
        } catch (IOException | ClassNotFoundException ioException) {
            System.out.println("IO error occured");
        }
        return returnValue;
    }

    @Override
    public List<UserFile> getAllActive() {
        List<UserFile> returnValue = getAll().stream().filter(file -> file.getFileStatus() == FileStatus.ACTIVE)
                .collect(Collectors.toList());
        return returnValue;
    }

    @Override
    public String getFileContent(Long id) throws NotFoundException {
        UserFile file = getById(id);
        String fileContent = "";
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(userFilesDir + file.getFileName()))) {
            while ((line = reader.readLine()) != null) {
                fileContent += line;
            }
        } catch (IOException ex) {
            throw new NotFoundException();
        }
        return fileContent;    }

    @Override
    public UserFile getById(Long id) throws NotFoundException {
        List<UserFile> files = getAllActive().stream().filter(userFile -> userFile.getId() == id).collect(Collectors.toList());
        if (files.size() == 0 || files.get(0).getFileStatus() == FileStatus.DELETED) {
            throw new UserFileNotFoundException();
        }
        return files.get(0);
    }

    @Override
    public long create(UserFile userFile) {
        List<UserFile> files = getAll();
        userFile.setId(Helpers.calcEntityId(files, userFile));
        files.add(userFile);

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filesDir + "files.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter((userFilesDir + userFile.getFileName()))))
        {
            oos.writeObject(files);
            writer.write(userFile.getContent());
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return userFile.getId();
    }

    @Override
    public void update(UserFile userFile, Long id) {
        UserFile fileToChange;
        List<UserFile> files = getAll();
        try {
            fileToChange = getById(id);
        } catch (NotFoundException ex) {
            System.out.println("File you want to change not found");
            return;
        }

        if (!fileToChange.getFileName().equals(userFile.getFileName())) {
            File file = new File(userFilesDir + fileToChange.getFileName());
            file.renameTo(new File(userFilesDir + userFile.getFileName()));
            fileToChange.setFileName(userFile.getFileName());
        }

        files = files.stream().map(file -> {
            if (file.getId() == id) {
                file = fileToChange;
            }
            return file;
        }).collect(Collectors.toList());

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filesDir + "files.txt"));
            BufferedWriter writer = new BufferedWriter(new FileWriter((filesDir + fileToChange.getFileName()))))
        {
            oos.writeObject(files);
            if (!fileToChange.getContent().equals(userFile.getContent())) {
                writer.write(userFile.getContent());
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        UserFile fileToDelete;
        List<UserFile> files = getAll();
        try {
             fileToDelete = getById(id);
        } catch (NotFoundException ex) {
            System.out.println("File not found");
            return;
        }

        fileToDelete.setFileStatus(FileStatus.DELETED);
        if (new File(userFilesDir + fileToDelete.getFileName()).delete()) {
            System.out.println("File successfully deleted");
        } else {
            System.out.println("There is a problem in deleting a file");
        }

        files = files.stream().map(file -> {
            if (file.getId() == id) {
                file = fileToDelete;
            }
            return file;
        }).collect(Collectors.toList());

        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filesDir + "files.txt")))
        {
            oos.writeObject(files);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }

}
