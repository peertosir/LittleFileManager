package dev.peertosir.littlemanager.view;

import java.util.List;

public class EventView extends BaseView {
    public static String showAllEvents(List<String> files, List<String> dates,
                                       List<String> statuses, List<Long> userIds) {
        System.out.println("Events: ");
        for (int i = 0; i < files.size(); i++) {
            System.out.println("File: " + files.get(i) + "\tDate: " + dates.get(i) + "\tStatus changed to: "
                    + statuses.get(i) + "\tUserID: " + userIds.get(i));
        }
        String menu = "\n1. Enter 'back' to return to main menu\n2. Enter 'q' to quit";
        String[] options = {"back", "q"};
        return getMenuUserChoice(options, menu);
    }

    public static String showUserEvents(List<String> files, List<String> dates,
                                       List<String> statuses) {
        System.out.println("Participated in events: ");
        for (int i = 0; i < files.size(); i++) {
            System.out.println("File: " + files.get(i) + "\tDate: " + dates.get(i) + "\tStatus changed to: "
                    + statuses.get(i));
        }
        String menu = "\n1. Enter 'back' to return to main menu\n2. Enter 'q' to quit";
        String[] options = {"back", "q"};
        return getMenuUserChoice(options, menu);
    }

}
