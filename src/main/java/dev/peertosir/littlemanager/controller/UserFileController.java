package dev.peertosir.littlemanager.controller;

import dev.peertosir.littlemanager.model.Event;
import dev.peertosir.littlemanager.model.User;
import dev.peertosir.littlemanager.model.UserFile;
import dev.peertosir.littlemanager.model.enums.FileStatus;
import dev.peertosir.littlemanager.repository.abstraction.EventRepository;
import dev.peertosir.littlemanager.repository.abstraction.UserFileRepository;
import dev.peertosir.littlemanager.repository.impl.JavaIOEventRepository;
import dev.peertosir.littlemanager.repository.impl.JavaIOUserFileRepository;
import dev.peertosir.littlemanager.view.UserFileView;
import dev.peertosir.littlemanager.view.UserView;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class UserFileController {

    private static final UserFileRepository<UserFile, Long> userFileRepository = new JavaIOUserFileRepository();
    private static final EventRepository<Event, Long> eventRepository = new JavaIOEventRepository();

    public static void getMainUserFileMenu(User user) {
        String userInput = UserFileView.showFileMenu();
        switch (userInput) {
            case "showall":
                UserFileController.getAllFiles(user);
                break;
            case "showactive":
                UserFileController.getAllActiveFiles(user);
                break;
            case "createfile":
                UserFileController.createUserFile(user);
                break;
            case "back":
                MainMenuController.MainMenu(user);
                break;
        }
    }

    public static void getAllFiles(User user) {
        List<UserFile> files =  userFileRepository.getAll();
        List<String> fileIds = files.stream().map(file -> String.valueOf(file.getId())).collect(Collectors.toList());
        List<String> fileNames = files.stream().map(UserFile::getFileName).collect(Collectors.toList());
        List<String> fileStatuses = files.stream().map(file -> file.getFileStatus().name())
                .collect(Collectors.toList());

        String userInput = UserFileView.showAllUserFiles(fileIds, fileNames, fileStatuses);

        switch (userInput) {
            case "back":
                UserFileController.getMainUserFileMenu(user);
                break;
            case "q":
                System.out.println("Bye-bye");
                break;
        }
    }

    public static void getAllActiveFiles(User user) {
        List<UserFile> files =  userFileRepository.getAllActive();
        List<String> fileIds = files.stream().map(file -> String.valueOf(file.getId())).collect(Collectors.toList());
        List<String> fileNames = files.stream().map(UserFile::getFileName).collect(Collectors.toList());
        List<String> fileStatuses = files.stream().map(file -> file.getFileStatus().name())
                .collect(Collectors.toList());

        String userInput = UserFileView.showAllActiveUserFiles(fileIds, fileNames, fileStatuses);

        switch (userInput) {
            case "showfile":
                UserFileController.getFileContent(user);
                break;
            case "editfile":
                UserFileController.editFileContent(user);
                break;
            case "deletefile":
                UserFileController.deleteFile(user);
            case "back":
                UserFileController.getMainUserFileMenu(user);
                break;
            case "q":
                System.out.println("Bye-bye");
                break;
        }
    }

    public static void deleteFile(User user) {
        Long fileId;
        try {
            fileId = Long.parseLong(UserFileView.requestFieldFromUser("fileId", 1));
        } catch (NumberFormatException ex) {
            System.out.println("Wrong id format");
            UserFileController.getMainUserFileMenu(user);
            return;
        } catch (Exception ex) {
            System.out.println("File not found or something went wrong");
            UserFileController.getMainUserFileMenu(user);
            return;
        }
        try {
            eventRepository.create(new Event(0, userFileRepository.getById(fileId),
                     new Date(), FileStatus.DELETED, user.getId()));
            userFileRepository.delete(fileId);
        } catch (Exception ex) {
            System.out.println("Strange error");
        }


        UserFileController.getMainUserFileMenu(user);
    }

    public static void editFileContent(User user) {
        long fileId;
        UserFile file;
        String fileContent;

        try {
            fileId = Long.parseLong(UserFileView.requestFieldFromUser("fileId", 1));
            file = userFileRepository.getById(fileId);
            fileContent = userFileRepository.getFileContent(fileId);
        } catch (NumberFormatException ex) {
            System.out.println("Wrong id format");
            UserFileController.getMainUserFileMenu(user);
            return;
        } catch (Exception ex) {
            System.out.println("File not found or something went wrong");
            UserFileController.getMainUserFileMenu(user);
            return;
        }

        if (UserFileView.getChangeRequest("file name")) {
            String fileName = UserFileView.requestFieldFromUser("file name", 2);
            file.setFileName(fileName);
        }

        if (UserView.getChangeRequest("content")) {
            System.out.println("Current content: \n" + fileContent);
            fileContent = UserView.requestFieldFromUser("content", 0);
            file.setContent(fileContent);
        }
        userFileRepository.update(file, fileId);
        UserFileController.getMainUserFileMenu(user);
    }

    public static void createUserFile(User user) {
        UserFile userFile;
        String fileName = UserFileView.requestFieldFromUser("file name", 2);
        String fileContent = UserFileView.requestFieldFromUser("content", 0);
        userFile = new UserFile(0, fileName, FileStatus.ACTIVE, fileContent);
        long fileId = userFileRepository.create(userFile);
        try {
            eventRepository.create(new Event(0, userFileRepository.getById(fileId),
                    new Date(), FileStatus.ACTIVE, user.getId()));
        } catch (Exception ex) {
            System.out.println("Something strange happened");
        }
        UserFileController.getMainUserFileMenu(user);
    }

    public static void getFileContent(User user) {
        long fileId;
        String fileContent;
        UserFile file;
        try {
            fileId = Long.parseLong(UserFileView.requestFieldFromUser("fileId", 1));
            file = userFileRepository.getById(fileId);
            fileContent = userFileRepository.getFileContent(fileId);
        } catch (NumberFormatException ex) {
            System.out.println("Wrong id format");
            UserFileController.getMainUserFileMenu(user);
            return;
        } catch (Exception ex) {
            System.out.println("File not found or something went wrong");
            UserFileController.getMainUserFileMenu(user);
            return;
        }

        String userInput = UserFileView.showFileContent(file.getFileName(), fileContent);
        switch (userInput) {
            case "back":
                UserFileController.getMainUserFileMenu(user);
                break;
            case "q":
                System.out.println("Bye-bye");
                break;
        }
    }
}
