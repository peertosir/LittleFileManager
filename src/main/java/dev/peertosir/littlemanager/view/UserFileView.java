package dev.peertosir.littlemanager.view;

import dev.peertosir.littlemanager.utils.Helpers;

import java.util.*;

public class UserFileView extends BaseView {
    public static String showAllUserFiles(List<String> ids, List<String> names, List<String> statuses) {
        System.out.println("\nList of all files\n");
        for (int i = 0; i < ids.size(); i++) {
            System.out.println(ids.get(i) + ". " + names.get(i) + " - " + statuses.get(i));
        }
        String menu = "\n1. Enter 'back' to return to main menu\n2. Enter 'q' to quit";
        String[] options = {"back", "q"};
        return getMenuUserChoice(options, menu);
    }

    public static String showAllActiveUserFiles(List<String> ids, List<String> names, List<String> statuses) {
        System.out.println("List of all active files\n");
        for (int i = 0; i < ids.size(); i++) {
            System.out.println(ids.get(i) + ". " + names.get(i) + " - " + statuses.get(i));
        }
        String menu = "\n1. Enter 'back' to return to main menu\n2. Enter 'q' to quit\n3. Enter 'showfile' - to show file" +
                " content\n4. Enter 'editfile' - to edit file\n5. Enter 'deletefile' - to delete file";
        String[] navigation = {"back", "q", "showfile", "editfile", "deletefile"};
        String[] options = Helpers.arrayConcat(ids.toArray(new String[0]), navigation);
        return getMenuUserChoice(options, menu);
    }

    public static String showFileMenu() {
        String[] options = {"showall", "showactive", "createfile", "back"};
        String menu = "\n1. Enter 'showall' to see all files " +
                "\n2. Enter 'showactive' to see all " +
                "\n3. Enter 'createfile' to create file " +
                "\n4. Enter 'back' to return back";
        return getMenuUserChoice(options, menu);
    }

    public static String showFileContent(String file, String fileContent) {
        System.out.println("FILE NAME: " + file);
        System.out.println();
        System.out.println("CONTENT: \n" + fileContent);
        String menu = "1. Enter 'back' to return to file menu\n2. Enter 'q' to quit";
        String[] options = {"back", "q"};
        return getMenuUserChoice(options, menu);
    }

}
