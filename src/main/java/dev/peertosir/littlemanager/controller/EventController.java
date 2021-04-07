package dev.peertosir.littlemanager.controller;

import dev.peertosir.littlemanager.model.Event;
import dev.peertosir.littlemanager.model.User;
import dev.peertosir.littlemanager.repository.abstraction.EventRepository;
import dev.peertosir.littlemanager.repository.impl.JavaIOEventRepository;
import dev.peertosir.littlemanager.view.EventView;

import java.util.List;
import java.util.stream.Collectors;

public class EventController {

    private static final EventRepository<Event, Long> eventRepository = new JavaIOEventRepository();

    public static void getAllEvents(User user, boolean areUserEvents) {
        List<Event> events = areUserEvents ? eventRepository.getAllUserEvents(user.getId()) : eventRepository.getAll();

        List<String> userFiles = events.stream().map(event -> event.getUserFile().getFileName())
                .collect(Collectors.toList());
        List<String> occuredDates = events.stream().map(event -> event.getOccuredAt().toString())
                .collect(Collectors.toList());
        List<String> fileStatuses = events.stream().map(event -> event.getFileStatus().name())
                .collect(Collectors.toList());
        List<Long> userIds = events.stream().map(Event::getUserId)
                .collect(Collectors.toList());
        String userInput;
        if (areUserEvents) {
            userInput = EventView.showUserEvents(userFiles, occuredDates, fileStatuses);
        } else  {
            userInput = EventView.showAllEvents(userFiles, occuredDates, fileStatuses, userIds);
        }
        switch (userInput) {
            case "back":
                if (areUserEvents) {
                    UserController.getUser(user.getId());
                } else {
                    MainMenuController.MainMenu(user);
                }
                break;
            case "q":
                System.out.println("Bye-bye");
                break;
        }
    }
}
